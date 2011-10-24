/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Janela.java
 *
 * Created on 15/09/2010, 10:57:01
 */

package compilador.gui;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel
 */
public class Janela extends javax.swing.JFrame {

    /** Creates new form Janela */
    public Janela() {
        initComponents();
        setTitle("Analisador Léxico GI - Gabriel e Ítalo");
        jTextAreaCodigoFonte.setBorder(new NumberedBorder());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jButtonNovo = new javax.swing.JButton();
        jButtonAbrir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButtonExecutar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonAjuda = new javax.swing.JButton();
        jTabbedPaneErros = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaErros = new javax.swing.JTextArea();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPaneTokens = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTokens = new javax.swing.JTable();
        jTabbedPaneCodigoFonte = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCodigoFonte = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemNovo = new javax.swing.JMenuItem();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemSalvar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemExecutar = new javax.swing.JMenuItem();
        jMenuItemCancelar = new javax.swing.JMenuItem();
        jMenuItemLimpar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAjuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analisador Léxico Ecompilator 1.0");

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jToolBar1.setRollover(true);
        jToolBar1.add(jLabel1);

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/paper_48.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.setToolTipText("Novo arquivo fonte");
        jButtonNovo.setFocusable(false);
        jButtonNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNovo);

        jButtonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/folder_48.png"))); // NOI18N
        jButtonAbrir.setText("Abrir");
        jButtonAbrir.setToolTipText("Abrir");
        jButtonAbrir.setFocusable(false);
        jButtonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAbrir);

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/floppy_disk_48.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setToolTipText("Salvar");
        jButtonSalvar.setFocusable(false);
        jButtonSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSalvar);
        jToolBar1.add(jSeparator3);

        jButtonExecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/spanner_48.png"))); // NOI18N
        jButtonExecutar.setText("Executar");
        jButtonExecutar.setToolTipText("Análise Léxica");
        jButtonExecutar.setFocusable(false);
        jButtonExecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonExecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecutarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonExecutar);

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancel_48.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonCancelar);

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/refresh_48.png"))); // NOI18N
        jButtonLimpar.setText("Limpar");
        jButtonLimpar.setToolTipText("Limpar");
        jButtonLimpar.setFocusable(false);
        jButtonLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLimpar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonLimpar);

        jButtonAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/questionmark_48.png"))); // NOI18N
        jButtonAjuda.setText("Ajuda");
        jButtonAjuda.setToolTipText("Estrutura Léxica da Linguagem");
        jButtonAjuda.setFocusable(false);
        jButtonAjuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAjuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjudaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAjuda);

        jTabbedPaneErros.setAutoscrolls(true);

        jTextAreaErros.setColumns(20);
        jTextAreaErros.setEditable(false);
        jTextAreaErros.setRows(5);
        jScrollPane3.setViewportView(jTextAreaErros);

        jTabbedPaneErros.addTab("Erros", jScrollPane3);

        jTableTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linha", "Token", "Tipo", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTokens.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTableTokens);

        jTabbedPaneTokens.addTab("Tokens", jScrollPane2);

        jSplitPane1.setLeftComponent(jTabbedPaneTokens);

        jTextAreaCodigoFonte.setColumns(20);
        jTextAreaCodigoFonte.setRows(5);
        jTextAreaCodigoFonte.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jTextAreaCodigoFonte);

        jTabbedPaneCodigoFonte.addTab("Novo Arquivo", jScrollPane1);

        jSplitPane1.setRightComponent(jTabbedPaneCodigoFonte);

        jMenu1.setText("Arquivo");

        jMenuItemNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/paper_48_menor.png"))); // NOI18N
        jMenuItemNovo.setText("Novo");
        jMenuItemNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemNovo);

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/folder_48_menor.png"))); // NOI18N
        jMenuItemAbrir.setText("Abrir");
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemAbrir);

        jMenuItemSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/floppy_disk_48_menor.png"))); // NOI18N
        jMenuItemSalvar.setText("Salvar");
        jMenuItemSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalvar);
        jMenu1.add(jSeparator2);

        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Executar");

        jMenuItemExecutar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/spanner_48_menor.png"))); // NOI18N
        jMenuItemExecutar.setText("Análise Léxica");
        jMenu2.add(jMenuItemExecutar);

        jMenuItemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancel_48_menor.png"))); // NOI18N
        jMenuItemCancelar.setText("Cancelar");
        jMenu2.add(jMenuItemCancelar);

        jMenuItemLimpar.setText("Limpar");
        jMenuItemLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLimparActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemLimpar);
        jMenu2.add(jSeparator1);

        jMenuItemAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/questionmark_48_menor.png"))); // NOI18N
        jMenuItemAjuda.setText("Ajuda");
        jMenuItemAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjudaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemAjuda);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE))
            .addComponent(jTabbedPaneErros, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneErros, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        abrirArquivo();
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jMenuItemSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_jMenuItemSalvarActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
       abrirArquivo();
    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        comecarNovoArquivoFonte();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jMenuItemNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoActionPerformed
        comecarNovoArquivoFonte();
    }//GEN-LAST:event_jMenuItemNovoActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        this.dispose();
        System.exit(0);     
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jButtonExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecutarActionPerformed

    }//GEN-LAST:event_jButtonExecutarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
  
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparFrameDeErros();
        limparTabelaDeTokens();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jMenuItemLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLimparActionPerformed
        limparFrameDeErros();
        limparTabelaDeTokens();
    }//GEN-LAST:event_jMenuItemLimparActionPerformed

    private void jButtonAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjudaActionPerformed
        new Ajuda().setVisible(true);
    }//GEN-LAST:event_jButtonAjudaActionPerformed

    private void jMenuItemAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjudaActionPerformed
        new Ajuda().setVisible(true);        
    }//GEN-LAST:event_jMenuItemAjudaActionPerformed

