package edu.musicrating.dao;

import edu.musicrating.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe com os comandos SQL relacionados a entidade Usuario.
 */
public class UsuarioDAO {

    private UsuarioDAO() {
    }

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

    public static Usuario login(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = String.join("\n",
                    "SELECT id_usuario, nome_usuario, login_usuario, email_usuario",
                    "FROM tb_usuario",
                    "WHERE (login_usuario LIKE ? OR email_usuario LIKE ?)",
                    "  AND senha_usuario LIKE ?"
            );

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getLogin());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getSenha());

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Usuario usuarioAutenticado = new Usuario();
                        usuarioAutenticado.setId(rs.getInt("id_usuario"));
                        usuarioAutenticado.setNome(rs.getString("nome_usuario"));
                        usuarioAutenticado.setLogin(rs.getString("login_usuario"));
                        usuarioAutenticado.setEmail(rs.getString("email_usuario"));

                        return usuarioAutenticado;
                    }
                    return null;
                }
            }
        }
    }
}
