package edu.musicrating.dao;

import edu.musicrating.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    public static void inserir(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "INSERT INTO tb_usuario (nome_usuario, email_usuario, login_usuario, senha_usuario) VALUES(?, ?, ?, ?)";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getNome());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getLogin());
                ps.setString(4, usuario.getPassword());

                ps.executeUpdate();
            }
        }
    }
}
