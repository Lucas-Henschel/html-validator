/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author lucas
 */
public class TableFileController {
    public static TableFileController tableFileController;
    
    private JTable jResultTable;
    private JPanel jTableResultPanel;
    
    public static TableFileController getTableFileController() {
        if (tableFileController == null) {
            tableFileController = new TableFileController();
        }
        
        return tableFileController;
    }
    
    public void screen() {
        jTableResultPanel.setVisible(false);
    }

    public JTable getjResultTable() {
        return jResultTable;
    }

    public void setjResultTable(JTable jResultTable) {
        this.jResultTable = jResultTable;
    }

    public JPanel getjTableResultPanel() {
        return jTableResultPanel;
    }

    public void setjTableResultPanel(JPanel jTableResultPanel) {
        this.jTableResultPanel = jTableResultPanel;
    }
}
