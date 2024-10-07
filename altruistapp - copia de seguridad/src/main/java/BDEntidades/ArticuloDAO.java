/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;
import Entidades.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO {
    private static final String INSERT_ARTICULO_SQL = "INSERT INTO articulo (nombre, id_donacion, id_puntorecogida) VALUES (?, ?, ?)";
    private static final String SELECT_ARTICULO_BY_ID = "SELECT id_articulo, nombre, id_donacion, id_puntorecogida FROM articulo WHERE id_articulo = ?";
    private static final String SELECT_ALL_ARTICULOS = "SELECT * FROM articulo";
    private static final String DELETE_ARTICULO_SQL = "DELETE FROM articulo WHERE id_articulo = ?";
    private static final String UPDATE_ARTICULO_SQL = "UPDATE articulo SET nombre = ?, id_donacion = ?, id_puntorecogida = ? WHERE id_articulo = ?";

    public void insertArticulo(Articulo articulo) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICULO_SQL)) {
            preparedStatement.setString(1, articulo.getNombre());
            preparedStatement.setInt(2, articulo.getIdDonacion());
            preparedStatement.setInt(3, articulo.getIdPuntoRecogida());
            preparedStatement.executeUpdate();
        }
    }

    public Articulo selectArticulo(int id) throws SQLException {
        Articulo articulo = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICULO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int idDonacion = rs.getInt("id_donacion");
                int idPuntoRecogida = rs.getInt("id_puntorecogida");
                articulo = new Articulo(id, nombre, idDonacion, idPuntoRecogida);
            }
        }
        return articulo;
    }

    public List<Articulo> selectAllArticulos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICULOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_articulo");
                String nombre = rs.getString("nombre");
                int idDonacion = rs.getInt("id_donacion");
                int idPuntoRecogida = rs.getInt("id_puntorecogida");
                articulos.add(new Articulo(id, nombre, idDonacion, idPuntoRecogida));
            }
        }
        return articulos;
    }

    public boolean deleteArticulo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICULO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateArticulo(Articulo articulo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICULO_SQL)) {
            statement.setString(1, articulo.getNombre());
            statement.setInt(2, articulo.getIdDonacion());
            statement.setInt(3, articulo.getIdPuntoRecogida());
            statement.setInt(4, articulo.getIdArticulo());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}