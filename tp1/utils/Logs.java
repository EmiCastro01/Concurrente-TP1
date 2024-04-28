package tp1.utils;

/**
 * La clase Logs proporciona métodos estáticos para registrar mensajes de error y de log.
 * Los mensajes sólo se registran si la variable showLog es verdadera.
 */
public class Logs {
    private static boolean showLog = true;

    /**
     * Registra un mensaje de error.
     * 
     * Este método imprime un mensaje de error en la consola, precedido por la palabra "ERROR" y el nombre del hilo que lo invocó.
     * El mensaje sólo se imprime si la variable showLog es verdadera.
     * 
     * @param thread El hilo que invoca este método.
     * @param mensaje El mensaje de error a registrar.
     */
    public static void Error(Thread thread, String mensaje){
        if(showLog){
            System.out.println("ERROR: [" + thread.getName() + "] " + mensaje);
        }
    }

    /**
     * Registra un mensaje de log.
     * 
     * Este método imprime un mensaje de log en la consola, precedido por el nombre del hilo que lo invocó.
     * El mensaje sólo se imprime si la variable showLog es verdadera.
     * 
     * @param thread El hilo que invoca este método.
     * @param mensaje El mensaje de log a registrar.
     */
    public static void Log(Thread thread, String mensaje, int numeroAsiento){
        if(showLog){
            System.out.println("[" + thread.getName()  + "] " /*+ mensaje*/ + numeroAsiento);
        }
    }
}
