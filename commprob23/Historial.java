import java.util.ArrayList;
import java.util.Scanner;


public class Historial {
    private Ventas objectventas = new Ventas();

    public void setobjectVentas(Ventas objectventas) {
        this.objectventas = objectventas;
    }

    public Ventas get() {
        return objectventas;
    }

    public void mostrarReporte(ArrayList<Ventas> listaVentas) {
        for (int i = 0; i < listaVentas.size(); i++) {
            Citas cita = listaVentas.get(i).getObjetoCitas();
            Servicios servicio = cita.getObjServicios();
            System.out.println("Cita " + (i + 1) + " - Cliente: " + cita.getNombreCompleto() + ", Servicio: "
                    + servicio.getNombreServicios() + ", Precio: " + servicio.getPrecios());
        }
        double ingresosTotales = objectventas.calcularGanancias(listaVentas);
        System.out.println("La ganancia total fue: " + ingresosTotales);
    }

    public void editarReporte(ArrayList<Ventas> listaVentas){
        Scanner entrada = new Scanner(System.in);
        String nuevoNombre,nuevoServicio,nombre;
        int nuevoPrecio,r1;
        System.out.println("Ingrese el nombre del cliente");
        nombre= entrada.nextLine();
        for(int i=0; i<listaVentas.size();i++){
            Citas citas = listaVentas.get(i).getObjetoCitas();
            Servicios servicio = citas.getObjServicios();
            if(nombre.equalsIgnoreCase(citas.getNombreCompleto())){
                System.out.println("¿Qué quieres editar?(1 Nombre/2 Precios/3 Servicio).");
                do {
                    r1 = entrada.nextInt();
                    if (r1 < 1 || r1 > 3) {
                        System.out.println("Error, ingrese una opcion entre el 1, 2 o 3");
                    }
                } while (r1 < 1 || r1 > 3);
                switch (r1) {
                    case 1:
                        System.out.println("Ingrese la correcion del nuevo nomebre:");
                        entrada.nextLine();
                        nuevoNombre = entrada.nextLine();
                        citas.setNombreCompleto(nuevoNombre);
                        break;
                    case 2:
                        System.out.println("Ingrese la correccion del nuevo precio:");
                        nuevoPrecio = entrada.nextInt();
                            servicio.setPrecios(nuevoPrecio);
                        break;
                    case 3:
                        System.out.println("Ingrese la nueva correcion del servicio:");
                        entrada.nextLine();
                        nuevoServicio= entrada.nextLine();
                        servicio.setNombreServicios(nuevoServicio);
                        break;
                }
            }

        }
        
    }

}