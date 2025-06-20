/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import enums.TagEnum;
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
        int stackSize = stack.getListaEncadeada().obterComprimento();
        
        PilhaLista<String> tempStack = new PilhaLista<>();
        
        String[] tags = new String[stackSize];
        int[] counts = new int[stackSize];
        int tagCount = countTags(stack, tempStack, tags, counts);

        restoreOriginalStack(stack, tempStack);

        DefaultTableModel tableModel = buildTableModel(tags, counts, tagCount);
        updateResultTable(tableModel);

//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("Tag");
//        tableModel.addColumn("Número de ocorrências");
//
//        jResultTable.setRowHeight(35);
//
//        List<String> tags = new ArrayList<>();
//        List<Integer> counts = new ArrayList<>();
//
//        while (!stack.estaVazia()) {
//            String tag = stack.pop();            
//            tempStack.push(tag);
//            
//            if (TagEnum.START_TAG.isStartTag(tag)) {
//                int index = tags.indexOf(tag);
//                
//                if (index != -1) {
//                    counts.set(index, counts.get(index) + 1);
//                } else {
//                    tags.add(tag);
//                    counts.add(1);
//                }
//            }
//        }
//        
//        while (!tempStack.estaVazia()) {
//            stack.push(tempStack.pop());
//        }
//
//        for (int i = tags.size() - 1; i >= 0; i--) {
//            Object[] row = { 
//                " " + tags.get(i), 
//                counts.get(i) 
//            };
//            
//            tableModel.addRow(row);
//        }
//
//        jResultTable.setModel(tableModel);
//        jResultTable.setVisible(false);
//        jResultTable.setVisible(true);
    }
    
    private int countTags(PilhaLista<String> stack, PilhaLista<String> tempStack, String[] tags, int[] counts) {
        int size = 0;
        
        while (!stack.estaVazia()) {
            String tag = stack.pop();            
            tempStack.push(tag);
            
            if (TagEnum.START_TAG.isStartTag(tag)) {
                int index = findTagIndex(tags, size, tag);
                
                if (index != -1) {
                    counts[index]++;
                } else {
                    tags[size] = tag;
                    counts[size] = 1;
                    size++;
                }
            }
        }
        
        return size;
    }
    
    private int findTagIndex(String[] tags, int size, String tag) {
        for (int i = 0; i < size; i++) {
            if (tags[i].equals(tag)) {
                return i;
            }
        }
        
        return -1;
    }
    
    private void restoreOriginalStack(PilhaLista<String> stack, PilhaLista<String> tempStack) {
        while (!tempStack.estaVazia()) {
            stack.push(tempStack.pop());
        }
    }
    
    private DefaultTableModel buildTableModel(String[] tags, int[] counts, int size) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tag");
        model.addColumn("Número de ocorrências");

        for (int i = size - 1; i >= 0; i--) {
            model.addRow(new Object[]{" " + tags[i], counts[i]});
        }

        return model;
    }
    
    private void updateResultTable(DefaultTableModel model) {
        jResultTable.setRowHeight(35);
        jResultTable.setModel(model);
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
