package edu.musicrating.acessodados;

import edu.musicrating.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDados {

    public static void inserir(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "INSERT INTO tb_usuario(login_usuario, senha_usuario) VALUES(?, ?)";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getLogin());
                ps.setString(2, usuario.getPassword());

                ps.executeUpdate();
            }
        }
    }
}
