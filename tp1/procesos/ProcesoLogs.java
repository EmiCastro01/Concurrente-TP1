package tp1.procesos;


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

    private boolean printHeader = true;
    private tp1.GestorDeReservas GestorReservas;
    public ProcesoLogs(tp1.GestorDeReservas gestorReservas){

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
        if(printHeader){
            WriteInFile("Canceladas | Confirmadas");
            printHeader = false;
        }
        WriteInFile(String.format("%s      |%s",padLeft(GestorReservas.getCountReservasCanceladas(), 5, ' ' ),padLeft(GestorReservas.getCountReservasConfirmadas(), 7, ' ')));
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

    private String padLeft(int value, int length, char charPad) {
        return padLeft(""+value,length, charPad);
    }
    private String padLeft(String inputString, int length, char charPad) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(charPad);
        }
        sb.append(inputString);

        return sb.toString();
    }
}
