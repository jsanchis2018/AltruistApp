package BDEntidades;

import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

public Usuario login(String nombreUsuario, String contraseña) {
    String query = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contraseña = ?";
    try (Connection connection = DBConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, nombreUsuario);
        stmt.setString(2, contraseña);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setContraseña(rs.getString("contraseña"));
            return usuario;
        }
    } catch (SQLException e) {
        System.out.println("Error en login: " + e.getMessage());
    }
    return null;
}


    public boolean registrar(Usuario usuario) {
        String query = "INSERT INTO usuario (nombre_usuario, contraseña) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            return stmt.executeUpdate() > 0; // Retorna true si se inserta correctamente
        } catch (SQLException e) {
            System.out.println("Error en registro: " + e.getMessage());
        }
        return false;
    }
}
