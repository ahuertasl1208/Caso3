package caso3;

public class Solicitud {

    private final String id;
    private final int procesadores;
    private final int memoriaGiB;
    private final int discoGiB;

    public Solicitud(String id, int procesadores, int memoriaGiB, int discoGiB){

        this.id = id; 
        this.procesadores = procesadores; 
        this.memoriaGiB = memoriaGiB; 
        this.discoGiB = discoGiB;
    }

    public String getId(){
        return id;
    }

    public int getProcesadores(){
        return procesadores; 
    }

    public int getMemoriaGiB() {
        return memoriaGiB; 
    }

    public int getDiscoGiB() { 
        return discoGiB; 
    }

    @Override 

    public String toString(){
        return id + " [cpu=" + procesadores + ", memo=" + memoriaGiB + ", disc =" + discoGiB + " GiB]";  
    }
}
