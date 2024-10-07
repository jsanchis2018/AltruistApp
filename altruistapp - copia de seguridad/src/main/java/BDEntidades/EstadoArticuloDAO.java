/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;
import Entidades.EstadoArticulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoArticuloDAO {
    private static final String INSERT_ESTADO_ARTICULO_SQL = "INSERT INTO estado_articulo (id_estadoarticulo, estado) VALUES (?, ?)";
    private static final String SELECT_ESTADO_ARTICULO_BY_ID = "SELECT idestado_articulo, id_estadoarticulo, estado FROM estado_articulo WHERE idestado_articulo = ?";
    private static final String SELECT_ALL_ESTADO_ARTICULOS = "SELECT * FROM estado_articulo";
    private static final String DELETE_ESTADO_ARTICULO_SQL = "DELETE FROM estado_articulo WHERE idestado_articulo = ?";
    private static final String UPDATE_ESTADO_ARTICULO_SQL = "UPDATE estado_articulo SET id_estadoarticulo = ?, estado = ? WHERE idestado_articulo = ?";

    public void insertEstadoArticulo(EstadoArticulo estadoArticulo) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTADO_ARTICULO_SQL)) {
            preparedStatement.setInt(1, estadoArticulo.getIdEstadoArticuloRef());
            preparedStatement.setString(2, estadoArticulo.getEstado());
            preparedStatement.executeUpdate();
        }
    }

    public EstadoArticulo selectEstadoArticulo(int id) throws SQLException {
        EstadoArticulo estadoArticulo = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ESTADO_ARTICULO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idEstadoArticuloRef = rs.getInt("id_estadoarticulo");
                String estado = rs.getString("estado");
                estadoArticulo = new EstadoArticulo(id, idEstadoArticuloRef, estado);
            }
        }
        return estadoArticulo;
    }

    public List<EstadoArticulo> selectAllEstadoArticulos() throws SQLException {
        List<EstadoArticulo> estadoArticulos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ESTADO_ARTICULOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idestado_articulo");
                int idEstadoArticuloRef = rs.getInt("id_estadoarticulo");
                String estado = rs.getString("estado");
                estadoArticulos.add(new EstadoArticulo(id, idEstadoArticuloRef, estado));
            }
        }
        return estadoArticulos;
    }

    public boolean deleteEstadoArticulo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ESTADO_ARTICULO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEstadoArticulo(EstadoArticulo estadoArticulo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ESTADO_ARTICULO_SQL)) {
            statement.setInt(1, estadoArticulo.getIdEstadoArticuloRef());
            statement.setString(2, estadoArticulo.getEstado());
            statement.setInt(3, estadoArticulo.getIdEstadoArticulo());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}