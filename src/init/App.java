package init;

import views.MainView;

/**
 * Classe principal responsável por inicializar e executar a aplicação.
 * Estende {@code javax.swing.JFrame} e exibe a interface principal ao ser executada.
 */
public class App extends javax.swing.JFrame {

    /**
     * Construtor da classe {@code App}.
     * Inicializa os componentes da janela e executa o método de inicialização.
     */
    public App() {
        initComponents();
        init();
    }

    /**
     * Método de inicialização da aplicação.
     * Exibe a tela principal e reseta os dados da interface.
     */
    private void init() {
        showMainView();
    }

    /**
     * Exibe a janela principal da aplicação,
     * limpando os dados anteriores e preparando a interface.
     */
    private void showMainView() {
        MainView mainView = MainView.getMainView();
        mainView.setVisible(true);
        mainView.screen();
        mainView.resetInteractions();
    }

    /**
     * Este método é chamado automaticamente pelo construtor
     * para inicializar os componentes gráficos (GUI).
     *
     * <p><b>ATENÇÃO:</b> Não modificar este método manualmente.
     * O conteúdo é gerado automaticamente pelo Editor de Formulários.</p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método principal que inicia a execução da aplicação.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String args[]) {
        /* Define o look and feel Nimbus, se disponível */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Instancia e exibe a aplicação
        new App();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
