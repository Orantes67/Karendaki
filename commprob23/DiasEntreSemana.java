public class DiasEntreSemana extends Dias{
    public DiasEntreSemana(String fecha){
        super(fecha);
    }
    public boolean verificarHora(int hora){
        if(hora>= 9&& hora<17){
            return  true;
        }
        return false;
    }
}
