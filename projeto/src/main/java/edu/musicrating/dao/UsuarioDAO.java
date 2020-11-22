package edu.musicrating.dao;

import edu.musicrating.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static void inserir(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "INSERT INTO tb_usuario (nome_usuario, email_usuario, login_usuario, senha_usuario) VALUES(?, ?, ?, ?)";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getNome());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getLogin());
                ps.setString(4, usuario.getSenha());

                ps.executeUpdate();
            }
        }
    }

    public static boolean login(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = new StringBuilder()
                    .append("SELECT COUNT(*) FROM tb_usuario")
                    .append(" WHERE (login_usuario LIKE ? OR email_usuario LIKE ?)")
                    .append(" AND senha_usuario LIKE ?")
                    .toString();

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getLogin());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getSenha());

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count == 1) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
    }
}
