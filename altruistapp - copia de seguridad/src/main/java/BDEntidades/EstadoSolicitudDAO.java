/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;
import Entidades.EstadoSolicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoSolicitudDAO {
    private static final String INSERT_ESTADO_SOLICITUD_SQL = "INSERT INTO estado_solicitud (pendiente, aceptada, id_estadosolicitud) VALUES (?, ?, ?)";
    private static final String SELECT_ESTADO_SOLICITUD_BY_ID = "SELECT idestado_solicitud, pendiente, aceptada, id_estadosolicitud FROM estado_solicitud WHERE idestado_solicitud = ?";
    private static final String SELECT_ALL_ESTADO_SOLICITUDES = "SELECT * FROM estado_solicitud";
    private static final String DELETE_ESTADO_SOLICITUD_SQL = "DELETE FROM estado_solicitud WHERE idestado_solicitud = ?";
    private static final String UPDATE_ESTADO_SOLICITUD_SQL = "UPDATE estado_solicitud SET pendiente = ?, aceptada = ?, id_estadosolicitud = ? WHERE idestado_solicitud = ?";

    public void insertEstadoSolicitud(EstadoSolicitud estadoSolicitud) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTADO_SOLICITUD_SQL)) {
            preparedStatement.setString(1, estadoSolicitud.getPendiente());
            preparedStatement.setString(2, estadoSolicitud.getAceptada());
            preparedStatement.setInt(3, estadoSolicitud.getIdEstadoSolicitudRef());
            preparedStatement.executeUpdate();
        }
    }

    public EstadoSolicitud selectEstadoSolicitud(int id) throws SQLException {
        EstadoSolicitud estadoSolicitud = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ESTADO_SOLICITUD_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String pendiente = rs.getString("pendiente");
                String aceptada = rs.getString("aceptada");
                int idEstadoSolicitudRef = rs.getInt("id_estadosolicitud");
                estadoSolicitud = new EstadoSolicitud(id, pendiente, aceptada, idEstadoSolicitudRef);
            }
        }
        return estadoSolicitud;
    }

    public List<EstadoSolicitud> selectAllEstadoSolicitudes() throws SQLException {
        List<EstadoSolicitud> estadoSolicitudes = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ESTADO_SOLICITUDES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idestado_solicitud");
                String pendiente = rs.getString("pendiente");
                String aceptada = rs.getString("aceptada");
                int idEstadoSolicitudRef = rs.getInt("id_estadosolicitud");
                estadoSolicitudes.add(new EstadoSolicitud(id, pendiente, aceptada, idEstadoSolicitudRef));
            }
        }
        return estadoSolicitudes;
    }

    public boolean deleteEstadoSolicitud(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ESTADO_SOLICITUD_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEstadoSolicitud(EstadoSolicitud estadoSolicitud) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ESTADO_SOLICITUD_SQL)) {
            statement.setString(1, estadoSolicitud.getPendiente());
            statement.setString(2, estadoSolicitud.getAceptada());
            statement.setInt(3, estadoSolicitud.getIdEstadoSolicitudRef());
            statement.setInt(4, estadoSolicitud.getIdEstadoSolicitud());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}