package views;

import controller.FileController;

/**
 * Interface gráfica para seleção de arquivos, integrada com FileController para tratamento da escolha do usuário.
 */
public class FileChooserView extends javax.swing.JFrame {
    /**
     * Instância singleton da janela FileChooserView.
     */
    public static FileChooserView fileChooserView;

    /**
     * Controlador responsável pela seleção e tratamento dos arquivos.
     */
    private final FileController fileController = FileController.getFileController();

    /**
     * Construtor que inicializa os componentes da interface.
     */
    public FileChooserView() {
        initComponents();
    }

    /**
     * Retorna a instância singleton da janela FileChooserView.
     *
     * @return instância única da janela
     */
    public static FileChooserView getFileChooserView() {
        if (fileChooserView == null) {
            fileChooserView = new FileChooserView();
        }
        return fileChooserView;
    }

    /**
     * Configura e exibe a tela de seleção de arquivos.
     */
    public void screen() {
        fileController.setjFileChooser(jFileChooser);
        fileController.screen();
    }

    /**
     * Reseta as interações no controller associadas à seleção de arquivo.
     */
    public void resetInteractions() {
        fileController.resetInteractions();
    }

    /**
     * Este método é chamado pelo construtor para inicializar o formulário.
     * AVISO: O conteúdo deste método é gerado automaticamente pelo Editor de Formulário.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMain = new javax.swing.JPanel();
        jFileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escolher um arquivo");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(761, 450));
        setPreferredSize(new java.awt.Dimension(761, 450));

        jFileChooser.setMinimumSize(new java.awt.Dimension(741, 387));
        jFileChooser.setPreferredSize(new java.awt.Dimension(741, 387));

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
                jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jMainLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
        );
        jMainLayout.setVerticalGroup(
                jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jMainLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método principal para execução da aplicação.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String args[]) {
        /*
         * Define o look and feel Nimbus, se disponível.
         * Caso contrário, permanece com o padrão do sistema.
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileChooserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileChooserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileChooserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileChooserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JPanel jMain;
    // End of variables declaration//GEN-END:variables
}
