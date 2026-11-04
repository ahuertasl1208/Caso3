package caso3;

import java.util.ArrayList;

public class Gestor extends Thread {

    private int id;
    private BuzonEntrada buzon;
    private ZonaEspera zona;
    private Controlador cProcesadores;
    private Controlador cMemoria;
    private Controlador cDisco;
    private ArrayList<MV> mvsCreadas;
    
    public Gestor(int id, BuzonEntrada buzon, ZonaEspera zona,
            Controlador cProcesadores, Controlador cMemoria, Controlador cDisco) {
    	this.id = id;
    	this.buzon = buzon;
    	this.zona = zona;
    	this.cProcesadores = cProcesadores;
    	this.cMemoria = cMemoria;
    	this.cDisco = cDisco;
    	this.mvsCreadas = new ArrayList<>();
    }

    @Override
    public void run() {
    	while (buzon.hayTrabajo()) {
    		
    		if (zona.pendiente() >= zona.getUmbral() ) {
    			
    			esperaTotal();
    		}
    		
    		Solicitud s = buzon.retirar();
    		
    		if(s == null) {
    			Thread.yield();
    		} else {
    			System.out.println("Gestor "+id+" retiro "+s);
    			
    			MV mv = new MV(s, cProcesadores, cMemoria, cDisco, zona);
    			mv.start();
    			mvsCreadas.add(mv);
    		}
    	}
    	
    	for (MV mv: mvsCreadas) {
    		try {
    			mv.join();
    		}catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	System.out.println("Gestor "+id+" termino");
    }
    
    private void esperaTotal() { 
    	while (zona.pendiente() > 0) {
    		
    		Thread.yield();
    	}
    }
}
