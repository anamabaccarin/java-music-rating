package edu.musicrating.telas;

import edu.musicrating.acessodados.UsuarioDados;
import edu.musicrating.entidades.Usuario;
import edu.musicrating.negocio.UsuarioNegocio;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CadastroTela extends JPanel {

    private JTextField loginTextField;

    private JPasswordField senhaPasswordField;

    private JButton cadastroButton;

    public CadastroTela() {
        super(new GridBagLayout());

        // Inicialização dos objetos do formulario
        loginTextField = new JTextField();
        senhaPasswordField = new JPasswordField();

        cadastroButton = new JButton("Cadastrar");
        cadastroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String senha = new String(senhaPasswordField.getPassword());

                // Persistencia
                try {
                    UsuarioNegocio.cadastrar(login, senha);
                    MensagemPopUp.mostrarMensagemSucesso(cadastroButton, "Usuario cadastrado!");
                } catch (Exception ex) {
                    MensagemPopUp.mostrarMensagemErro(cadastroButton, ex);
                }
            }
        });

        // Layout
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        add(new JLabel("Nome do usuario:"), c);

        c.gridy = 0;
        c.gridx = 1;
        c.weightx = 1.0;
        add(loginTextField, c);

        c.gridy = 1;
        c.gridx = 0;
        c.weightx = 0.0;
        add(new JLabel("Senha:"), c);

        c.gridy = 1;
        c.gridx = 1;
        add(senhaPasswordField, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        add(cadastroButton, c);
    }

}
