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
import scatterMap.main.MapaDispersao;
import scatterMap.model.NoMapa;
import stack.main.PilhaLista;
import utils.RestoreOriginalStackUtil;
import utils.StartTagsUtil;

// Controlador responsável por processar as tags do arquivo e exibir os resultados em formato de tabela.
public class TableFileController {
    public static TableFileController tableFileController;

    private final FileHandler fileHandler = FileHandler.getFileHandler();

    private MapaDispersao<String> tagMap;
    private String[] startTags;

    private JTable jResultTable;
    private JPanel jTableResultPanel;

    // Retorna a instância singleton do controlador
    public static TableFileController getTableFileController() {
        if (tableFileController == null) {
            tableFileController = new TableFileController();
        }
        return tableFileController;
    }

    // Oculta o painel da tabela
    public void screen() {
        jTableResultPanel.setVisible(false);
    }

    // Reseta dados e oculta o painel da tabela
    public void resetInteractions() {
        this.tagMap = new MapaDispersao<>(0);
        this.startTags = new String[0];
        jTableResultPanel.setVisible(false);
    }

    // Processa as tags e exibe a tabela com os resultados
    public void treatTableFile() {
        jTableResultPanel.setVisible(true);

        PilhaLista<String> stack = fileHandler.getStack();
        PilhaLista<String> tempStack = new PilhaLista<>();

        countTags(stack, tempStack); // Conta as tags de início
        RestoreOriginalStackUtil.restoreOriginalStack(stack, tempStack); // Restaura a pilha original

        DefaultTableModel tableModel = buildTableModel(); // Monta a tabela
        updateResultTable(tableModel); // Atualiza a UI
    }

    // Conta a ocorrência das tags de início na pilha
    private void countTags(PilhaLista<String> stack, PilhaLista<String> tempStack) {
        int quantityStartTags = StartTagsUtil.countStartTags(stack);
        this.tagMap = new MapaDispersao<>(quantityStartTags);
        this.startTags = new String[quantityStartTags];

        int count = 0;

        while (!stack.estaVazia()) {
            String tag = stack.pop();
            tempStack.push(tag);

            if (TagEnum.START_TAG.isStartTag(tag)) {
                int key = tag.hashCode();
                NoMapa<String> no = this.tagMap.buscar(key);

                if (no != null && no.getInfo().equals(tag)) {
                    no.addCount(); // Tag já existe, incrementa contador
                } else {
                    this.tagMap.inserir(key, tag); // Insere nova tag
                    this.startTags[count] = tag;
                    count++;
                }
            }
        }
    }

    // Cria o modelo da tabela com as tags e contagens
    private DefaultTableModel buildTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tag");
        model.addColumn("Número de ocorrências");

        for (int i = this.startTags.length - 1; i >= 0; i--) {
            if (this.startTags[i] == null) continue;

            int key = this.startTags[i].hashCode();
            NoMapa<String> no = this.tagMap.buscar(key);

            model.addRow(new Object[]{
                    " " + no.getInfo(),
                    no.getCount()
            });
        }

        return model;
    }

    // Atualiza e exibe a tabela na interface
    private void updateResultTable(DefaultTableModel model) {
        jResultTable.setRowHeight(35);
        jResultTable.setModel(model);
        jResultTable.setVisible(false);
        jResultTable.setVisible(true);
    }

    // Retorna o JTable
    public JTable getjResultTable() {
        return jResultTable;
    }

    // Define o JTable
    public void setjResultTable(JTable jResultTable) {
        this.jResultTable = jResultTable;
    }

    // Retorna o painel da tabela
    public JPanel getjTableResultPanel() {
        return jTableResultPanel;
    }

    // Define o painel da tabela
    public void setjTableResultPanel(JPanel jTableResultPanel) {
        this.jTableResultPanel = jTableResultPanel;
    }
}
