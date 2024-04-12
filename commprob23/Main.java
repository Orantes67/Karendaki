import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
       
        ArrayList<Servicios> listaServicios = new ArrayList<>();
        ArrayList<Citas> listaCitas = new ArrayList<>();
        ArrayList<Ventas> listaVentas = new ArrayList<>();

        verMenuPrincipal(listaServicios,listaCitas,listaVentas);
        
    }
    public static void verCalendario(ArrayList<Citas> listaCitas,Calendario  objCalendario){
        objCalendario.mostrarCalendario(listaCitas);
        
    }

    public static void VerMenuVentas(ArrayList<Citas> listaCitas,ArrayList<Ventas> listaVentas,Historial objHistorial){
        Scanner entrada = new Scanner(System.in); 
        int r1; 
        Ventas objVentas = new Ventas();

        do {
            System.out.println("Menú Ventas.");
            System.out.println("Seleccionar (1 Confirmar venta/2 Ver Reporte /3 Editar Reporte /4 Salir).");
            r1 = entrada.nextInt();
            if(r1 <1 || r1>4){
                System.out.println("Por favor ingrese una opcion entre el 1 al 4 ");
            }
            switch (r1) {
                case 1:
                    if(listaCitas.size()==0){
                        System.out.println(ANSI_RED + "No hay citas para confirmar el pago, porfavor ingrese una cita." + ANSI_RESET);
                    }else{
                        objVentas.confirmarServiciosPago(listaCitas,listaVentas);
                    }
                    break;
                case 2:
                    if(listaVentas.size()==0){
                        System.out.println(ANSI_RED + "No hay ventas para mostrar." + ANSI_RESET);
                    }else{
                        objHistorial.mostrarReporte(listaVentas);
                    }
                    break;
                case 3:
                    if(listaVentas.size()==0){
                        System.out.println(ANSI_RED + "No hay ventas para editar." + ANSI_RESET);
                    }else{
                        objHistorial.editarReporte(listaVentas);
                    }
                    break;                
            }
        } while (r1!=4);
    }
    
    public static void VerMenuCitas(ArrayList<Servicios> listaServicios,ArrayList<Citas> listaCitas,Calendario objCalendario){
        Scanner entrada = new Scanner(System.in); 
        int r1; 
       
        do {
            System.out.println("Menú citas.");
            System.out.println("Seleccionar (1 Agregar cita/2 Editar cita/3 Mostrar citas/4 Eliminar cita/5 Salir).");
            r1 = entrada.nextInt();
            if(r1 <1 || r1>5){
                System.out.println("Por favor ingrese una opcion entre el 1 al 5 ");
            }
            switch (r1) {
                case 1:
                    if(listaServicios.size()==0){
                        System.out.println(ANSI_RED + "No hay servicios para editar, porfavor ingrese algun servicio." + ANSI_RESET);
                    }else{
                        int r2;
                        do {
                            objCalendario.agregarCitas(listaServicios,listaCitas);
                            listaCitas.add(objCalendario.getObjetoCitas());
                            System.out.println("¿Quieres agregar otra cita (1 Sí/2 No)?.");
                            do {
                                r2 = entrada.nextInt();
                                if(r2<1 || r2>2){
                                System.out.println("Por favor ingrese una opcion entre el 1 o el 2 ");
                                }
                            } while (r2<1 || r2>2);
                        } while (r2==1);
                    }
                    break;
                case 2:
                    objCalendario.editarCitas(listaCitas,listaServicios);
                    break;
                case 3:
                
                    objCalendario.mostrarCitas(listaCitas);
                
                    break;
                case 4:
                    objCalendario.eliminarCitas(listaCitas);
                
                    break;
            }
        } while (r1!=5);
    }
    
    public static void VerMenuServicios(ArrayList<Servicios> listaServicios){
        Scanner entrada = new Scanner(System.in); 
        int r1,r2; 
        Servicios objServicios= new Servicios();
        Servicios servicio= new Servicios();
        
        do {
            System.out.println("Menú servicios.");
            System.out.println("Seleccionar (1 Agregar/2 Editar/3 Mostrar/4 Eliminar/5 Salir).");
            r1 = entrada.nextInt();
            if(r1 <1 || r1>5){
                System.out.println("Por favor ingrese una opcion entre el 1 al 5 ");
            }
            switch (r1) {
                case 1:
                do {
                    objServicios.agregarServicios();
                    listaServicios.add(objServicios);
                    System.out.println("¿Quieres agregar otro servicio (1 Sí/2 No)?.");
                    do {
                        r2 = entrada.nextInt();
                        if(r2<1 || r2>2){
                            System.out.println("Por favor ingrese una opcion entre el 1 o el 2 ");
                            }
                    } while (r2<1 || r2>2);
                } while (r2==1);
                    break;
                case 2:
                objServicios.editarServicios(servicio,listaServicios);
                    break;
                case 3:
                
                    objServicios.mostrarServicios(listaServicios);
                
                    break;
                case 4:
                objServicios.eliminarServicios(listaServicios);
                    break;
            }
        } while (r1!=5);
    }
    
    public static  void verMenuPrincipal( ArrayList<Servicios> listaServicios,ArrayList<Citas> listaCitas,ArrayList<Ventas> listaVentas){
        Scanner entrada = new Scanner(System.in); 
        int r1; 
        Calendario  objCalendario =new Calendario();
        Historial objHistorial = new Historial();
        
        Servicios objetoservicios = new Servicios();
        String nombre= "pestanas";
        objetoservicios.setNombreServicios(nombre);
        objetoservicios.setDescripcionServicios("Pegamento");
        objetoservicios.setPrecios(500);
        listaServicios.add(objetoservicios);

        Servicios objetoservicios2 = new Servicios();
        String nombre2= "unas";
        objetoservicios2.setNombreServicios(nombre2);
        objetoservicios2.setDescripcionServicios("Pegamento");
        objetoservicios2.setPrecios(200);
        listaServicios.add(objetoservicios2);
         
        Citas objetoCitas = new Citas();
        objetoCitas.setNombreCompleto("hector");
        objetoCitas.setNumeroCelular(961131808);
        objetoCitas.setHora(9);
        objetoCitas.setFecha("12/04/2024");
        objetoCitas.setObjServicios(objetoservicios);
        listaCitas.add(objetoCitas);

        Citas objeto2Citas = new Citas();
        objeto2Citas.setNombreCompleto("pablo");
        objeto2Citas.setNumeroCelular(961123456);
        objeto2Citas.setHora(9);
        objeto2Citas.setFecha("13/04/2024");
        objeto2Citas.setObjServicios(objetoservicios);
        listaCitas.add(objeto2Citas);

   
        do {
            System.out.println("Menú principal.");
            System.out.println("Seleccionar (1 Ver citas/2 Menú de ventas/3 Menú de citas/4 Menú de servicios/5 Salir).");
            r1 = entrada.nextInt();
            if(r1 <1 || r1>5){
                System.out.println("Por favor ingrese una opcion entre el 1 al 5 ");
            }
            switch (r1) {
                case 1:
                    verCalendario(listaCitas,objCalendario);
                    break;
                case 2:
                    VerMenuVentas(listaCitas,listaVentas,objHistorial);
                    break;
                case 3:
                    VerMenuCitas( listaServicios,listaCitas,objCalendario);
                    break;
                case 4:
                    VerMenuServicios(listaServicios);
                    break;
            }
        } while (r1!=5);
    }

}