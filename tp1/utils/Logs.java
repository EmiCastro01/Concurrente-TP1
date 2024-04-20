package tp1.utils;

public class Logs {
    private static boolean showLog = true;

    public static void Log(Thread thread, String message){
        if(showLog){
            System.out.println("[" + thread.getName() + "] " + message);
        }
    }
}
