import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Servicios {
    private int precios;
    private String nombreServicios;
    private String descripcionServicios;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void setPrecios(int precios) {this.precios = precios;}
    public int getPrecios() { return precios;}

    public void setNombreServicios(String nombreServicios) {this.nombreServicios = nombreServicios;}
    public String getNombreServicios() {return nombreServicios;}

    public void setDescripcionServicios(String descripcionServicios) {this.descripcionServicios = descripcionServicios;}
    public String getDescripcionServicios() {return descripcionServicios;}

    public void agregarServicios() {
        Scanner entrada = new Scanner(System.in);
        String nombre, descripcion;
        boolean bandera = true;
        int precios;
        System.out.println("Ingrese el nombre del servicio.");
        nombre = entrada.nextLine();
        setNombreServicios(nombre);
        System.out.println("Ingrese la descripción del servicio.");
        descripcion = entrada.nextLine();
        setDescripcionServicios(descripcion);
        do {
            try {
                System.out.println("Ingrese el precio del servicio:");
                precios = entrada.nextInt();
                if (precios <= 0) {
                    System.out.println("El precio debe ser un número positivo mayor que cero.");
                    entrada.nextLine();
                } else {
                    setPrecios(precios);
                    bandera = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido para el precio.");
                entrada.nextLine();
            }
        } while (bandera == true);

    }

    public void editarServicios(Servicios servicio, ArrayList<Servicios> listaServicios) {
        if(listaServicios.size()==0){
            System.out.println(ANSI_RED + "No hay servicios para editar, porfavor ingrese algun servicio." + ANSI_RESET);
        }else{
            String nombre, nuevoNombre, nuevaDescripcion;
            int  nuevoPrecio, r1;
            boolean banderaedicion = true;
            boolean bandera = false, seguir;
            do {
                seguir=false;
                Scanner entrada = new Scanner(System.in);
                System.out.println("Ingrese el nombre del servicio a editar.");
                nombre = entrada.nextLine();
                for (Servicios objServicio : listaServicios) {
                    if (nombre.equals(objServicio.getNombreServicios())) {
                        bandera=true;
                        System.out.println("¿Qué quieres editar?(1 Nombre/2 Precios/3 Descripción).");
                        do {
                            r1 = entrada.nextInt();
                            if (r1 < 1 || r1 > 3) 
                                System.out.println("Error, ingrese una opcion entre el 1, 2 o 3");
                        } while (r1 < 1 || r1 > 3);
                        switch (r1) {
                            case 1:
                                System.out.println("Ingrese el nuevo nombre:");
                                entrada.nextLine();
                                nuevoNombre = entrada.nextLine();
                                objServicio.setNombreServicios(nuevoNombre);
                                break;
                            case 2:
                                do {
                                    try {
                                        System.out.println("Ingrese el nuevo precio:");
                                        nuevoPrecio = entrada.nextInt();
                                        if (nuevoPrecio <= 0) {
                                            System.out.println("El precio debe ser un número positivo mayor que cero.");
                                            entrada.nextLine();
                                        } else {
                                            objServicio.setPrecios(nuevoPrecio);
                                            banderaedicion = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Debe ingresar un número válido para el precio.");
                                        entrada.nextLine();
                                    }
                                } while (banderaedicion == true);
                                break;
                            case 3:
                                System.out.println("Ingrese la nueva descripción:");
                                entrada.nextLine();
                                nuevaDescripcion = entrada.nextLine();
                                objServicio.setDescripcionServicios(nuevaDescripcion);
                                break;
                        }
                    }
                }
                if (bandera == false) {
                    System.out.println("El servicio " + nombre + " no se encontro \n¿Desea seguir buscando? (true/false)");
                    seguir = entrada.nextBoolean();
                }
            } while (seguir == true);
    }
    }

    public void mostrarServicios(ArrayList<Servicios> listaServicios) {
        if(listaServicios.size()==0){
            System.out.println(ANSI_RED + "No hay servicios para mostrar, porfavor ingrese algun servicio." + ANSI_RESET);
        }else{
            for (int i = 0; i < listaServicios.size(); i++) {
                Servicios servicio = listaServicios.get(i);
                System.out.println("Servicio " + (i + 1));
                System.out.println("Nombre del servicio: " + servicio.getNombreServicios());
                System.out.println("Precio del servicio: " + servicio.getPrecios());
                System.out.println("Descripcion del servicio: " + servicio.getDescripcionServicios());
                System.out.println("----------------------------------");
            }
        }
    }   

    public void eliminarServicios(ArrayList<Servicios> listaServicios) {
        if(listaServicios.size()==0){
            System.out.println(ANSI_RED + "No hay servicios para eliminar, porfavor ingrese algun servicio." + ANSI_RESET);
        }else{
            String nombre;
            boolean bandera = false, seguir;
            do {
                seguir=false;
                Scanner entrada = new Scanner(System.in);
                System.out.println("Ingrese el nombre del servicio a eliminar:");
                nombre = entrada.nextLine();
                for (int i = 0; i < listaServicios.size(); i++) {
                    Servicios objServicio = listaServicios.get(i);
                    if (nombre.equalsIgnoreCase(objServicio.getNombreServicios())) {
                        listaServicios.remove(objServicio);
                        System.out.println("Servicio eliminado.\nServicios disponibles: "+ listaServicios.size());
                        bandera = true;
                    }
                }
                if (bandera == false) {
                    System.out.println("El servicio " + nombre + " no se encontro \n¿Desea seguir buscando? (true/false)");
                    seguir = entrada.nextBoolean();
                }
            } while (seguir == true);
        }
    }
}
