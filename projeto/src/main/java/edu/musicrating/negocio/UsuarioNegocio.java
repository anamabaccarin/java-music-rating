package edu.musicrating.negocio;

import edu.musicrating.dao.UsuarioDAO;
import edu.musicrating.entidades.Usuario;

public class UsuarioNegocio {

    private UsuarioNegocio() {
    }

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

        // Criando objeto usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        // Persistencia        
        UsuarioDAO.inserir(usuario);
    }

    public static void login(String loginOuEmail, String senha) throws Exception {
        // Validação
        if (loginOuEmail.trim().isEmpty()) {
            throw new RuntimeException("Login/email deve ser informado");
        }
        if (senha.trim().isEmpty()) {
            throw new RuntimeException("Senha deve ser informada");
        }

        // Criando objeto usuario
        Usuario usuario = new Usuario();
        usuario.setLogin(loginOuEmail);
        usuario.setEmail(loginOuEmail);
        usuario.setSenha(senha);

        // Consulta banco de dados
        boolean sucesso = UsuarioDAO.login(usuario);
        if (!sucesso) {
            throw new RuntimeException("Login/email ou senha invalidos");
        }
    }
}
