package BDEntidades;

import Entidades.Donacion;
import Entidades.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidades.Usuario;
import java.sql.DriverManager;


public class DonacionDAO {

    public boolean ofrecerDonacion(Donacion donacion, Articulo articulo) {
        String queryDonacion = "INSERT INTO donacion (id_ofrecedonacion) VALUES (?)";
        String queryArticulo = "INSERT INTO articulo (nombre, estado, id_donacion, id_puntorecogida) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false); // Empezar transacción
            
            try (PreparedStatement stmtDonacion = connection.prepareStatement(queryDonacion, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtDonacion.setInt(1, donacion.getIdOfreceDonacion());
                stmtDonacion.executeUpdate();
                ResultSet generatedKeys = stmtDonacion.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idDonacion = generatedKeys.getInt(1);
                    try (PreparedStatement stmtArticulo = connection.prepareStatement(queryArticulo)) {
                        stmtArticulo.setString(1, articulo.getNombre());
                        stmtArticulo.setString(2, articulo.getEstado());
                        stmtArticulo.setInt(3, idDonacion);
                        stmtArticulo.setInt(4, articulo.getIdPuntoDeRecogida());
                        stmtArticulo.executeUpdate();
                    }
                }
                connection.commit(); // Confirmar transacción
                return true;
            } catch (SQLException e) {
                connection.rollback(); // Deshacer en caso de error
                System.out.println("Error al ofrecer donación: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error en conexión o transacción: " + e.getMessage());
        }
        return false;
    }
    
    public List<Articulo> verArticulosDisponibles() {
        String query = "SELECT * FROM articulo a JOIN donacion d ON a.id_donacion = d.id_donacion WHERE d.estado = 'Pendiente'";
        List<Articulo> listaArticulos = new ArrayList<>();
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(rs.getInt("id_articulo"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setEstado(rs.getString("estado"));
                listaArticulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar artículos: " + e.getMessage());
        }
        return listaArticulos;
    }

    public boolean aceptarDonacion(int idArticulo, int idAceptaDonacion) {
        String query = "UPDATE donacion SET id_aceptadonacion = ?, estado = 'Aceptada', fecha_aceptaciondonacion = CURRENT_TIMESTAMP " +
                       "WHERE id_donacion = (SELECT id_donacion FROM articulo WHERE id_articulo = ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAceptaDonacion);
            stmt.setInt(2, idArticulo);
            return stmt.executeUpdate() > 0; // Retorna true si se actualizó correctamente
        } catch (SQLException e) {
            System.out.println("Error al aceptar donación: " + e.getMessage());
        }
        return false;
    }

    public List<Donacion> verSolicitudesPendientes(int idDonante) {
        List<Donacion> solicitudes = new ArrayList<>();
        String query = "SELECT d.id_donacion, d.estado, a.nombre AS nombre_articulo, u.nombre_usuario " +
                       "FROM donacion d " +
                       "JOIN articulo a ON d.id_donacion = a.id_donacion " +
                       "LEFT JOIN usuario u ON d.id_aceptadonacion = u.id_usuario " + // Cambié a LEFT JOIN para evitar errores
                       "WHERE d.id_ofrecedonacion = ? AND d.estado = 'Pendiente'";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idDonante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Donacion donacion = new Donacion();
                donacion.setIdDonacion(rs.getInt("id_donacion"));
                donacion.setEstado(rs.getString("estado"));

                Articulo articulo = new Articulo();
                articulo.setNombre(rs.getString("nombre_articulo"));
                donacion.setArticulo(articulo);

                Usuario solicitante = new Usuario();
                solicitante.setNombreUsuario(rs.getString("nombre_usuario"));
                donacion.setUsuarioSolicitante(solicitante);

                solicitudes.add(donacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener solicitudes: " + e.getMessage());
        }
        return solicitudes;
    }




    public boolean actualizarEstadoDonacion(int idDonacion, String nuevoEstado) {
        String query = "UPDATE donacion SET estado = ? WHERE id_donacion = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idDonacion);
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado de donación: " + e.getMessage());
            return false;
        }
    }

    public List<Donacion> verSolicitudesRecibidas(int idUsuario) {
        List<Donacion> solicitudesRecibidas = new ArrayList<>();
        String query = "SELECT * FROM donacion WHERE id_aceptadonacion = ?";

    try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Donacion donacion = new Donacion();
            donacion.setIdDonacion(rs.getInt("id_donacion"));
            donacion.setEstado(rs.getString("estado"));
            // Completa la inicialización de la donación según tu estructura de datos
            solicitudesRecibidas.add(donacion);
        }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }

        return solicitudesRecibidas;
    }
    
        public List<Donacion> verSolicitudesHechas(int idUsuario) {
        List<Donacion> solicitudesHechas = new ArrayList<>();
        String query = "SELECT * FROM donacion WHERE id_ofrecedonacion = ?";

    try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Donacion donacion = new Donacion();
            donacion.setIdDonacion(rs.getInt("id_donacion"));
            donacion.setEstado(rs.getString("estado"));
            // Completa la inicialización de la donación según tu estructura de datos
            solicitudesHechas.add(donacion);
        }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }

        return solicitudesHechas;
    
    }

 }
