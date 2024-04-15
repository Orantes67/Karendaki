
public class Citas {
    private String nombreCompleto;
    private long numeroCelular;
    private String fecha;
    private int hora;
    private Servicios objServicios = new Servicios();
  
    
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto=nombreCompleto;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    } 
    public void setNumeroCelular(long numeroCelular){
        this.numeroCelular=numeroCelular;
    }
    public long getNumeroCelular() {
        return numeroCelular;
    } 
    public void setFecha(String fecha){
        this.fecha=fecha;
    }
    public String getFecha() {
        return fecha;
    } 
    public void setHora(int hora){
        this.hora=hora;
    }
    public int getHora() {
        return hora;
    } 

    public void setObjServicios(Servicios objServicios) {
        this.objServicios = objServicios;
    }
    public Servicios getObjServicios() {
        return objServicios;
    }

    
}

