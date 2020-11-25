package edu.musicrating.telas;

import edu.musicrating.entidades.Genero;
import edu.musicrating.entidades.UsuarioGenero;
import edu.musicrating.negocio.UsuarioGeneroNegocio;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GenerosTela extends javax.swing.JFrame {

    /**
     * Classe que representa uma celula da tabela que vai conter a referencia do
     * objecto que será excluido na ação "Excluir"
     */
    private static class CelulaBotaoExcluir {

        private UsuarioGenero usuarioGenero;

        public CelulaBotaoExcluir(UsuarioGenero usuarioGenero) {
            this.usuarioGenero = usuarioGenero;
        }

        public UsuarioGenero getUsuarioGenero() {
            return usuarioGenero;
        }

        @Override
        public String toString() {
            return "Excluir";
        }
    }

    private static final int COLUNA_EXCLUIR = 2;

    private DefaultTableModel generosTableModel;

    /**
     * Creates new form GenerosTela
     */
    public GenerosTela() {
        super("Meus Gêneros");

        generosTableModel = new DefaultTableModel(new String[]{
            "Gênero Musical", "Data de Inserção", ""}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == COLUNA_EXCLUIR);
            }
        };

        initComponents();

        new ButtonColumn(generosTable, new AbstractAction() {
            public void actionPerformed(ActionEvent evento) {
                int row = Integer.valueOf(evento.getActionCommand());
                CelulaBotaoExcluir selecionado = (CelulaBotaoExcluir) generosTable.getValueAt(row, COLUNA_EXCLUIR);

                try {
                    UsuarioGeneroNegocio.excluir(selecionado.getUsuarioGenero());

                    atualizar();
                } catch (Exception e) {
                    MensagemPopUp.mostrarMensagemErro(GenerosTela.this, e);
                }
            }
        }, COLUNA_EXCLUIR);

        generosTable.getColumnModel().getColumn(COLUNA_EXCLUIR).setMaxWidth(200);
    }

    /**
     * Retorna instancia de Renderer responsavel por configurar qual propriedade
     * de Genero deve ser visualizada na ComboBox.
     */
    private ListCellRenderer getGenerosRenderer() {
        return new BasicComboBoxRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component componente = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    Genero genero = (Genero) value;
                    setText(genero.getNome());
                }
                return componente;
            }
        };
    }

    private TableModel getGenerosTableModel() {
        return generosTableModel;
    }

    private void atualizar() {
        try {
            // Preenche combo box com valores do banco de dados
            List<Genero> generosNaoPreferidos = UsuarioGeneroNegocio.obterGenerosNaoPreferidos();
            generosComboBox.setModel(new DefaultComboBoxModel(generosNaoPreferidos.toArray()));

            // Preenche tabela com os valroes do banco de dados
            List<UsuarioGenero> generosPreferidos = UsuarioGeneroNegocio.obterGenerosPrerefidos();

            generosTableModel.setRowCount(0);

            SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy kk:mm");
            Iterator<UsuarioGenero> it = generosPreferidos.iterator();
            while (it.hasNext()) {
                UsuarioGenero usuarioGenero = it.next();
                generosTableModel.addRow(new Object[]{
                    usuarioGenero.getGenero().getNome(),
                    formatadorData.format(usuarioGenero.getDataRegistro()),
                    new CelulaBotaoExcluir(usuarioGenero)
                });
            }

            // Habilitar paineis
            boolean habilitarAdicionar = !generosNaoPreferidos.isEmpty();

            generosComboBox.setEnabled(habilitarAdicionar);
            adicionarGeneroPanel.setEnabled(habilitarAdicionar);
            adicionarButton.setEnabled(habilitarAdicionar);

        } catch (Exception e) {
            MensagemPopUp.mostrarMensagemErro(this, e);
        }
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            atualizar();
        }
        super.setVisible(b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generosScrollPane = new javax.swing.JScrollPane();
        generosTable = new javax.swing.JTable();
        adicionarGeneroPanel = new javax.swing.JPanel();
        generosComboBox = new javax.swing.JComboBox<>();
        adicionarButton = new javax.swing.JButton();
        voltarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        generosTable.setModel(getGenerosTableModel());
        generosTable.getTableHeader().setReorderingAllowed(false);
        generosScrollPane.setViewportView(generosTable);

        adicionarGeneroPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione seus gêneros preferidos"));

        generosComboBox.setRenderer(getGenerosRenderer());

        adicionarButton.setText("Adicionar");
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adicionarGeneroPanelLayout = new javax.swing.GroupLayout(adicionarGeneroPanel);
        adicionarGeneroPanel.setLayout(adicionarGeneroPanelLayout);
        adicionarGeneroPanelLayout.setHorizontalGroup(
            adicionarGeneroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generosComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adicionarGeneroPanelLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(adicionarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adicionarGeneroPanelLayout.setVerticalGroup(
            adicionarGeneroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarGeneroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(adicionarButton)
                .addContainerGap())
        );

        voltarButton.setText("Voltar");
        voltarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adicionarGeneroPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(voltarButton)
                        .addContainerGap(323, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adicionarGeneroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(voltarButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarButtonActionPerformed
        try {
            Genero genero = (Genero) generosComboBox.getSelectedItem();
            UsuarioGeneroNegocio.inserirGeneroPreferido(genero);

            atualizar();
        } catch (Exception e) {
            MensagemPopUp.mostrarMensagemErro(this, e);
        }
    }//GEN-LAST:event_adicionarButtonActionPerformed

    private void voltarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarButtonActionPerformed
        Controlador.mostrarTelaDashboard();
    }//GEN-LAST:event_voltarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarButton;
    private javax.swing.JPanel adicionarGeneroPanel;
    private javax.swing.JComboBox<Genero> generosComboBox;
    private javax.swing.JScrollPane generosScrollPane;
    private javax.swing.JTable generosTable;
    private javax.swing.JButton voltarButton;
    // End of variables declaration//GEN-END:variables
}
