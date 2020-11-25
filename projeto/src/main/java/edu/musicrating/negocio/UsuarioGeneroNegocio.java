package edu.musicrating.negocio;

import edu.musicrating.dao.UsuarioGeneroDAO;
import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.entidades.UsuarioGenero;
import edu.musicrating.telas.Controlador;
import java.util.Date;
import java.util.List;

public class UsuarioGeneroNegocio {

    private UsuarioGeneroNegocio() {
    }

    public static List<UsuarioGenero> obterGenerosPrerefidos() throws Exception {
        Usuario usuarioAutenticado = Controlador.getUsuarioAutenticado();
        return UsuarioGeneroDAO.listarGenerosPreferidos(usuarioAutenticado);
    }

    public static List<Genero> obterGenerosNaoPreferidos() throws Exception {
        Usuario usuarioAutenticado = Controlador.getUsuarioAutenticado();
        return UsuarioGeneroDAO.listarGenerosNaoPreferidos(usuarioAutenticado);
    }

    public static void inserirGeneroPreferido(Genero genero) throws Exception {
        Usuario usuario = Controlador.getUsuarioAutenticado();

        UsuarioGenero usuarioGenero = new UsuarioGenero();
        usuarioGenero.setUsuario(usuario);
        usuarioGenero.setGenero(genero);
        usuarioGenero.setDataRegistro(new Date());

        UsuarioGeneroDAO.inserirGeneroPreferido(usuarioGenero);
    }

    public static void excluir(UsuarioGenero usuarioGenero) throws Exception {
        UsuarioGeneroDAO.excluirGeneroPreferido(usuarioGenero);
    }
}
