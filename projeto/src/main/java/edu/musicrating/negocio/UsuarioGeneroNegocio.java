package edu.musicrating.negocio;

import edu.musicrating.dao.UsuarioGeneroDAO;
import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.entidades.UsuarioGenero;
import edu.musicrating.telas.Controlador;
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
}
