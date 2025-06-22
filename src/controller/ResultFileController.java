/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import handler.FileHandler;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Controlador responsável por exibir e limpar os resultados do arquivo processado.
 */
public class ResultFileController {

    /**
     * Instância singleton do {@code ResultFileController}.
     */
    public static ResultFileController resultFileController;

    /**
     * Referência ao manipulador de arquivos.
     */
    private final FileHandler fileHandler = FileHandler.getFileHandler();

    /**
     * Componente de área de texto onde o resultado será exibido.
     */
    private JTextArea jResult;

    /**
     * Painel que contém a área de resultado.
     */
    private JPanel jResultPanel;

    /**
     * Retorna a instância singleton do controlador {@code ResultFileController}.
     *
     * @return instância de {@code ResultFileController}
     */
    public static ResultFileController getResultFileController() {
        if (resultFileController == null) {
            resultFileController = new ResultFileController();
        }
        return resultFileController;
    }

    /**
     * Oculta o painel de resultado.
     */
    public void screen() {
        jResultPanel.setVisible(false);
    }

    /**
     * Limpa o conteúdo da área de resultado e oculta o painel.
     */
    public void resetInteractions() {
        jResultPanel.setVisible(false);
        jResult.setText("");
    }

    /**
     * Exibe o resultado processado no painel de resultado.
     * Os dados são obtidos da pilha do {@code FileHandler}.
     */
    public void treatResultFile() {
        jResultPanel.setVisible(true);
        jResult.setText(fileHandler.getStack().toString());
    }

    /**
     * Retorna o componente {@code JTextArea} que exibe o resultado.
     *
     * @return {@code JTextArea} de resultado
     */
    public JTextArea getjResult() {
        return jResult;
    }

    /**
     * Define o componente {@code JTextArea} de resultado.
     *
     * @param jResult nova área de texto de resultado
     */
    public void setjResult(JTextArea jResult) {
        this.jResult = jResult;
    }

    /**
     * Retorna o painel {@code JPanel} que contém a área de resultado.
     *
     * @return painel de resultado
     */
    public JPanel getjResultPanel() {
        return jResultPanel;
    }

    /**
     * Define o painel {@code JPanel} de resultado.
     *
     * @param jResultPanel novo painel de resultado
     */
    public void setjResultPanel(JPanel jResultPanel) {
        this.jResultPanel = jResultPanel;
    }
}
