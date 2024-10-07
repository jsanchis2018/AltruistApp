/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDEntidades;

import Entidades.Solicitudes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudesDAO {

    private static final String INSERT_SOLICITUD_SQL = "INSERT INTO solicitudes (id_usuariorecibe, id_usuariorealiza) VALUES (?, ?)";
    private static final String SELECT_SOLICITUD_BY_ID = "SELECT idsolicitudes, id_usuariorecibe, id_usuariorealiza FROM solicitudes WHERE idsolicitudes = ?";
    private static final String SELECT_ALL_SOLICITUDES = "SELECT * FROM solicitudes";
    private static final String DELETE_SOLICITUD_SQL = "DELETE FROM solicitudes WHERE idsolicitudes = ?";
    private static final String UPDATE_SOLICITUD_SQL = "UPDATE solicitudes SET id_usuariorecibe = ?, id_usuariorealiza = ? WHERE idsolicitudes = ?";

    public void insertSolicitudes(Solicitudes solicitudes) throws SQLException {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SOLICITUD_SQL)) {
            preparedStatement.setInt(1, solicitudes.getIdUsuarioRecibe());
            preparedStatement.setInt(2, solicitudes.getIdUsuarioRealiza());
            preparedStatement.executeUpdate();
        }
    }

    public Solicitudes selectSolicitudes(int id) throws SQLException {
        Solicitudes solicitudes = null;
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOLICITUD_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idUsuarioRecibe = rs.getInt("id_usuariorecibe");
                int idUsuarioRealiza = rs.getInt("id_usuariorealiza");
                solicitudes = new Solicitudes(id, idUsuarioRecibe, idUsuarioRealiza);
            }
        }
        return solicitudes;
    }
}
