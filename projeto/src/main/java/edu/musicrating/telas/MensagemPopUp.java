package edu.musicrating.telas;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MensagemPopUp {

    private MensagemPopUp() {
    }

    /**
     * Exibe um pop up de erro com a mensagem da exceção.
     *
     * @param parentComponent instancia do objeto grafico que será bloqueada
     * quando o pop up for exibido.
     * @param ex exceção a ser tratata
     */
    public static void mostrarMensagemErro(Component parentComponent, Exception ex) {
        JOptionPane.showMessageDialog(parentComponent, ex.getMessage(), "Ocorreu um Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarMensagemSucesso(Component parentComponent, String mensagem) {
        JOptionPane.showMessageDialog(parentComponent, mensagem, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    }
}
