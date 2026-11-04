package caso3;

public class ControladorProcesador extends Controlador {

    // Se pasa 0 como tamaño de unidad para indicar que no hay conversión de GiB
    public ControladorProcesador(int capacidadTotal) {
        super(capacidadTotal, 0); 
    }

    @Override
    public synchronized boolean reservar(int procesadoresSolicitados, MV mv) {
        return super.reservar(procesadoresSolicitados, mv);
    }

    @Override
    public synchronized void liberar(MV mv) {
        super.liberar(mv);
    }
}

