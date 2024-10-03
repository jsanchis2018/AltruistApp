package com.thecorner.altruistapp;
import BDEntidades.UsuarioDAO;
import BDEntidades.DonacionDAO;
import Entidades.Usuario;
import Entidades.Articulo;
import Entidades.Donacion;
import java.util.Scanner;
import java.util.List;

public class Menus {

    private Scanner scanner;
    private UsuarioDAO usuarioDAO;
    private DonacionDAO donacionDAO;
    private Usuario usuarioLogueado;

    public Menus (Scanner scanner) {
        this.scanner = scanner;
        this.usuarioDAO = new UsuarioDAO();
        this.donacionDAO = new DonacionDAO();
        this.usuarioLogueado = null;
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n---- IDENTIFICAR USUARIO ----");
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
            System.out.println("1. Ofrecer donación");
            System.out.println("2. Aceptar donación");
            System.out.println("3. Revisar solicitudes");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ofrecerDonacion();
                    break;
                case 2:
                    aceptarDonacion();
                    break;
                case 3:
                    revisarSolicitudes();
                    break;
                case 4:
                    usuarioLogueado = null;
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void revisarSolicitudes() {
        System.out.println("\n---- REVISAR SOLICITUDES ----");
        List<Donacion> solicitudes = donacionDAO.verSolicitudesPendientes(usuarioLogueado.getIdUsuario());

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        for (int i = 0; i < solicitudes.size(); i++) {
            Donacion solicitud = solicitudes.get(i);
            System.out.println((i + 1) + ". Artículo: " + solicitud.getArticulo().getNombre() 
                               + " | Solicitado por: " + solicitud.getUsuarioSolicitante().getNombreUsuario());
        }
        System.out.print("Selecciona una solicitud para gestionar (o 0 para volver): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion > 0 && seleccion <= solicitudes.size()) {
            Donacion donacionSeleccionada = solicitudes.get(seleccion - 1);
            System.out.println("Artículo seleccionado: " + donacionSeleccionada.getArticulo().getNombre());
            System.out.println("1. Aceptar donación");
            System.out.println("2. Rechazar donación");
            System.out.println("3. Volver al menú anterior");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (donacionDAO.actualizarEstadoDonacion(donacionSeleccionada.getIdDonacion(), "Aceptada")) {
                        System.out.println("Donación aceptada.");
                    } else {
                        System.out.println("Error al aceptar la donación.");
                    }
                    break;
                case 2:
                    if (donacionDAO.actualizarEstadoDonacion(donacionSeleccionada.getIdDonacion(), "Rechazada")) {
                        System.out.println("Donación rechazada.");
                    } else {
                        System.out.println("Error al rechazar la donación.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } else if (seleccion == 0) {
            return;
        } else {
            System.out.println("Selección no válida.");
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

    public void mostrarSolicitudesPendientes() {
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