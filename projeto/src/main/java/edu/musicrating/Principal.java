package edu.musicrating;

import edu.musicrating.negocio.Controlador;

public class Principal {

    private Principal() {
    }

    public static void main(String[] args) {
        //Configura o Look and Feel com base em codigo gerado pelo Netbeans (JFrame Form)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        Controlador.mostrarTelaLogin();
    }
}
