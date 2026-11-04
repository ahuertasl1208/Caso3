package caso3;

public class Controlador {
    private int tamUnidad; // Tamaño en KiB por unidad (página/bloque)
    private MV[] dueno;     // Representa cada unidad libre (null) u ocupada por una MV

    public Controlador(int capacidadTotal, int tamUnidad) {
        this.tamUnidad = tamUnidad;
        
        // Si tamUnidad > 0 se convierte de GiB a número de unidades
        int numUnidades = (tamUnidad > 0) ? (capacidadTotal * 1024 * 1024) / tamUnidad : capacidadTotal;
        this.dueno = new MV[numUnidades];
    }

    // Intenta reservar la cantidad requerida de forma atómica
    public synchronized boolean reservar(int cantidadSolicitada, MV mv) {
        int unidadesNecesarias = cantidadSolicitada;
        
        // Traducción de GiB a múltiplos de unidad si aplica
        if (tamUnidad > 0) {
            long bytesSolicitados = (long) cantidadSolicitada * 1024 * 1024;
            unidadesNecesarias = (int) Math.ceil((double) bytesSolicitados / tamUnidad);
        }

        // Contar cuantas unidades están libres
        int libres = 0;
        for (MV slot : dueno) {
            if (slot == null) libres++;
        }

        // Si no alcanza todo el recurso, no asigna nada
        if (libres < unidadesNecesarias) {
            return false;
        }

        // Asignar las unidades necesarias a la MV
        int asignadas = 0;
        for (int i = 0; i < dueno.length && asignadas < unidadesNecesarias; i++) {
            if (dueno[i] == null) {
                dueno[i] = mv;
                asignadas++;
            }
        }
        return true;
    }

    // Libera todas las unidades asignadas a la MV especificada
    public synchronized void liberar(MV mv) {
        for (int i = 0; i < dueno.length; i++) {
            if (dueno[i] == mv) {
                dueno[i] = null;
            }
        }
    }
}
