/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import enums.TagEnum;
import handler.FileHandler;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import scatterMap.main.ListaEncadeadaMapa;
import scatterMap.main.MapaDispersao;
import scatterMap.model.NoMapa;
import stack.main.PilhaLista;
import utils.RestoreOriginalStackUtil;
import utils.TagsUtil;

/**
 *
 * @author lucas
 */
public class TableFileController {
    public static TableFileController tableFileController;
    
    private final FileHandler fileHandler = FileHandler.getFileHandler();
    
    private MapaDispersao<String> tagMap;
    
    private PilhaLista<String> stack = new PilhaLista<>();
    private PilhaLista<String> tempStack = new PilhaLista<>();
    
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
        this.tagMap = new MapaDispersao<>(0);
        
        jTableResultPanel.setVisible(false);
    }
    
    public void treatTableFile() {
        jTableResultPanel.setVisible(true);
        
        stack = fileHandler.getTagsApproved();        
        tempStack = new PilhaLista<>();
        
        countTags();
        RestoreOriginalStackUtil.restoreOriginalStack(stack, tempStack);

        DefaultTableModel tableModel = buildTableModel();
        updateResultTable(tableModel);
    }
    
    private void countTags() {
        int quantityStartTags = stack.getListaEncadeada().obterComprimento();
        this.tagMap = new MapaDispersao<>(quantityStartTags);
                
        while (!stack.estaVazia()) {
            String tag = stack.pop();
            tempStack.push(tag);

            if (TagEnum.START_TAG.isStartTag(tag)) {
                int key = tag.hashCode();
                NoMapa<String> no = this.tagMap.buscar(key);
                
                if (no != null && no.getInfo().equals(tag)) {
                    no.addCount();
                } else {
                    this.tagMap.inserir(key, tag);
                }
            }
        }
    }
        
    private DefaultTableModel buildTableModel() {
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("Tag");
        model.addColumn("Número de ocorrências");
        
        tempStack = new PilhaLista<>();
        
        while (!stack.estaVazia()) {
            String tag = stack.pop();
            tempStack.push(tag);
        }
        
        while(!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            stack.push(tag);
            
            if (!TagsUtil.isTagExist(tempStack, tag)) {
                int key = tag.hashCode();
            
                NoMapa<String> node = tagMap.buscar(key);

                model.addRow(
                    new Object[]{
                        " " + node.getInfo(), 
                        node.getCount()
                    }
                );
            }
        }
                
        return model;
    }
    
    private void updateResultTable(DefaultTableModel model) {
        jResultTable.getTableHeader().setReorderingAllowed(false);
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
