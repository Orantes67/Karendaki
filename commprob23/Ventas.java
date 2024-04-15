import java.util.ArrayList;
import java.util.Scanner;

public class Ventas {
    private Citas objetoCitas = null;
    private int totalIngresos;

    public void setObjetoCitas(Citas objetoCitas) {
        this.objetoCitas = objetoCitas;
    }

    public Citas getObjetoCitas() {
        return objetoCitas;
    }

    public void setTotalIngresos(int totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public int getTotalingresoso() {
        return totalIngresos;
    }

    public void confirmarServiciosPago(ArrayList<Citas> listaCitas, ArrayList<Ventas> listaVentas) {
        Scanner entrada = new Scanner(System.in);
        Ventas objVentas = new Ventas();
        String nombreCliente;
        Boolean datoConfirmacion1 = false, datoConfirmacion2 = false;
        System.out.println("Ingresa el nombre del cliente para confirmar la cita y pago.");
        nombreCliente = entrada.nextLine();
        int i = 0;
        for (i = 0; i < listaCitas.size(); i++) {
            Citas objetoCitas = listaCitas.get(i);
            if (nombreCliente.equals(objetoCitas.getNombreCompleto())) {
                System.out.println("Confirmacion de citas\n¿El servicio se realizó correctamente?(true/false)");
                datoConfirmacion1 = entrada.nextBoolean();
                System.out.println("¿El pago se realizo correctamente?(true/false)");
                datoConfirmacion2 = entrada.nextBoolean();
                if (datoConfirmacion1 == true && datoConfirmacion2 == true) {
                    System.out.println("El servicio y el pago se realizo correctamente");
                    objVentas.setObjetoCitas(objetoCitas);
                    listaVentas.add(objVentas);

                    listaCitas.remove(i);
                    System.out.println("Cita eliminada.\nCitas disponibles: " + listaCitas.size());

                } else {
                    System.out.println("Realize el servicio y el pago correctamente");
                    i = 10000;
                }
            } else if (!nombreCliente.equals(objetoCitas.getNombreCompleto()) && i == listaCitas.size() - 1) {
                System.out.println("La cita de " + nombreCliente + " no se encontro");
            }
        }
    }

    public double calcularGanancias(ArrayList<Ventas> listaVentas) {
        double ingresos = 0;
        for (int i = 0; i < listaVentas.size(); i++) {
            Citas cita = listaVentas.get(i).getObjetoCitas();
            Servicios servicio = cita.getObjServicios();

            if (servicio.getPrecios() == 0) {
                System.out.println("El precio del servicio en la venta " + (i + 1) + " está vacío.");
            } else {
                int precioServicio = servicio.getPrecios();
                ingresos += precioServicio;
            }
        }
        return ingresos;
    }
}