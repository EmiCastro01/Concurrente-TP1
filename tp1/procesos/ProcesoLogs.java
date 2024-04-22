package tp1.procesos;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        PrintLogLine();
        WriteInFile(String.format("Duración: %s |  Ocupación Total: %s",secondsDuration, GestorReservas.getOcupacionTotal()));

    }
    @Override
    public void run() {
        PrintLogLine();
    }

    public void imprimirTiemposPorProceso(){
        WriteInFile("Tiempos por proceso");
        WriteInFile("Reserva | Confirmación Pagos | Cancelación | Validación");
        WriteInFile(String.format("%s|%s|%s|%s",
                padRigth(GestorReservas.getMilisProcesoReserva() + "ms", 8, ' ' ),
                padRigth(GestorReservas.getMilisProcesoPago()+ "ms", 20, ' '),
                padRigth(GestorReservas.getMilisProcesoCancelacion()+ "ms", 13, ' ' ),
                padRigth(GestorReservas.getMilisProcesoVerificacion()+ "ms", 11, ' ')));
        WriteInFile("");
    }

    public void PrintLogLine(){
        if(printHeader){
            WriteInFile("Reservadas | Confirmadas | Canceladas | Aprobadas");
            printHeader = false;
        }
        WriteInFile(String.format("%s|%s|%s|%s",
                padRigth(GestorReservas.getCountReservasReservadas(), 11, ' ' ),
                padRigth(GestorReservas.getCountReservasConfirmadas(), 13, ' '),
                padRigth(GestorReservas.getCountReservasCanceladas(), 12, ' ' ),
                padRigth(GestorReservas.getCountReservasVerificadas(), 10, ' ')));
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

    private String padRigth(int value, int length, char charPad) {
        return padRigth(""+value,length, charPad);
    }
    private String padRigth(String inputString, int length, char charPad) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(inputString);
        while (sb.length() < length) {
            sb.append(charPad);
        }
        return sb.toString();
    }
}
