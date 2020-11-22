package edu.musicrating.telas;

import java.awt.Color;
import javax.swing.JFrame;

public class Controlador {

    private static LoginTela telaLogin = new LoginTela();

    private static CadastroTela telaCadastro = new CadastroTela();

    private Controlador() {
    }

    public static void mostrarTelaLogin() {
        fecharTela(telaCadastro);
        abrirTela(telaLogin);
    }

    public static void mostrarTelaCadastro() {
        fecharTela(telaLogin);
        abrirTela(telaCadastro);
    }

    public static void mostrarTelaDashboard() {

    }

    public static void mostrarTelaEscolherGenero() {

    }

    public static void mostrarTelaAvaliarMusica() {

    }

    public static void mostrarTelaReceberRecomendacao() {

    }

    private static void abrirTela(JFrame tela) {
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        tela.setResizable(false);
        tela.getContentPane().setBackground(Color.WHITE);
    }

    private static void fecharTela(JFrame tela) {
        tela.setVisible(false);
    }
}
