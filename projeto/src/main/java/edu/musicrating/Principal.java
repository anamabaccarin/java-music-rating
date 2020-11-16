package edu.musicrating;

import edu.musicrating.telas.CadastroTela;
import javax.swing.JFrame;

public class Principal {

    public static void main(String[] args) {

        CadastroTela cadastroTela = new CadastroTela();

        JFrame frame = new JFrame();
        frame.getContentPane().add(cadastroTela);

        frame.setTitle("Bem-vindo a Music Rating");
        frame.setSize(600, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}
