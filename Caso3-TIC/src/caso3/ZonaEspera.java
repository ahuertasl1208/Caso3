package caso3;

public class ZonaEspera {
    private int enEspera;
    private final int umbral;

    public ZonaEspera(int umbral) {
        this.umbral = umbral;
        this.enEspera = 0;
    }

    // La MV entra en espera pasiva cuando no puede reservar recursos
    public synchronized void espera(MV mv) {
        enEspera++;
        try {
            wait(); // Espera pasiva
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            enEspera--;
        }
    }

    // Despierta a todas las MVs para reintentar la reserva completa
    public synchronized void despertar() {
        notifyAll(); // Se usa notifyAll para dar oportunidad a las MVs que quepan
    }

    // Utilizado por el Gestor para verificar la condición del umbral
    public synchronized int pendiente() {
        return enEspera;
    }

    public int getUmbral() {
        return umbral;
    }
}
