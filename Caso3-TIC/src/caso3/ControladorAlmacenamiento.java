package caso3;

public class ControladorAlmacenamiento extends Controlador {

    // Se recibe la capacidad total y el tamaño del bloque en KiB
    public ControladorAlmacenamiento(int capacidadTotalGiB, int tamBloqueKiB) {
        super(capacidadTotalGiB, tamBloqueKiB);
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
