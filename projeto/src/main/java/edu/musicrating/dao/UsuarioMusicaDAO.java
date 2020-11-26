package edu.musicrating.dao;

import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.Musica;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.entidades.UsuarioMusica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioMusicaDAO {

    private UsuarioMusicaDAO() {
    }

    public static List<UsuarioMusica> listarMusicasPreferidas(Usuario usuario) throws Exception {

        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = new StringBuilder()
                    .append("SELECT M.id_musica, M.nome_musica, G.id_genero, G.nome_genero, UM.avaliacao, UM.data_registro")
                    .append(" FROM tb_musica M")
                    .append(" INNER JOIN tb_musica_genero MG ON MG.id_musica = M.id_musica")
                    .append(" INNER JOIN tb_usuario_genero UG ON UG.id_genero = MG.id_genero AND UG.id_usuario = ?")
                    .append(" INNER JOIN tb_genero G on G.id_genero = MG.id_genero")
                    .append(" LEFT OUTER JOIN tb_usuario_musica UM ON UM.id_usuario = UG.id_usuario AND UM.id_musica = M.id_musica")
                    .append(" ORDER BY G.nome_genero, M.nome_musica")
                    .toString();

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuario.getId());

                try ( ResultSet rs = ps.executeQuery()) {

                    List<UsuarioMusica> lista = new ArrayList<>();
                    
                    // Mapa para garantir uma unica instancia de musica que pode conter mais de um genero
                    Map<Integer, Musica> musicasMap = new HashMap<>();

                    while (rs.next()) {
                        // Instancia do genero
                        Genero genero = new Genero();
                        genero.setId(rs.getInt("id_genero"));
                        genero.setNome(rs.getString("nome_genero"));

                        // Instancia da musica
                        Integer idMusica = rs.getInt("id_musica");
                        Musica musica;

                        if (musicasMap.containsKey(idMusica)) {
                            musica = musicasMap.get(idMusica);
                        } else {
                            musica = new Musica();
                            musica.setId(idMusica);
                            musica.setNome(rs.getString("nome_musica"));
                            musica.setGeneros(new ArrayList());
                            
                            musicasMap.put(idMusica, musica);
                        }
                        musica.getGeneros().add(genero);

                        // Instancia UsuarioMusica
                        UsuarioMusica usuarioMusica = new UsuarioMusica();
                        usuarioMusica.setAvaliacao(rs.getInt("avaliacao"));
                        usuarioMusica.setDataRegistro(rs.getTimestamp("data_registro"));
                        usuarioMusica.setMusica(musica);
                        usuarioMusica.setUsuario(usuario);
                    }
                    return lista;
                }
            }
        }
    }
}
