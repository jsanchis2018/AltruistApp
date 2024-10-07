package Entidades;
import BDEntidades.UsuarioDAO;
import BDEntidades.DonacionDAO;
import java.util.List;
import java.util.Scanner;

public class Menus {
    private final Scanner scanner;
    private final UsuarioDAO usuarioDAO;
    private final DonacionDAO donacionDAO;
    private Usuario usuarioLogueado;

    public Menus (Scanner scanner) {
        this.scanner = scanner;
        this.usuarioDAO = new UsuarioDAO();
        this.donacionDAO = new DonacionDAO();
        this.usuarioLogueado = null;
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
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarse();
                break;
            case 3:
                System.exit(0);
            default:
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
                case 1 -> ofrecerDonacion();
                case 2 -> aceptarDonacion();
                case 3 -> revisarSolicitudes();
                case 4 -> {
                    usuarioLogueado = null;
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void revisarSolicitudes() {
   
        System.out.println("\n---- REVISAR SOLICITUDES ----");
        System.out.println("1. Solicitudes realizadas");
        System.out.println("2. Solicitudes recibidas");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1 -> mostrarSolicitudesHechas();
            case 2 -> mostrarSolicitudesRecibidas();
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }


    private void mostrarSolicitudesRecibidas() {
        List<Donacion> solicitudesRecibidas = donacionDAO.verSolicitudesRecibidas(usuarioLogueado.getIdUsuario());
        if (solicitudesRecibidas.isEmpty()) {
            System.out.println("Sin solicitudes recibidas.");
        } else {
            for (Donacion donacion : solicitudesRecibidas) {
                System.out.println("ID Donación: " + donacion.getIdDonacion() + "Artículo: " + donacion.getArticulo().getNombre() + ", Estado: " + donacion.getEstado());
            }
            int idDonacion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Donacion donacion = solicitudesRecibidas.stream()
                    .filter(d -> d.getIdDonacion() == idDonacion)
                    .findFirst()
                    .orElse(null);

            if (donacion != null) {
                aceptarSolicitud(donacion);
            } else {
                System.out.println("ID de donación no encontrado.");
            }
        }
    }

    private void aceptarSolicitud(Donacion donacion) {
        try {
            donacion.aceptarSolicitud();
            System.out.println("Solicitud aceptada con éxito.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ofrecerDonacion() {
        System.out.println("\n---- OFRECER DONACIÓN ----");
        System.out.print("Nombre del artículo: ");
        String nombreArticulo = scanner.nextLine();
        System.out.print("Estado del artículo (Nuevo, Usado): ");
        String estadoArticulo = scanner.nextLine();

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

        if (donacionDAO.ofrecerDonacion(donacion, articulo)) {
            System.out.println("Donación registrada exitosamente.");
        } else {
            System.out.println("Error al registrar la donación.");
        }
    }

    private void aceptarDonacion() {
        System.out.println("\n---- ACEPTAR DONACIÓN ----");
        List<Articulo> articulos = donacionDAO.verArticulosDisponibles();
        if (articulos.isEmpty()) {
            System.out.println("No hay donaciones disponibles.");
            return;
        }

        for (int i = 0; i < articulos.size(); i++) {
            System.out.println((i + 1) + ". " + articulos.get(i).getNombre());
        }
        System.out.print("Selecciona un artículo: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion > 0 && seleccion <= articulos.size()) {
            Articulo articuloSeleccionado = articulos.get(seleccion - 1);
            System.out.println("Artículo seleccionado: " + articuloSeleccionado.getNombre());
            System.out.println("1. Solicitar donación");
            System.out.println("2. Volver al menú anterior");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                if (donacionDAO.aceptarDonacion(articuloSeleccionado.getIdArticulo(), usuarioLogueado.getIdUsuario())) {
                    System.out.println("Donación solicitada exitosamente.");
                } else {
                    System.out.println("Error al solicitar la donación.");
                }
            }
        } else {
            System.out.println("Selección no válida.");
        }
    }

    public void mostrarSolicitudesHechas() {
        System.out.println("\n---- SOLICITUDES PENDIENTES ----");
        List<Donacion> solicitudes = donacionDAO.verSolicitudesPendientes(usuarioLogueado.getIdUsuario());

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
        } else {
            for (Donacion donacion : solicitudes) {
                System.out.println("ID Donación: " + donacion.getIdDonacion());
                System.out.println("Estado: " + donacion.getEstado());
                System.out.println("Artículo: " + donacion.getArticulo().getNombre());
                System.out.println("Solicitado por: " + donacion.getUsuarioSolicitante().getNombreUsuario());
                System.out.println("-------------------------");
            }
        }
    }
}
