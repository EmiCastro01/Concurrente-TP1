package tp1.procesos;

public interface Proceso {
    boolean validarSiContinua();
    void procesar();
    void esperar();

}
