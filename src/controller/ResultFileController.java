/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import handler.FileHandler;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author lucas
 */
public class ResultFileController {
    public static ResultFileController resultFileController;
    
    private final FileHandler fileHandler = FileHandler.getFileHandler();
    
    private JTextArea jResult;
    private JPanel jResultPanel;
    
    public static ResultFileController getResultFileController() {
        if (resultFileController == null) {
            resultFileController = new ResultFileController();
        }
        
        return resultFileController;
    }
    
    public void screen() {
        jResultPanel.setVisible(false);
    }
    
    public void resetInteractions() {
        jResultPanel.setVisible(false);
        jResult.setText("");
    }
    
    public void treatResultFile() {
        jResultPanel.setVisible(true);
        jResult.setText(fileHandler.getStack().toString());
    }

    public JTextArea getjResult() {
        return jResult;
    }

    public void setjResult(JTextArea jResult) {
        this.jResult = jResult;
    }

    public JPanel getjResultPanel() {
        return jResultPanel;
    }

    public void setjResultPanel(JPanel jResultPanel) {
        this.jResultPanel = jResultPanel;
    }
}
