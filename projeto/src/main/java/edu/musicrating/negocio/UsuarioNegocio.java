package edu.musicrating.negocio;

import edu.musicrating.acessodados.UsuarioDAO;
import edu.musicrating.entidades.Usuario;

public class UsuarioNegocio {

    public static void cadastrar(String nome, String email, String login, String senha, String confirmacaoSenha) throws Exception {

        // Validação
        if (nome.trim().isEmpty()) {
            throw new RuntimeException("Nome deve ser informado");
        }

        if (email.trim().isEmpty()) {
            throw new RuntimeException("Email deve ser informado");
        }
        if (!email.contains("@")) {
            throw new RuntimeException("Email invalido");
        }

        if (login.trim().isEmpty()) {
            throw new RuntimeException("Login deve ser informado");
        }

        if (senha.trim().isEmpty()) {
            throw new RuntimeException("Senha deve ser informada");
        }
        if (!senha.equals(confirmacaoSenha)) {
            throw new RuntimeException("Senha de confirmação deve ser igual a senha");
        }

        // Criando usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setPassword(senha);

        // Persistencia        
        UsuarioDAO.inserir(usuario);
    }
}
