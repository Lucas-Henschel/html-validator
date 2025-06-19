/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import javax.swing.JFileChooser;
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
        startListener();
    }
    
    private void startListener() {
        if (jFileChooser == null) return;

        jFileChooser.addActionListener(e -> {
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
                File selectedFile = jFileChooser.getSelectedFile();
                setAbsolutePath(selectedFile.getAbsolutePath());
                
                MainView.getMainView().setTextJFileLabel(absolutePath);
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
