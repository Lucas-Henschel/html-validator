/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JFileChooser;

/**
 *
 * @author lucas
 */
public class FileController {
    public static FileController fileController;
    
    private JFileChooser jFileChooser;
    
    public static FileController getFileController() {
        if (fileController == null) {
            fileController = new FileController();
        }
        
        return fileController;
    }
    
    public void screen() {
        jFileChooser.setVisible(false);
    }
    
    public void chooseFile() {
        jFileChooser.setVisible(true);
    }

    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    public void setjFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
    }
}
