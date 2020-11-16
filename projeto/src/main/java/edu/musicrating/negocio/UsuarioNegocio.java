package edu.musicrating.negocio;

import edu.musicrating.acessodados.UsuarioDados;
import edu.musicrating.entidades.Usuario;

public class UsuarioNegocio {

    public static void cadastrar(String login, String senha) throws Exception {
        // Criando usuario
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPassword(senha);

        // Persistencia        
        UsuarioDados.inserir(usuario);
    }
}
