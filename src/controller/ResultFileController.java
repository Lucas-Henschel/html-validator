/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import handler.FileHandler;
import javax.swing.JPanel;
import javax.swing.JTextArea;

 //Controlador responsável por exibir e limpar os resultados do arquivo processado.
public class ResultFileController {
    public static ResultFileController resultFileController;

    private final FileHandler fileHandler = FileHandler.getFileHandler();
    private JTextArea jResult;
    private JPanel jResultPanel;

    // Retorna a instância singleton do controlador
    public static ResultFileController getResultFileController() {
        if (resultFileController == null) {
            resultFileController = new ResultFileController();
        }
        return resultFileController;
    }

    // Oculta o painel de resultado
    public void screen() {
        jResultPanel.setVisible(false);
    }

    // Limpa o conteúdo e oculta o painel
    public void resetInteractions() {
        jResultPanel.setVisible(false);
        jResult.setText("");
    }

    // Exibe o resultado do arquivo no painel
    public void treatResultFile() {
        jResultPanel.setVisible(true);
        jResult.setText(fileHandler.getStack().toString());
    }

    // Retorna o JTextArea de resultado
    public JTextArea getjResult() {
        return jResult;
    }

    // Define o JTextArea de resultado
    public void setjResult(JTextArea jResult) {
        this.jResult = jResult;
    }

    // Retorna o JPanel de resultado
    public JPanel getjResultPanel() {
        return jResultPanel;
    }

    // Define o JPanel de resultado
    public void setjResultPanel(JPanel jResultPanel) {
        this.jResultPanel = jResultPanel;
    }
}
