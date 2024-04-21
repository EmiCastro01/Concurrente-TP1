package tp1.utils;

public class Logs {
    private static boolean showLog = true;

    public static void Error(Thread thread, String mensaje){
        if(showLog){
            System.out.println("ERROR: [" + thread.getName() + "] " + mensaje);
        }
    }
    public static void Log(Thread thread, String mensaje, int numeroAsiento){
        if(showLog){
            System.out.println("[" + thread.getName()  + "] " /*+ mensaje*/ + numeroAsiento);
        }
    }
}
