package edu.musicrating.dao;

import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.Musica;
import edu.musicrating.entidades.Recomendacao;
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

            String sql = String.join("\n",
                    "SELECT M.id_musica, M.nome_musica, G.id_genero, G.nome_genero, UM.avaliacao",
                    "FROM tb_musica M",
                    "INNER JOIN tb_musica_genero MG ON MG.id_musica = M.id_musica",
                    "INNER JOIN tb_usuario_genero UG ON UG.id_genero = MG.id_genero AND UG.id_usuario = ?",
                    "INNER JOIN tb_genero G on G.id_genero = MG.id_genero",
                    "LEFT OUTER JOIN tb_usuario_musica UM ON UM.id_usuario = UG.id_usuario AND UM.id_musica = M.id_musica",
                    "ORDER BY G.nome_genero, M.nome_musica"
            );

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

                            // Instancia UsuarioMusica
                            UsuarioMusica usuarioMusica = new UsuarioMusica();
                            usuarioMusica.setAvaliacao(rs.getInt("avaliacao"));
                            usuarioMusica.setMusica(musica);
                            usuarioMusica.setUsuario(usuario);

                            lista.add(usuarioMusica);
                        }
                        musica.getGeneros().add(genero);

                    }
                    return lista;
                }
            }
        }
    }

    public static void inserirAvaliacaoMusica(UsuarioMusica usuarioMusica) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "INSERT INTO tb_usuario_musica (id_usuario, id_musica, avaliacao) VALUES (?, ?, ?)";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuarioMusica.getUsuario().getId());
                ps.setInt(2, usuarioMusica.getMusica().getId());
                ps.setInt(3, usuarioMusica.getAvaliacao());

                ps.executeUpdate();
            }
        }
    }

    public static void removerAvaliacaoMusica(UsuarioMusica usuarioMusica) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = "DELETE FROM tb_usuario_musica WHERE id_usuario = ? AND id_musica = ?";

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, usuarioMusica.getUsuario().getId());
                ps.setInt(2, usuarioMusica.getMusica().getId());

                ps.executeUpdate();
            }
        }
    }

    public static List<Recomendacao> listarRecomendacoes(Usuario usuario) throws Exception {
        try ( Connection connection = FabricaDeConexao.obterConexao()) {

            String sql = String.join("\n",
                    // Seleciona todas as musicas da base de dados e calcula a media de avaliacoes
                    "SELECT M.id_musica, M.nome_musica, AVG(UM.avaliacao) AS 'media_avaliacoes', COUNT(UM.avaliacao) 'numero_avaliacoes'",
                    "FROM (",
                    // Seleciona todas as musicas da base de dados que pertençam ao genero preferido do usuario
                    "  SELECT DISTINCT M.id_musica, M.nome_musica",
                    "  FROM tb_musica M",
                    "  INNER JOIN tb_musica_genero MG ON MG.id_musica = M.id_musica",
                    "  INNER JOIN tb_usuario_genero UG ON UG.id_genero = MG.id_genero AND UG.id_usuario = ?",
                    // Para não trazer uma música já avaliada para lista de recomendação
                    "  WHERE M.id_musica NOT IN(",
                    "    SELECT id_musica FROM tb_usuario_musica WHERE id_usuario = ?",
                    "  )",
                    ") M",
                    "LEFT OUTER JOIN tb_usuario_musica UM ON UM.id_musica = M.id_musica",
                    "GROUP BY M.id_musica",
                    "ORDER BY media_avaliacoes DESC"
            );

            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                Integer id = usuario.getId();
                ps.setInt(1, id);
                ps.setInt(2, id);

                try ( ResultSet rs = ps.executeQuery()) {

                    // Criação de lista de recomendações para mapeamento
                    List<Recomendacao> lista = new ArrayList<>();
                    while (rs.next()) {
                        Musica musica = new Musica();
                        musica.setId(rs.getInt("id_musica"));
                        musica.setNome(rs.getString("nome_musica"));

                        Recomendacao recomendacao = new Recomendacao();
                        recomendacao.setMusica(musica);
                        recomendacao.setMediaAvaliacoes(rs.getDouble("media_avaliacoes"));

                        lista.add(recomendacao);
                    }
                    return lista;
                }
            }
        }
    }
}
