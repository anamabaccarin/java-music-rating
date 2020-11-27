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

        this.generosTableModel = new DefaultTableModel(new String[]{
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

        this.generosTable.getColumnModel().getColumn(COLUNA_EXCLUIR).setMaxWidth(200);
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
        voltarButton = new javax.swing.JButton();
        generosComboBox = new javax.swing.JComboBox<>();
        adicionarButton = new javax.swing.JButton();
        generojLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        generosTable.setModel(getGenerosTableModel());
        generosTable.getTableHeader().setReorderingAllowed(false);
        generosScrollPane.setViewportView(generosTable);

        voltarButton.setText("Voltar");
        voltarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarButtonActionPerformed(evt);
            }
        });

        generosComboBox.setBorder(null);
        generosComboBox.setRenderer(getGenerosRenderer());

        adicionarButton.setText("Adicionar");
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        generojLabel.setText("Selecione gênero");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(voltarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(adicionarButton)))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generojLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(generosComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generosScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generojLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(adicionarButton)
                .addGap(21, 21, 21)
                .addComponent(voltarButton)
                .addContainerGap(16, Short.MAX_VALUE))
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
    private javax.swing.JLabel generojLabel;
    private javax.swing.JComboBox<Genero> generosComboBox;
    private javax.swing.JScrollPane generosScrollPane;
    private javax.swing.JTable generosTable;
    private javax.swing.JButton voltarButton;
    // End of variables declaration//GEN-END:variables
}
