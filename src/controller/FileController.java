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

/**
 *
 * @author lucas
 */
public class FileController {
    public static FileController fileController;
        
    private JFileChooser jFileChooser;
    
    private String absolutePath;
    
    public static FileController getFileController() {
        if (fileController == null) {
            fileController = new FileController();
        }
        
        return fileController;
    }
    
    public void screen() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Arquivos TXT e HTML", "txt", "html"
        );
        
        jFileChooser.setFileFilter(filter);
        
        startListener();
    }
    
    public void resetInteractions() {
        absolutePath = "";
    }
    
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

    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    public void setjFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
