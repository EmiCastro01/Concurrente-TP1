package tp1.procesos;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La clase ProcesoLogs implementa Runnable y se utiliza para registrar información de las reservas en un archivo de log.
 * Esta clase crea un nuevo archivo de log en la carpeta "logs" cada vez que se instancia.
 * Los métodos de esta clase se utilizan para escribir diferentes tipos de información en el archivo de log.
 */
public class ProcesoLogs  implements Runnable{

    private String folder = "logs";
    private String filePath;
    private File file;

    private boolean printHeader = true;
    private tp1.GestorDeReservas GestorReservas;
    /**
     * Constructor de la clase ProcesoLogs.
     * 
     * Este constructor crea un nuevo archivo de log con un nombre basado en la fecha y hora actuales.
     * Si el archivo no existe, lo crea.
     * 
     * @param gestorReservas El gestor de reservas del que se va a registrar la información.
     * @throws RuntimeException si ocurre un error al crear el archivo.
     */
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
    /**
     * Escribe en el archivo de log la duración total y la ocupación total.
     * 
     * @param secondsDuration La duración total en segundos.
     */
    public void WriteEndLog(double secondsDuration){
        PrintLogLine();
        WriteInFile(String.format("Duración: %s |  Ocupación Total: %s",secondsDuration, GestorReservas.getOcupacionTotal()));

    }
    /**
     * Escribe en el archivo de log una línea con la cantidad de reservas reservadas, confirmadas, canceladas y verificadas.
     */
    @Override
    public void run() {
        PrintLogLine();
    }

    /**
     * Escribe en el archivo de log los tiempos de los procesos de reserva, confirmación de pagos, cancelación y validación.
     */
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

    /**
     * Escribe en el archivo de log una línea con la cantidad de reservas reservadas, confirmadas, canceladas y verificadas.
     * 
     * Este método es similar al método run, pero no es parte de la interfaz Runnable.
     */
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

    /**
     * Escribe una línea de texto en el archivo de log.
     * 
     * @param text El texto a escribir en el archivo de log.
     * @throws RuntimeException si ocurre un error al escribir en el archivo.
     */
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

    /**
     * Devuelve una cadena de texto con un valor numérico alineado a la derecha y rellenado con un carácter dado hasta alcanzar una longitud dada.
     * 
     * @param value El valor numérico a alinear a la derecha.
     * @param length La longitud total de la cadena de texto.
     * @param charPad El carácter con el que se va a rellenar la cadena de texto.
     * @return La cadena de texto con el valor numérico alineado a la derecha.
     */
    private String padRigth(int value, int length, char charPad) {
        return padRigth(""+value,length, charPad);
    }
    /**
     * Devuelve una cadena de texto alineada a la derecha y rellenada con un carácter dado hasta alcanzar una longitud dada.
     * 
     * @param inputString La cadena de texto a alinear a la derecha.
     * @param length La longitud total de la cadena de texto.
     * @param charPad El carácter con el que se va a rellenar la cadena de texto.
     * @return La cadena de texto alineada a la derecha.
     */
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
