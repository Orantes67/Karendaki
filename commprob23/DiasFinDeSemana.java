public class DiasFinDeSemana extends Dias{
    public DiasFinDeSemana(String fecha){
        super(fecha);
    }
    public boolean verificarHora(int hora){
        if(hora>= 9&& hora<15){
            return  true;
        }
        return false;
    }

}
