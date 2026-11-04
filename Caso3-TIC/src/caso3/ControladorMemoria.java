package caso3;

public class ControladorMemoria extends Controlador {

    // Se recibe la capacidad total y el tamaño de la página en KiB
    public ControladorMemoria(int capacidadTotalGiB, int tamPaginaKiB) {
        super(capacidadTotalGiB, tamPaginaKiB);
    }

    @Override
    public synchronized boolean reservar(int gibSolicitados, MV mv) {
        return super.reservar(gibSolicitados, mv);
    }

    @Override
    public synchronized void liberar(MV mv) {
        super.liberar(mv);
    }
}
