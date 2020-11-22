package edu.musicrating.telas;

import java.awt.Color;
import javax.swing.JFrame;

public class Controlador {

    private static LoginTela loginTela = new LoginTela();

    private static CadastroTela cadastroTela = new CadastroTela();

    private static DashboardTela dashboardTela = new DashboardTela();

    private Controlador() {
    }

    public static void mostrarTelaLogin() {
        fecharTela(cadastroTela);
        abrirTela(loginTela);
    }

    public static void mostrarTelaCadastro() {
        fecharTela(loginTela);
        abrirTela(cadastroTela);
    }

    public static void mostrarTelaDashboard() {
        fecharTela(loginTela);
        abrirTela(dashboardTela);
    }

    public static void mostrarTelaEscolherGenero() {

    }

    public static void mostrarTelaAvaliarMusica() {

    }

    public static void mostrarTelaReceberRecomendacao() {

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
