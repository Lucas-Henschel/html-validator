/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import handler.FileHandler;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stack.main.PilhaLista;

/**
 *
 * @author lucas
 */
public class TableFileController {
    public static TableFileController tableFileController;
    
    private final FileHandler fileHandler = FileHandler.getFileHandler();
    
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
    
    public void resetInteractions() {
        jTableResultPanel.setVisible(false);
    }
    
    public void treatTableFile() {
        jTableResultPanel.setVisible(true);
        
        PilhaLista<String> stack = fileHandler.getStack();
        PilhaLista<String> tempStack = new PilhaLista<>();

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Tag");
        tableModel.addColumn("Número de ocorrências");

        jResultTable.setRowHeight(35);

        List<String> tags = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        while (!stack.estaVazia()) {
            String tag = stack.pop();            
            tempStack.push(tag);
            
            if (!tag.startsWith("</")) {
                int index = tags.indexOf(tag);
                
                if (index != -1) {
                    counts.set(index, counts.get(index) + 1);
                } else {
                    tags.add(tag);
                    counts.add(1);
                }
            }
        }
        
        while (!tempStack.estaVazia()) {
            stack.push(tempStack.pop());
        }

        for (int i = tags.size() - 1; i >= 0; i--) {
            Object[] row = { 
                " " + tags.get(i), 
                counts.get(i) 
            };
            
            tableModel.addRow(row);
        }

        jResultTable.setModel(tableModel);
        jResultTable.setVisible(false);
        jResultTable.setVisible(true);
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
