package edu.musicrating.telas;

import edu.musicrating.entidades.Usuario;
import java.awt.Color;
import javax.swing.JFrame;

public class Controlador {

    private static LoginTela loginTela = new LoginTela();

    private static CadastroTela cadastroTela = new CadastroTela();

    private static DashboardTela dashboardTela = new DashboardTela();

    private static GenerosTela generosTela = new GenerosTela();

    private static MusicasTela musicasTela = new MusicasTela();

    private static RecomendacaoTela recomendacaoTela = new RecomendacaoTela();

    private static Usuario usuarioAutenticado;

    private Controlador() {
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public static void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        Controlador.usuarioAutenticado = usuarioAutenticado;
        if (usuarioAutenticado == null) {
            mostrarTelaLogin();
        } else {
            mostrarTelaDashboard();
        }
    }

    public static void mostrarTelaLogin() {
        fecharTela(cadastroTela);
        fecharTela(dashboardTela);
        abrirTela(loginTela);
    }

    public static void mostrarTelaCadastro() {
        fecharTela(loginTela);
        abrirTela(cadastroTela);
    }

    public static void mostrarTelaDashboard() {
        fecharTela(loginTela);
        fecharTela(generosTela);
        fecharTela(musicasTela);
        fecharTela(recomendacaoTela);
        abrirTela(dashboardTela);
    }

    public static void mostrarTelaGeneros() {
        fecharTela(dashboardTela);
        abrirTela(generosTela);
    }

    public static void mostrarTelaAvaliarMusica() {
        fecharTela(dashboardTela);
        abrirTela(musicasTela);
    }

    public static void mostrarTelaRecomendacao() {
        fecharTela(dashboardTela);
        abrirTela(recomendacaoTela);
    }

    private static void abrirTela(JFrame tela) {
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);
        tela.getContentPane().setBackground(Color.WHITE);
        tela.setVisible(true);
    }

    private static void fecharTela(JFrame tela) {
        tela.setVisible(false);
    }
}
