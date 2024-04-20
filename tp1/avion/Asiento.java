package tp1.avion;

public class Asiento {

    private Boolean estadoDeAsiento;
    private Integer numeroDeAsiento;
    
    public Asiento(){
        this.estadoDeAsiento = false;
    }

    public void ocuparAsiento(){
        this.setEstadoDeAsiento(true);
    }
    public void liberarAsiento(){
        this.setEstadoDeAsiento(false);
    }
    public void setNumeroDeAsiento(int numeroDeAsiento){
        this.numeroDeAsiento = numeroDeAsiento;
    }

    public void setEstadoDeAsiento(Boolean estadoDeAsiento){
        this.estadoDeAsiento = estadoDeAsiento;                 // true para ocupado, false para libre
    }
    public Estado getEstadoDeAsiento() {



        return this.estadoDeAsiento ? Estado.OCUPADO : Estado.LIBRE;
    }

    public Integer getNumeroDeAsiento() {
        return numeroDeAsiento; 
    }
}
