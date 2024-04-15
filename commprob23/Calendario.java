import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Calendario {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Citas objetoCitas = new Citas();

    public void setObjetoCitas(Citas objetoCitas) {
        this.objetoCitas = objetoCitas;
    }

    public Citas getObjetoCitas() {
        return objetoCitas;
    }

    public void mostrarCalendario(ArrayList<Citas> listaCitas) {
        System.out.println("Calendario de citas:");
        for (int i = 0; i < listaCitas.size(); i++) {
            Citas cita = listaCitas.get(i);
            System.out.println("Cita " + (i + 1));
            System.out.println("Fecha: " + cita.getFecha());
            System.out.println("Hora: " + cita.getHora());
            System.out.println("Cliente: " + cita.getNombreCompleto());
            System.out.println("Servicio: " + cita.getObjServicios().getNombreServicios());

        }
    }

    public void agregarCitas(ArrayList<Servicios> listaServicios, ArrayList<Citas> listaCitas) {
        Scanner entrada = new Scanner(System.in);
        Citas objetoCitas = new Citas();
        Servicios Servicio = new Servicios();
        String nombreCompleto,fecha;
        LocalDate fechaCita = null;
        boolean bandera = true;

        int r1;
        long numeroCelular;

        do {
            System.out.println("Ingresa el nombre del cliente.");

            nombreCompleto = entrada.nextLine();
            for (int i = 0; i < listaCitas.size(); i++) {
                objetoCitas = listaCitas.get(i);

                if (objetoCitas.getNombreCompleto().equals(nombreCompleto)) {
                    System.out.println("una cita ya ha sido guardada con ese nombre, añadele  un apellido.");
                    i = 10000;
                }
            }
        } while (objetoCitas.getNombreCompleto().equals(nombreCompleto));
        getObjetoCitas().setNombreCompleto(nombreCompleto);

        System.out.println("Ingresa el numero de telefono.");
        do {
            numeroCelular = entrada.nextLong();
            entrada.nextLine();
            if (numeroCelular <= 1000000000 && numeroCelular <= 999999999) {
                System.out.println("El número de teléfono debe tener 10 dígitos. Por favor, ingréselo nuevamente:");
            }
        } while (numeroCelular <= 1000000000 && numeroCelular <= 999999999);
        getObjetoCitas().setNumeroCelular(numeroCelular);

        Citas citas;
        int i, hora2;
        boolean bandera3 = false;
     
        DiasEntreSemana objectDES = new DiasEntreSemana("sabado y domingo");
        DiasFinDeSemana objectFES = new DiasFinDeSemana("lunes, martes, miercoles, jueves, viernes");
        Calendar calendario = Calendar.getInstance();

        do {

            do {
                System.out.println("Ingresa la fecha de la cita (dd/MM/yyyy):");
                fecha = entrada.next();

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                     fechaCita = LocalDate.parse(fecha, formatter); 
                    LocalDate fechaActual = LocalDate.now();
                    if (fechaCita.isBefore(fechaActual)) {
                        System.out.println("La fecha de la cita debe ser posterior a la fecha actual.");
                    } else {
                        bandera = false;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(
                            "Formato de fecha inválido. Asegúrate de ingresar la fecha en el formato dd/MM/yyyy.");
                }

            } while (bandera);
            DayOfWeek diaSemana = fechaCita.getDayOfWeek();
            if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
                System.out.println("En fin de semana solo se trabaja de 9 a 15, ingresa otro horario");
                System.out.println("La cita dura una hora, solo ingresa la hora a la que va a iniciar la cita");
                for (i = 0; i < listaCitas.size(); i++) {
                    citas = listaCitas.get(i);
                    if (fecha.equalsIgnoreCase(citas.getFecha())) {
                        System.out.println("hora no disponible: " + citas.getHora());
                    }
                }
                hora2 = entrada.nextInt();
                for (i = 0; i < listaCitas.size(); i++) {
                    citas = listaCitas.get(i);

                    if (objectFES.verificarHora(hora2) == true && hora2 != citas.getHora()
                            && fecha.equalsIgnoreCase(citas.getFecha()) && i == listaCitas.size() - 1) {
                        System.out.println("hora disponible, patata");
                        getObjetoCitas().setHora(hora2);
                        getObjetoCitas().setFecha(fecha);
                        bandera3 = true;
                    }
                    if (hora2 == citas.getHora() && fecha.equalsIgnoreCase(citas.getFecha())) {
                        System.out.println("hora no disponible, escoje otra fecha o un horario disponible");
                        i = 10000;
                    }
                    if (objectFES.verificarHora(hora2) == false && i == listaCitas.size() - 1) {
                        System.out.println(
                                "En fin de semana solo se trabaja de 9 a 15, ingresa otro horario o cambia la fecha");

                    }
                    if (objectFES.verificarHora(hora2) == true && !fecha.equalsIgnoreCase(citas.getFecha())
                            && i == listaCitas.size() - 1) {
                        System.out.println("hora disponible");
                        getObjetoCitas().setHora(hora2);
                        getObjetoCitas().setFecha(fecha);
                        bandera3 = true;
                    }
                }

            } else {
                System.out.println("Entre semana solo se trabaja de 9 a 17, ingresa otro horario");
                System.out.println("La cita dura una hora, solo ingresa la hora a la que va a iniciar la cita");
                for (i = 0; i < listaCitas.size(); i++) {
                    citas = listaCitas.get(i);
                    if (fecha.equalsIgnoreCase(citas.getFecha())) {
                        System.out.println("hora no disponible: " + citas.getHora());
                    }
                }
                hora2 = entrada.nextInt();
                for (i = 0; i < listaCitas.size(); i++) {
                    citas = listaCitas.get(i);

                    if (objectDES.verificarHora(hora2) == true && hora2 != citas.getHora()
                            && fecha.equalsIgnoreCase(citas.getFecha()) && i == listaCitas.size() - 1) {
                        System.out.println("hora disponible, patata");
                        getObjetoCitas().setHora(hora2);
                        getObjetoCitas().setFecha(fecha);
                        bandera3 = true;
                    }
                    if (hora2 == citas.getHora() && fecha.equalsIgnoreCase(citas.getFecha())) {
                        System.out.println("hora no disponible, escoje otra fecha o un horario disponible");
                        i = 10000;
                    }
                    if (objectDES.verificarHora(hora2) == false && i == listaCitas.size() - 1) {
                        System.out.println(
                                "En fin de semana solo se trabaja de 9 a 15, ingresa otro horario o cambia la fecha");

                    }
                    if (objectDES.verificarHora(hora2) == true && !fecha.equalsIgnoreCase(citas.getFecha())
                            && i == listaCitas.size() - 1) {
                        System.out.println("hora disponible");
                        getObjetoCitas().setHora(hora2);
                        getObjetoCitas().setFecha(fecha);
                        bandera3 = true;
                    }
                }

            }
        } while (bandera3 == false);

        System.out.println("Selecciona un servicio, ingrese el numero del servicio.");
        Servicio.mostrarServicios(listaServicios);
        do {
            r1 = entrada.nextInt();
            if (r1 <= 0 || r1 > listaServicios.size())
                System.out.println("ingrese el numero de un servicio existente");
        } while (r1 <= 0 || r1 > listaServicios.size());
        Servicio = listaServicios.get(r1 - 1);
        getObjetoCitas().setObjServicios(Servicio);
    }

    public boolean buscarCitas(ArrayList<Citas> listaCitas) {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        boolean bandera;
        System.out.println("Ingrese el nombre del cliente");
        nombre = entrada.nextLine();
        for (Citas objetoCitas : listaCitas) {
            if (nombre.equals(objetoCitas.getNombreCompleto())) {

                bandera = true;
                return bandera;
            }
        }
        return false;
    }

    public void editarCitas(ArrayList<Citas> listaCitas, ArrayList<Servicios> listaServicios) {
        if (listaCitas.size() == 0) {
            System.out.println(ANSI_RED + "No hay citas para editar, porfavor ingrese una cita." + ANSI_RESET);
        } else {
            int r1, nuevaHora;
            long nuevoNumero;
            String nuevoNombre = "", fecha, nombre;

            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese el nombre del cliente");
            nombre = entrada.nextLine();
            for (Citas objetoCitas : listaCitas) {
                if (nombre.equals(objetoCitas.getNombreCompleto())) {
                    System.out.println(
                            "¿Qué quieres editar?(1 Nombre del cliente/2 Numero Telefonico/3 Fecha y Hora/4 Servicio/5 Salir).");
                    do {
                        r1 = entrada.nextInt();
                        if (r1 < 1 || r1 > 5)
                            System.out.println("Error, ingrese un numero que este entre el 1 o el 6");
                    } while (r1 < 1 || r1 > 5);
                    switch (r1) {
                        case 1:
                            do {
                                System.out.println("Ingresa el nuevo nombre: ");
                                Scanner in = new Scanner(System.in);
                                nuevoNombre = in.nextLine();
                                if (nombre.equals(nuevoNombre)) {
                                    System.out.println("Es el mismo nombre, ingresa uno distinto");

                                }

                            } while (nombre.equals(nuevoNombre));
                            System.out.println("Antiguo nombre: " + objetoCitas.getNombreCompleto());
                            objetoCitas.setNombreCompleto(nuevoNombre);
                            break;
                        case 2:
                            System.out.println("Ingresa el nuevo numero de telefono: ");
                            nuevoNumero = entrada.nextLong();
                            do {
                                if (nuevoNumero <= 1000000000 && nuevoNumero <= 999999999) {
                                    System.out.println(
                                            "El número de teléfono debe tener 10 dígitos. Por favor, ingréselo nuevamente:");
                                    nuevoNumero = entrada.nextLong();
                                    entrada.nextLine();
                                }
                            } while (nuevoNumero <= 1000000000 && nuevoNumero <= 999999999);
                            System.out.println("Antiguo numero de telefono: " + objetoCitas.getNumeroCelular());
                            objetoCitas.setNumeroCelular(nuevoNumero);
                            System.out.println("El nuevo numero es : " + nuevoNumero);
                            break;
                        case 3:
                            boolean bandera = true;
                            LocalDate fechaCita = null;
                            Citas citas = null;
                            int i, hora2;
                            boolean bandera3 = false;
                            DiasEntreSemana objectDES = new DiasEntreSemana("sabado y domingo");
                            DiasFinDeSemana objectFES = new DiasFinDeSemana(
                                 "lunes, martes, miercoles, jueves, viernes");
                            Calendar calendario = Calendar.getInstance();
                            do {

                                do {
                                    System.out.println("Ingresa la fecha de la cita (dd/MM/yyyy):");
                                    fecha = entrada.next();

                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                        fechaCita = LocalDate.parse(fecha, formatter);

                                        LocalDate fechaActual = LocalDate.now();
                                        if (fechaCita.isBefore(fechaActual)) {
                                            System.out.println(
                                                    "La fecha de la cita debe ser posterior a la fecha actual.");
                                        } else {
                                            bandera = false;
                                        }
                                    } catch (DateTimeParseException e) {
                                        System.out.println(
                                                "Formato de fecha inválido. Asegúrate de ingresar la fecha en el formato dd/MM/yyyy.");
                                    }

                                } while (bandera);
                                DayOfWeek diaSemana = fechaCita.getDayOfWeek();
                                if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
                                    System.out.println("En fin de semana solo se trabaja de 9 a 15");
                                    System.out.println(
                                            "La cita dura una hora, solo ingresa la hora a la que va a iniciar la cita");
                                    for (i = 0; i < listaCitas.size(); i++) {
                                        citas = listaCitas.get(i);
                                        if (fecha.equalsIgnoreCase(citas.getFecha())) {
                                            System.out.println("hora no disponible: " + citas.getHora());
                                        }
                                    }
                                    hora2 = entrada.nextInt();
                                    for (i = 0; i < listaCitas.size(); i++) {
                                        citas = listaCitas.get(i);

                                        if (objectFES.verificarHora(hora2) == true && hora2 != citas.getHora()
                                                && fecha.equalsIgnoreCase(citas.getFecha())
                                                && i == listaCitas.size() - 1) {

                                            System.out.println("hora disponible, patata");
                                            objetoCitas.setHora(hora2);
                                            objetoCitas.setFecha(fecha);
                                            bandera3 = true;
                                        }
                                        if (hora2 == citas.getHora() && fecha.equalsIgnoreCase(citas.getFecha())) {
                                            System.out.println(
                                                    "hora no disponible, escoje otra fecha o un horario disponible");
                                            i = 10000;
                                        }
                                        if (objectFES.verificarHora(hora2) == false && i == listaCitas.size() - 1) {
                                            System.out.println(
                                                    "En fin de semana solo se trabaja de 9 a 15, ingresa otro horario o cambia la fecha");

                                        }
                                        if (objectFES.verificarHora(hora2) == true
                                                && !fecha.equalsIgnoreCase(citas.getFecha())
                                                && i == listaCitas.size() - 1) {

                                            System.out.println("hora disponible");
                                            objetoCitas.setHora(hora2);
                                            objetoCitas.setFecha(fecha);
                                            bandera3 = true;
                                        }
                                    }

                                } else {
                                    System.out.println("Entre semana solo se trabaja de 9 a 17");
                                    System.out.println(
                                            "La cita dura una hora, solo ingresa la hora a la que va a iniciar la cita");
                                    for (i = 0; i < listaCitas.size(); i++) {
                                        citas = listaCitas.get(i);
                                        if (fecha.equalsIgnoreCase(citas.getFecha())) {
                                            System.out.println("hora no disponible: " + citas.getHora());
                                        }
                                    }
                                    hora2 = entrada.nextInt();
                                    for (i = 0; i < listaCitas.size(); i++) {
                                        citas = listaCitas.get(i);
                                        if (objectDES.verificarHora(hora2) == true && hora2 != citas.getHora()
                                                && fecha.equalsIgnoreCase(citas.getFecha())
                                                && i == listaCitas.size() - 1) {

                                            System.out.println("hora disponible, patata");
                                            objetoCitas.setHora(hora2);
                                            objetoCitas.setFecha(fecha);
                                            bandera3 = true;
                                        }
                                        if (hora2 == citas.getHora() && fecha.equalsIgnoreCase(citas.getFecha())) {
                                            System.out.println(
                                                    "hora no disponible, escoje otra fecha o un horario disponible");
                                            i = 10000;
                                        }
                                        if (objectDES.verificarHora(hora2) == false && i == listaCitas.size() - 1) {
                                            System.out.println(
                                                    "En fin de semana solo se trabaja de 9 a 15, ingresa otro horario o cambia la fecha");

                                        }
                                        if (objectDES.verificarHora(hora2) == true
                                                && !fecha.equalsIgnoreCase(citas.getFecha())
                                                && i == listaCitas.size() - 1) {

                                            System.out.println("hora disponible");
                                            objetoCitas.setHora(hora2);
                                            objetoCitas.setFecha(fecha);
                                            bandera3 = true;
                                        }
                                    }

                                }
                            } while (bandera3 == false);
                            break;
                        case 4:
                            if (listaServicios.size() == 1) {
                                System.out.println(ANSI_RED + "solo hay un servicio disponible." + ANSI_RESET);
                            } else {
                                System.out.println("Seleccione el nuevo servicio: ");
                                objetoCitas.getObjServicios().mostrarServicios(listaServicios);
                                entrada.nextLine();
                                do {
                                    nuevoNombre = entrada.nextLine();
                                    System.out.println(
                                            nuevoNombre + " = " + objetoCitas.getObjServicios().getNombreServicios());
                                    if (nuevoNombre.equals(objetoCitas.getObjServicios().getNombreServicios())) {
                                        System.out
                                                .println("Es el mismo servicio,porfavor escoja un servicio diferente");
                                    }
                                } while (nuevoNombre.equals(objetoCitas.getObjServicios().getNombreServicios()));

                                System.out.println(
                                        "Antiguo servicio: " + objetoCitas.getObjServicios().getNombreServicios());
                                objetoCitas.setNombreCompleto(nuevoNombre);
                            }
                            break;
                    }
                }
            }
        }
    }

    public void mostrarCitas(ArrayList<Citas> listaCitas) {

        if (listaCitas.size() == 0) {
            System.out.println(ANSI_RED + "No hay citas para mostrar, porfavor ingrese una cita." + ANSI_RESET);
        } else {
            Citas objCitas;
            for (int i = 0; i < listaCitas.size(); i++) {
                objCitas = listaCitas.get(i);
                System.out.println("cita " + (i + 1));
                System.out.println("Cliente : "+ objCitas.getNombreCompleto());
                System.out.println("Numero de celular : "+ objCitas.getNumeroCelular());
                System.out.println("Fecha de la cita : "+ objCitas.getFecha());
                System.out.println("Horario de la cita : "+ objCitas.getHora());
                System.out.println("Servicio : "+ objCitas.getObjServicios().getNombreServicios());
                System.out.println("----------------------------------");
            }
        }

    }

    public void eliminarCitas(ArrayList<Citas> listaCitas) {
        if (listaCitas.size() == 0) {
            System.out.println(ANSI_RED + "No hay citas para eliminar, porfavor ingrese una cita." + ANSI_RESET);
        } else {
            String nombre;
            boolean bandera = false, seguir;
            do {
                seguir = false;
                Scanner entrada = new Scanner(System.in);
                System.out.println("Ingrese el nombre del cliente para eliminar su cita:");
                nombre = entrada.nextLine();
                for (int i = 0; i < listaCitas.size(); i++) {
                    Citas citas = listaCitas.get(i);
                    if (nombre.equalsIgnoreCase(citas.getNombreCompleto())) {
                        listaCitas.remove(i);
                        System.out.println("Cita eliminada. \nCitas disponibles: " + listaCitas.size());
                        bandera = true;
                    }
                }
                if (bandera == false) {
                    System.out
                            .println("La cita de " + nombre + " no se encontro \n¿Desea seguir buscando? (true/false)");
                    seguir = entrada.nextBoolean();
                }
            } while (seguir == true);
        }
    }

}