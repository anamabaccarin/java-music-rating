package edu.musicrating.negocio;

import edu.musicrating.dao.UsuarioMusicaDAO;
import edu.musicrating.entidades.Musica;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.entidades.UsuarioMusica;
import edu.musicrating.telas.Controlador;
import java.util.List;

public class UsuarioMusicaNegocio {

    private UsuarioMusicaNegocio() {
    }

    /**
     * Lista todas as musicas pertencentes aos generos musicais preferidos do
     * usuario.
     */
    public static List<UsuarioMusica> listarMusicas() throws Exception {
        Usuario usuario = Controlador.getUsuarioAutenticado();

        return UsuarioMusicaDAO.listarMusicasPreferidas(usuario);
    }

    public static void inserirAvaliacaoMusica(Musica musica, Integer avaliacao) throws Exception {
        Usuario usuario = Controlador.getUsuarioAutenticado();

        UsuarioMusica usuarioMusica = new UsuarioMusica();
        usuarioMusica.setUsuario(usuario);
        usuarioMusica.setMusica(musica);
        usuarioMusica.setAvaliacao(avaliacao);

        UsuarioMusicaDAO.inserirAvaliacaoMusica(usuarioMusica);
    }

    public static void removerAvaliacaoMusica(Musica musica) throws Exception {
        Usuario usuario = Controlador.getUsuarioAutenticado();

        UsuarioMusica usuarioMusica = new UsuarioMusica();
        usuarioMusica.setUsuario(usuario);
        usuarioMusica.setMusica(musica);

        UsuarioMusicaDAO.removerAvaliacaoMusica(usuarioMusica);
    }
}
