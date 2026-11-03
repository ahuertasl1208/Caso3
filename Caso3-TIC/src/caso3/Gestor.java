package caso3;

public class Gestor extends Thread {

    private int id;
    private BuzonEntrada buzon;
    
    public Gestor(int id, BuzonEntrada buzon) {
    	this.id = id; 
    	this.buzon = buzon; 
    }

    @Override
    public void run() {
    	while (buzon.hayTrabajo()) {
    		Solicitud s = buzon.retirar();
    		
    		if(s == null) {
    			Thread.yield();
    		} else {
    			System.out.println("Gestor "+id+" retiro "+s);
    			//TODO crear la MV con s cuando este listo =(-.-)=
    		}
    	}
    	
    	System.out.println("Gestor "+id+" termino");
    }
}
