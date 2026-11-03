package caso3;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) throws Exception {
    	
    	int nu = 0, base = 0, ng = 0, tam1 = 0, tam2 = 0;
        int procesadores = 0, memoria = 0, pagina = 0, almacenamiento = 0, bloque = 0;
        
    	BufferedReader lector = new BufferedReader(new FileReader("config.txt"));
    	
    	String linea; 
    	
    	while ((linea = lector.readLine()) != null) {
    		
    		if(linea.trim().isEmpty()) {
    			continue;
    		}
    		String [] partes = linea.split("=");
    		String clave = partes[0];
    		int valor = Integer.parseInt(partes[1]);
    		
    	    if (clave.equals("nu")) {
    	        nu = valor;
    	    } else if (clave.equals("base")) {
    	        base = valor;
    	    } else if (clave.equals("ng")) {
    	        ng = valor;
    	    } else if (clave.equals("tam1")) {
    	        tam1 = valor;
    	    } else if (clave.equals("tam2")) {
    	        tam2 = valor;
    	    } else if (clave.equals("procesadores")) {
    	        procesadores = valor;
    	    } else if (clave.equals("memoria")) {
    	        memoria = valor;
    	    } else if (clave.equals("pagina")) {
    	        pagina = valor;
    	    } else if (clave.equals("almacenamiento")) {
    	        almacenamiento = valor;
    	    } else if (clave.equals("bloque")) {
    	        bloque = valor;
    	    }
    	}
    	lector.close();
    	
    	BuzonEntrada buzon = new BuzonEntrada(tam1, nu);
    	
    	
    	Usuario[] usuarios = new Usuario[nu];
    	
    	for (int i = 0; i< nu; i++) {
    		int id = i + 1; 
    		
    		usuarios[i] = new Usuario(id,base*id,buzon,procesadores,memoria,almacenamiento);
    	}
   	
    	Gestor[] gestores = new Gestor[ng];
    	for (int i = 0; i < ng; i++) { 
    		gestores[i] = new Gestor(i+1, buzon);
    	}
    	
    	for (Usuario u : usuarios) u.start();
    	for (Gestor g : gestores) g.start();

    	for (Usuario u : usuarios) u.join();
    	for (Gestor g : gestores) g.join();
    }
}
