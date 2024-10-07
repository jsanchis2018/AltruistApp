/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;

import Entidades.EstadoDonacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDonacionDAO {
    private static final String INSERT_ESTADO_DONACION_SQL = "INSERT INTO estado_donacion (disponible, reservado, donado, id_estadodonacion) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ESTADO_DONACION_BY_ID = "SELECT idestado_donacion, disponible, reservado, donado, id_estadodonacion FROM estado_donacion WHERE idestado_donacion = ?";
    private static final String SELECT_ALL_ESTADO_DONACIONES = "SELECT * FROM estado_donacion";
    private static final String DELETE_ESTADO_DONACION_SQL = "DELETE FROM estado_donacion WHERE idestado_donacion = ?";
    private static final String UPDATE_ESTADO_DONACION_SQL = "UPDATE estado_donacion SET disponible = ?, reservado = ?, donado = ?, id_estadodonacion = ? WHERE idestado_donacion = ?";

    public void insertEstadoDonacion(EstadoDonacion estadoDonacion) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTADO_DONACION_SQL)) {
            preparedStatement.setString(1, estadoDonacion.getDisponible());
            preparedStatement.setString(2, estadoDonacion.getReservado());
            preparedStatement.setString(3, estadoDonacion.getDonado());
            preparedStatement.setInt(4, estadoDonacion.getIdEstadoDonacionRef());
            preparedStatement.executeUpdate();
        }
    }

    public EstadoDonacion selectEstadoDonacion(int id) throws SQLException {
        EstadoDonacion estadoDonacion = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ESTADO_DONACION_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String disponible = rs.getString("disponible");
                String reservado = rs.getString("reservado");
                String donado = rs.getString("donado");
                int idEstadoDonacionRef = rs.getInt("id_estadodonacion");
                estadoDonacion = new EstadoDonacion(id, disponible, reservado, donado, idEstadoDonacionRef);
            }
        }
        return estadoDonacion;
    }

    public List<EstadoDonacion> selectAllEstadoDonaciones() throws SQLException {
        List<EstadoDonacion> estadoDonaciones = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ESTADO_DONACIONES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idestado_donacion");
                String disponible = rs.getString("disponible");
                String reservado = rs.getString("reservado");
                String donado = rs.getString("donado");
                int idEstadoDonacionRef = rs.getInt("id_estadodonacion");
                estadoDonaciones.add(new EstadoDonacion(id, disponible, reservado, donado, idEstadoDonacionRef));
            }
        }
        return estadoDonaciones;
    }

    public boolean deleteEstadoDonacion(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ESTADO_DONACION_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEstadoDonacion(EstadoDonacion estadoDonacion) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ESTADO_DONACION_SQL)) {
            statement.setString(1, estadoDonacion.getDisponible());
            statement.setString(2, estadoDonacion.getReservado());
            statement.setString(3, estadoDonacion.getDonado());
            statement.setInt(4, estadoDonacion.getIdEstadoDonacionRef());
            statement.setInt(5, estadoDonacion.getIdEstadoDonacion());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}