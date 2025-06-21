/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import handler.FileHandler;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.FileChooserView;
import views.MainView;

//Controlador responsável por gerenciar a seleção e leitura de arquivos.
public class FileController {
    public static FileController fileController;
    private JFileChooser jFileChooser;
    private String absolutePath;

    // Retorna a instância singleton do FileController
    public static FileController getFileController() {
        if (fileController == null) {
            fileController = new FileController();
        }
        return fileController;
    }

    // Configura o filtro de arquivos e inicia o listener do JFileChooser
    public void screen() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos TXT e HTML", "txt", "html"
        );
        jFileChooser.setFileFilter(filter);
        startListener();
    }

    // Reseta o caminho e interações do FileHandler
    public void resetInteractions() {
        absolutePath = "";
        FileHandler.getFileHandler().resetInteractions();
    }

    // Inicia o listener para tratar a seleção de arquivos
    private void startListener() {
        if (jFileChooser == null) return;

        jFileChooser.addActionListener(e -> {
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
                File selectedFile = jFileChooser.getSelectedFile();
                setAbsolutePath(selectedFile.getAbsolutePath());
                MainView.getMainView().setTextJFileLabel(absolutePath);
                FileHandler.getFileHandler().treatFile(selectedFile);
            }
            FileChooserView.getFileChooserView().dispose();
        });
    }

    // Retorna o JFileChooser atual
    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    // Define o JFileChooser
    public void setjFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
    }

    // Retorna o caminho absoluto selecionado
    public String getAbsolutePath() {
        return absolutePath;
    }

    // Define o caminho absoluto do arquivo
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
