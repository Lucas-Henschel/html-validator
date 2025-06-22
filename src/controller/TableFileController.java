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

/**
 * Controlador responsável por processar as tags do arquivo
 * e exibir os resultados em formato de tabela.
 */
public class TableFileController {

    /**
     * Instância singleton do {@code TableFileController}.
     */
    public static TableFileController tableFileController;

    /**
     * Manipulador de arquivos utilizado para obter a pilha de dados.
     */
    private final FileHandler fileHandler = FileHandler.getFileHandler();

    /**
     * Estrutura para armazenar as tags e suas contagens.
     */
    private MapaDispersao<String> tagMap;

    /**
     * Array contendo as tags de início encontradas.
     */
    private String[] startTags;

    /**
     * Tabela de resultados exibida na interface.
     */
    private JTable jResultTable;

    /**
     * Painel que contém a tabela de resultados.
     */
    private JPanel jTableResultPanel;

    /**
     * Retorna a instância singleton do controlador {@code TableFileController}.
     *
     * @return instância de {@code TableFileController}
     */
    public static TableFileController getTableFileController() {
        if (tableFileController == null) {
            tableFileController = new TableFileController();
        }
        return tableFileController;
    }

    /**
     * Oculta o painel da tabela de resultados.
     */
    public void screen() {
        jTableResultPanel.setVisible(false);
    }

    /**
     * Reseta os dados internos e oculta o painel da tabela.
     */
    public void resetInteractions() {
        this.tagMap = new MapaDispersao<>(0);
        this.startTags = new String[0];
        jTableResultPanel.setVisible(false);
    }

    /**
     * Processa as tags presentes no arquivo, monta a tabela de resultados
     * e exibe os dados na interface.
     */
    public void treatTableFile() {
        jTableResultPanel.setVisible(true);

        PilhaLista<String> stack = fileHandler.getStack();
        PilhaLista<String> tempStack = new PilhaLista<>();

        countTags(stack, tempStack);
        RestoreOriginalStackUtil.restoreOriginalStack(stack, tempStack);

        DefaultTableModel tableModel = buildTableModel();
        updateResultTable(tableModel);
    }

    /**
     * Conta a ocorrência de tags de início na pilha de dados.
     *
     * @param stack pilha original com os dados
     * @param tempStack pilha temporária utilizada para restauração
     */
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
                    no.addCount();
                } else {
                    this.tagMap.inserir(key, tag);
                    this.startTags[count] = tag;
                    count++;
                }
            }
        }
    }

    /**
     * Cria o modelo da tabela com as tags encontradas e suas contagens.
     *
     * @return {@code DefaultTableModel} com os dados processados
     */
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

    /**
     * Atualiza a interface com o novo modelo de tabela.
     *
     * @param model modelo da tabela contendo as tags e suas contagens
     */
    private void updateResultTable(DefaultTableModel model) {
        jResultTable.setRowHeight(35);
        jResultTable.setModel(model);
        jResultTable.setVisible(false);
        jResultTable.setVisible(true);
    }

    /**
     * Retorna a tabela de resultados.
     *
     * @return {@code JTable} utilizada para exibir os resultados
     */
    public JTable getjResultTable() {
        return jResultTable;
    }

    /**
     * Define a tabela de resultados.
     *
     * @param jResultTable nova {@code JTable}
     */
    public void setjResultTable(JTable jResultTable) {
        this.jResultTable = jResultTable;
    }

    /**
     * Retorna o painel que contém a tabela de resultados.
     *
     * @return {@code JPanel} da tabela
     */
    public JPanel getjTableResultPanel() {
        return jTableResultPanel;
    }

    /**
     * Define o painel que contém a tabela de resultados.
     *
     * @param jTableResultPanel novo painel da tabela
     */
    public void setjTableResultPanel(JPanel jTableResultPanel) {
        this.jTableResultPanel = jTableResultPanel;
    }
}
