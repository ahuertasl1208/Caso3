package caso3;

import java.util.Random;

public class MV extends Thread {
    private Solicitud solicitud;
    private Controlador cProcesadores;
    private Controlador cMemoria;
    private Controlador cDisco;
    private ZonaEspera zonaEspera;

    public MV(Solicitud solicitud, Controlador cProcesadores, Controlador cMemoria, Controlador cDisco, ZonaEspera zonaEspera) {
        this.solicitud = solicitud;
        this.cProcesadores = cProcesadores;
        this.cMemoria = cMemoria;
        this.cDisco = cDisco;
        this.zonaEspera = zonaEspera;
    }

    @Override
    public void run() {
        boolean recursosObtenidos = false;

        // Bucle de intentos de reserva
        while (!recursosObtenidos) {
            // Intentar reservar Procesadores
            if (cProcesadores.reservar(solicitud.getProcesadores(), this)) {
                // Intentar reservar Memoria
                if (cMemoria.reservar(solicitud.getMemoriaGiB(), this)) {
                    // Intentar reservar Disco
                    if (cDisco.reservar(solicitud.getDiscoGiB(), this)) {
                        recursosObtenidos = true; // Se consiguieron los 3 recursos exitosamente
                    } else {
                        // Falló disco: liberar memoria y procesadores
                        cMemoria.liberar(this);
                        cProcesadores.liberar(this);
                    }
                } else {
                    // Falló memoria: liberar procesadores
                    cProcesadores.liberar(this);
                }
            }

            // Si no obtuvo todos los recursos, duerme en la Zona de Espera
            if (!recursosObtenidos) {
                zonaEspera.espera(this);
            }
        }

        // Ejecución simulada de la MV (entre 4,000 y 40,000 ms)
        try {
            int tiempoEjecucion = 4000 + new Random().nextInt(36001);
            Thread.sleep(tiempoEjecucion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Liberar todos los recursos asignados
        cDisco.liberar(this);
        cMemoria.liberar(this);
        cProcesadores.liberar(this);

        // Notificar a la zona de espera para despertar a MVs pendientes
        zonaEspera.despertar();
    }
}
