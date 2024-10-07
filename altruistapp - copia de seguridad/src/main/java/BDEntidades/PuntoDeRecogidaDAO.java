/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;
import Entidades.PuntoDeRecogida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuntoDeRecogidaDAO {
    private static final String INSERT_PUNTO_REC_SQL = "INSERT INTO puntoderecogida (nombre_punto) VALUES (?)";
    private static final String SELECT_PUNTO_REC_BY_ID = "SELECT id_puntorecogida, nombre_punto FROM puntoderecogida WHERE id_puntorecogida = ?";
    private static final String SELECT_ALL_PUNTO_REC = "SELECT * FROM puntoderecogida";
    private static final String DELETE_PUNTO_REC_SQL = "DELETE FROM puntoderecogida WHERE id_puntorecogida = ?";
    private static final String UPDATE_PUNTO_REC_SQL = "UPDATE puntoderecogida SET nombre_punto = ? WHERE id_puntorecogida = ?";

    public void insertPuntoDeRecogida(PuntoDeRecogida puntoDeRecogida) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUNTO_REC_SQL)) {
            preparedStatement.setString(1, puntoDeRecogida.getNombrePunto());
            preparedStatement.executeUpdate();
        }
    }

    public PuntoDeRecogida selectPuntoDeRecogida(int id) throws SQLException {
        PuntoDeRecogida puntoDeRecogida = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUNTO_REC_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombrePunto = rs.getString("nombre_punto");
                puntoDeRecogida = new PuntoDeRecogida(id, nombrePunto);
            }
        }
        return puntoDeRecogida;
    }

    public List<PuntoDeRecogida> selectAllPuntoDeRecogida() throws SQLException {
        List<PuntoDeRecogida> puntosDeRecogida = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PUNTO_REC)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_puntorecogida");
                String nombrePunto = rs.getString("nombre_punto");
                puntosDeRecogida.add(new PuntoDeRecogida(id, nombrePunto));
            }
        }
        return puntosDeRecogida;
    }

    public boolean deletePuntoDeRecogida(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUNTO_REC_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePuntoDeRecogida(PuntoDeRecogida puntoDeRecogida) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUNTO_REC_SQL)) {
            statement.setString(1, puntoDeRecogida.getNombrePunto());
            statement.setInt(2, puntoDeRecogida.getIdPuntoRecogida());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}