package procesos;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProcesoLogs  implements Runnable{

    private String folder = "logs";
    private String filePath;
    private File file;

    private tp1.Lista GestorReservas;
    public ProcesoLogs(tp1.Lista gestorReservas){

        GestorReservas = gestorReservas;
        filePath = String.format("%s_log.txt", DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()));
        String localDir = System.getProperty("user.dir");
        filePath = String.format("%s\\%s\\%s",localDir,folder,filePath);
        file = new File(filePath);
        String basePath = file.getAbsolutePath();
        System.out.println(basePath);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void WriteEndLog(double secondsDuration){
        WriteInFile(String.format("Duración: %s |  Ocupación Total: %s",secondsDuration, GestorReservas.getOcupacionTotal()));

    }
    @Override
    public void run() {
        WriteInFile(String.format("Canceladas: %s |  Confirmadas: %s",GestorReservas.getCountReservasCanceladas(),GestorReservas.getCountReservasConfirmadas()));
    }

    private void WriteInFile(String text){
        try {
            BufferedWriter outStream= new BufferedWriter(new FileWriter(file, true));
            outStream.append(text);
            outStream.newLine();
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
