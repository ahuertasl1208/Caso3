package caso3;
import java.util.Random;

public class Usuario extends Thread {

    private int id;
    private int numSolicitudes;
    private BuzonEntrada buzon; 
    private int maxProcesadores;
    private int maxMemoriaGiB;
    private int maxDiscoGiB; 
    private final Random random = new Random();
    
    
    public Usuario (int id, int numSolicitudes, BuzonEntrada buzon, int maxProcesadores, int maxMemoriaGiB, int maxDiscoGiB) {
    	this.id = id;
    	this.numSolicitudes = numSolicitudes;
    	this.buzon = buzon;
        this.maxProcesadores = maxProcesadores;
    	this.maxMemoriaGiB = maxMemoriaGiB; 
    	this.maxDiscoGiB = maxDiscoGiB; 
    	
    }
    
    

    @Override
    public void run() {
    	for (int i = 1; i <= numSolicitudes; i++) {
    		Solicitud s = generarSolicitud(i);
    		buzon.depositar(s);
    		System.out.println("Usuario " + id + " depositó " + s);
    	}
    	buzon.usuarioTermino();
    	System.out.println("Usuario " + id + " terminó");
    	
    }
    
    
 
    private Solicitud generarSolicitud(int secuencial) {
    	
        int limitePro = limitador(maxProcesadores);
        int nProcesadores = random.nextInt(limitePro) + 1;
        
        int limiteMem = limitador(maxMemoriaGiB);
        int memGenerada = random.nextInt(limiteMem)+1; 
        int memEspacio = powerChecker(memGenerada);
        
        int limiteDisc = limitador(maxDiscoGiB);
        int nDisc = random.nextInt(limiteDisc)+1;
        
        if (!checkMultDos(nDisc)) {
        	nDisc = nDisc - 1; 
        }
        if (nDisc == 0) {
        	nDisc = 2; 
        }
        
        return new Solicitud("u"+id+"-"+secuencial,nProcesadores,memEspacio,nDisc);
        
    }
    
   private int limitador(int max) { 
	   
	   int limite = (int) Math.round(0.75*max);
	   return limite; 
   }
   
   private boolean checkMultDos(int valor) {
	   if (valor%2 == 0) {
		   return true;
	   }
	   return false; 
   }
   
   private int powerChecker(int valor) {
	   int p = 1; 
	   while (p*2 <= valor) { 
		  p = p*2;
	   }
	   return p; 
   }
}
