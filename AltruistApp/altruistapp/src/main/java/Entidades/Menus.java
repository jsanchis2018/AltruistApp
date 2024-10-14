package Entidades;

import BDEntidades.UsuarioDAO;
import BDEntidades.DonacionDAO;
import static Entidades.Donacion.estadoDonado;
import static Entidades.Donacion.estadoReservado;
import static Entidades.Donacion.estadoSolicitado;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menus {

    private final Scanner scanner;
    private final UsuarioDAO usuarioDAO;
    private final DonacionDAO donacionDAO;
    private Usuario usuarioLogueado;
    private Object estado;
    private Date fechaAceptacionDonacion;

    public Menus(Scanner scanner) {
        this.scanner = scanner;
        this.usuarioDAO = new UsuarioDAO();
        this.donacionDAO = new DonacionDAO();

    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n---- BIENVENIDO ALTRUISTER ----");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 ->
                iniciarSesion();
            case 2 ->
                registrarse();
            case 3 ->
                System.exit(0);
            default ->
                System.out.println("Opción no válida.");
        }
    }

    private void iniciarSesion() {
        System.out.println("\n---- INICIAR SESIÓN ----");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioLogueado = usuarioDAO.login(usuario, contraseña);
        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso.");
            mostrarMenuUsuario();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void registrarse() {
        System.out.println("\n---- REGISTRARSE ----");
        System.out.print("Nuevo usuario: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Nueva contraseña: ");
        String nuevaContraseña = scanner.nextLine();

        Usuario nuevo = new Usuario();
        nuevo.setNombreUsuario(nuevoUsuario);
        nuevo.setContraseña(nuevaContraseña);

        if (usuarioDAO.registrar(nuevo)) {
            System.out.println("Registro exitoso.");
        } else {
            System.out.println("Error al registrar usuario.");
        }
    }

    private void mostrarMenuUsuario() {
        while (true) {
            System.out.println("\n---- MENÚ PRINCIPAL ----");
            System.out.println("1. Ofrecer donación altruista");
            System.out.println("2. Ver lo que donan otros Altruisters");
            System.out.println("3. Solicitudes");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 ->
                    ofrecerDonacion();
                case 2 ->
                    aceptarDonacion();
                case 3 ->
                    revisarSolicitudes();
                case 4 -> {
                    usuarioLogueado = null;
                    return;
                }
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void aceptarSolicitud() {
        if (estadoSolicitado.equals(this.estado)) {
            this.estado = estadoReservado;
            this.fechaAceptacionDonacion = new Date(); // Fecha de aceptación
        } else {
            throw new IllegalStateException("La donación no está solicitada.");
        }
    }

    // Método para marcar la donación como depositada
    public void marcarComoDepositada() {
        if (estadoReservado.equals(this.estado)) {
            this.estado = estadoDonado;
        } else {
            throw new IllegalStateException("La donación aún no está realizada.");
        }
    }

    public String obtenerEstado() {
        return (String) this.estado;
    }

    private void revisarSolicitudes() {
        System.out.println("\n---- REVISAR SOLICITUDES ----");
        System.out.println("1. Donado por ti");
        System.out.println("2. Donaciones solicitadas");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1 ->
                mostrarDonacionesHechas();
            case 2 ->
                mostrarSolicitudesRealizadas();
            default ->
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void mostrarDonacionesHechas() { //muestra todas las donaciones que el usuario ha ofrecido
        List<Donacion> solicitudesHechas = donacionDAO.mostrarDonacionesHechas(usuarioLogueado.getIdUsuario());
        if (solicitudesHechas.isEmpty()) {
            System.out.println("Sin solicitudes hechas.");
        } else {
            for (Donacion donacion : solicitudesHechas) {
                // Verificamos que el artículo no sea null
                Articulo articulo = donacion.getArticulo();
                String nombreArticulo = (articulo != null) ? articulo.getNombre() : "Artículo no disponible";
                String estadoDonado = donacion.getEstado().equals(Donacion.estadoDonado) ? "Donado" : "En espera";
                System.out.println("ID Donación: " + donacion.getIdDonacion() + ", Artículo: " + nombreArticulo /*+ ", Estado: " + estadoDonado)*/);
            }
        }
    }

    private void mostrarSolicitudesRealizadas() { //muestra las solicitudes realizadas, solo pueden estar en espera o aceptadas 
        List<Donacion> solicitudesRecibidas = donacionDAO.mostrarSolicitudesRealizadas(usuarioLogueado.getIdUsuario());
        if (solicitudesRecibidas.isEmpty()) {
            System.out.println("Sin donaciones solicitadas.");
        } else {

            // verificar este codigo de solicitudes
            for (Donacion donacion : solicitudesRecibidas) {
                Articulo articulo = donacion.getArticulo();
                var nombreArticulo = (articulo != null) ? articulo.getNombre() : "Artículo no disponible";
                String estadoDonado = donacion.getEstado().equals(Donacion.estadoDonado) ? "Donado" : "Aceptado";
                System.out.println("ID Donación: " + donacion.getIdDonacion() + ", Artículo: " + nombreArticulo + ", Estado: " + estadoDonado);

            }
        }
    }

    private void ofrecerDonacion() {
        System.out.println("\n---- OFRECER DONACIÓN ----");
        System.out.print("Nombre del artículo: ");
        String nombreArticulo = scanner.nextLine();

        //Articulo
        System.out.print("Estado del artículo: ");
        System.out.println("");
        System.out.println("1. Nuevo");
        System.out.println("2. Usado");
        System.out.println("3. Para reparar"); // Opción adicional
        System.out.print("Selecciona una opción: ");
        int option_user = scanner.nextInt();
        String estadoArticulo;

        switch (option_user) {
            case 1:
                estadoArticulo = "Nuevo";
                break;
            case 2:
                estadoArticulo = "Usado";
                break;
            case 3:
                estadoArticulo = "Para reparar";
                break;
            default:
                estadoArticulo = "Opción no válida";
                break;
        }

        System.out.println("Establece punto de recogida:");
        System.out.println("1. Locutorio");
        System.out.println("2. Estanco");
        System.out.println("3. Centro cultural");
        System.out.println("4. Nuestro punto Altruistapp");
        System.out.print("Selecciona una opción: ");
        int puntoRecogida = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = new Articulo();
        articulo.setNombre(nombreArticulo);
        articulo.setEstado(estadoArticulo);
        articulo.setIdPuntoDeRecogida(puntoRecogida);

        Donacion donacion = new Donacion();
        donacion.setIdOfreceDonacion(usuarioLogueado.getIdUsuario());
        donacion.setArticulo(articulo); // Asignamos el artículo a la donación

        if (donacionDAO.ofrecerDonacion(donacion, articulo)) {
            System.out.println("Donación registrada exitosamente.");
        } else {
            System.out.println("Error al registrar la donación.");
        }
    }

    private void aceptarDonacion() {
        while (true) { // Usar un bucle para permitir múltiples selecciones
            System.out.println("\n---- ARTICULOS DONADOS ----");
            List<Articulo> articulos = donacionDAO.verArticulosDisponibles();
            if (articulos.isEmpty()) {
                System.out.println("No hay donativos disponibles.");
                return;
            }

            for (int i = 0; i < articulos.size(); i++) {
                System.out.println((i + 1) + ". " + articulos.get(i).getNombre());
            }
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion == 0) {
                return; // Salir del método
            } else if (seleccion > 0 && seleccion <= articulos.size()) {
                Articulo articuloSeleccionado = articulos.get(seleccion - 1);
                System.out.println("Artículo seleccionado: " + articuloSeleccionado.getNombre());
                System.out.println("1. Solicitar donación");
                System.out.println("2. Volver al menú anterior");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> {
                        if (donacionDAO.aceptarDonacion(articuloSeleccionado.getIdArticulo(), usuarioLogueado.getIdUsuario())) {
                            System.out.println("Donación SOLICITADA exitosamente.");
                        } else {
                            System.out.println("Error al solicitar la donación.");
                        }
                    }
                    case 2 -> {
                        // Vuelve al inicio del bucle para mostrar la lista de artículos nuevamente
                        continue; // Esto ahora funciona porque estamos dentro de un bucle
                    }
                    default ->
                        System.out.println("Selección no válida.");
                }
            } else {
                System.out.println("Selección no válida.");
            }
        }
    }
}
