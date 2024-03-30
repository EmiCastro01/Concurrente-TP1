package tp1;
public class Main {

    public static void main(String[] args){
        Avion avion = new Avion();
        avion.getAsiento(1,1).ocuparAsiento();
        avion.getAsiento(4,2).ocuparAsiento();
        avion.getAsiento(2,3).ocuparAsiento();
        avion.getAsiento(3,8).ocuparAsiento();
        avion.printAvion();
    }
}
