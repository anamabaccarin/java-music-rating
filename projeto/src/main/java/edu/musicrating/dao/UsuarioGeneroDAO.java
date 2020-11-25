package edu.musicrating.dao;

import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.entidades.UsuarioGenero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsuarioGeneroDAO {

    private UsuarioGeneroDAO() {
    }

    public static List<Genero> listarGenerosNaoPreferidos(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = new StringBuilder()
                    .append("SELECT G.id_genero, G.nome_genero")
                    .append(" FROM tb_genero G")
                    .append(" WHERE G.id_genero NOT IN (")
                    .append("   SELECT UG.id_genero")
                    .append("   FROM tb_usuario_genero UG")
                    .append("   WHERE UG.id_usuario = ?)")
                    .append(" ORDER BY G.nome_genero")
                    .toString();

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuario.getId());

                try ( ResultSet rs = ps.executeQuery()) {

                    List<Genero> lista = new ArrayList<>();
                    while (rs.next()) {
                        Genero genero = new Genero();
                        genero.setId(rs.getInt("id_genero"));
                        genero.setNome(rs.getString("nome_genero"));
                        lista.add(genero);
                    }
                    return lista;
                }
            }
        }
    }

    public static List<UsuarioGenero> listarGenerosPreferidos(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = new StringBuilder()
                    .append("SELECT UG.id_usuario, UG.data_registro, G.id_genero, G.nome_genero")
                    .append(" FROM tb_usuario_genero UG")
                    .append(" INNER JOIN tb_genero G ON G.id_genero = UG.id_genero")
                    .append(" WHERE UG.id_usuario = ?")
                    .append(" ORDER BY UG.data_registro DESC")
                    .toString();

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuario.getId());

                try ( ResultSet rs = ps.executeQuery()) {

                    List<UsuarioGenero> lista = new ArrayList<>();
                    while (rs.next()) {
                        Genero genero = new Genero();
                        genero.setId(rs.getInt("id_genero"));
                        genero.setNome(rs.getString("nome_genero"));

                        UsuarioGenero usuarioGenero = new UsuarioGenero();
                        usuarioGenero.setDataRegistro(rs.getTimestamp("data_registro"));
                        usuarioGenero.setUsuario(usuario);
                        usuarioGenero.setGenero(genero);

                        lista.add(usuarioGenero);
                    }
                    return lista;
                }
            }
        }
    }

    public static void inserirGeneroPreferido(UsuarioGenero usuarioGenero) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "INSERT INTO tb_usuario_genero(id_usuario, id_genero, data_registro) VALUES (?, ?, ?)";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuarioGenero.getUsuario().getId());
                ps.setInt(2, usuarioGenero.getGenero().getId());
                ps.setTimestamp(3, new Timestamp(usuarioGenero.getDataRegistro().getTime()));

                ps.executeUpdate();
            }
        }
    }

    public static void excluirGeneroPreferido(UsuarioGenero usuarioGenero) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "DELETE FROM tb_usuario_genero WHERE id_usuario = ? AND id_genero= ?";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuarioGenero.getUsuario().getId());
                ps.setInt(2, usuarioGenero.getGenero().getId());

                ps.executeUpdate();
            }
        }
    }
}
