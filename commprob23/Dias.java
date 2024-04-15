public abstract class Dias {
    protected String fecha;
    
    public Dias(String fecha) {
        this.fecha=fecha;
    }
    protected abstract boolean verificarHora(int hora);
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   
}

