package caso3;

import java.util.ArrayList;  


public class BuzonEntrada {

	private ArrayList<Solicitud> cola;
    private int capacidad;
    private int usuariosActivos;
    
    public BuzonEntrada(int capacidad, int usuariosActivos) {
    	this.capacidad = capacidad;
    	this.usuariosActivos = usuariosActivos;
    	this.cola = new ArrayList<>();
    	
    }

    public synchronized void depositar(Solicitud s) {
    	while (cola.size() == capacidad ) {
    		try {
    			wait(); 
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}	 
    	}	
    	cola.add (s); 
    }

    public synchronized Solicitud retirar() {
        if (cola.size() == 0) {
        	return null;
        }
        Solicitud s = cola.remove(0);
        notify();
        return s;
    }

    public synchronized void usuarioTermino() {
    	
    	usuariosActivos--;  
    }
    
    public synchronized boolean hayTrabajo() { 
    	if (cola.size() > 0 || usuariosActivos > 0  ) { 
    		
    		return true;
    	}
    	return false; 
    }
    
    

}