//----- MÉTODOS DE AÇÃO -----------------------------------------------------

   private void abrirArquivo()
    {
        JFileChooser fileChooser = getFileChooser();
        int opcao = fileChooser.showOpenDialog(this);

        if (opcao == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader reader = new FileReader(fileChooser.getSelectedFile());
                BufferedReader breader = new BufferedReader(reader);

                String line = "";
                String aux = "";

                do {
                    try {
                        line = breader.readLine();
                        if (line != null) {
                            aux = aux + line;
                            aux = aux + "\n";
                        }

                    } catch (IOException ex) {
                        JOptionPane.showInternalMessageDialog(this, "Erro durante leitura do arquivo!", "Erro ao ler arquivo", JOptionPane.ERROR_MESSAGE);
                    }
                } while (line != null);

                jTabbedPaneCodigoFonte.setTitleAt(0, fileChooser.getSelectedFile().getName());
                jTextAreaCodigoFonte.setText(aux);

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "O arquivo especificado não foi encontrado!", "Arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvarArquivo()
    {
        JFileChooser fileChooser = getFileChooser();

        int opcao = fileChooser.showSaveDialog(this);

        if (opcao == JFileChooser.APPROVE_OPTION) {
            File arquivo = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
            FileWriter writer = null;

            try {
                writer = new FileWriter(arquivo);
                writer.write(jTextAreaCodigoFonte.getText());
                writer.close();
            } catch (IOException exception) {
                JOptionPane.showInternalMessageDialog(this, "Erro durante escrita do arquivo!", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
            }
            jTabbedPaneCodigoFonte.setTitleAt(0, fileChooser.getSelectedFile().getName());
        }
    }


    private void comecarNovoArquivoFonte()
    {
        if (!codigoFonteVazio()) {
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja salvar o estado atual do código?",
                    "Novo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {
                salvarArquivo();
            }
        }
        reinicializarInterface();
    }


//----- MÉTODOS AUXILIARES -----------------------------------------------------

    private void reinicializarInterface()
    {
        limparFrameDeCodigoFonte();
        jTabbedPaneCodigoFonte.setTitleAt(0, "Novo arquivo");

        limparTabelaDeTokens();
        limparFrameDeErros();
    }

     private JFileChooser getFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir Código");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Arquivos textos", "txt", "dat", "java"));

        return fileChooser;
    }
  
    private boolean codigoFonteVazio()
    {
        return jTextAreaCodigoFonte.getText().equals("");
    }

    private void limparFrameDeCodigoFonte()
    {
        this.jTextAreaCodigoFonte.setText("");
    }

    private void limparFrameDeErros()
    {
        this.jTextAreaErros.setText("");
    }

    private void limparTabelaDeTokens()
    {
        jTableTokens.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Linha", "Token", "Tipo", "Valor"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
        });
    }

    public void imprimirErro (String erro) {

        jTextAreaErros.append(erro+'\n');
        jTextAreaErros.setForeground(Color.red);

      }

    public void imprimirToken (String token, String tipo, String valor, int linha) {

       DefaultTableModel modelo = (DefaultTableModel) jTableTokens.getModel();

       String line = null;
       line = String.valueOf(linha);

       String[] dados = {line,token,tipo,valor};

       modelo.addRow(dados);

      }

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Janela().setVisible(true);
//
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonAjuda;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExecutar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemAjuda;
    private javax.swing.JMenuItem jMenuItemCancelar;
    private javax.swing.JMenuItem jMenuItemExecutar;
    private javax.swing.JMenuItem jMenuItemLimpar;
    private javax.swing.JMenuItem jMenuItemNovo;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPaneCodigoFonte;
    private javax.swing.JTabbedPane jTabbedPaneErros;
    private javax.swing.JTabbedPane jTabbedPaneTokens;
    private javax.swing.JTable jTableTokens;
    private javax.swing.JTextArea jTextAreaCodigoFonte;
    private javax.swing.JTextArea jTextAreaErros;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}