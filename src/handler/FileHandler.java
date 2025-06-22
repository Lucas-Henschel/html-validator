/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import controller.ResultFileController;
import controller.TableFileController;
import enums.SingletonTagEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import stack.main.PilhaLista;

/**
 *
 * @author lucas
 */
public class FileHandler {
    public static FileHandler fileHandler;
    
    private final PilhaLista<String> stack = new PilhaLista<>();
    
    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        
        return fileHandler;
    }
    
    public void resetInteractions() {
        stack.liberar();
    }
    
    public void treatFile(File file) {
        resetInteractions();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("</?\\s*([a-zA-Z0-9]+)[^>]*>");
                Matcher matcher = pattern.matcher(line);
                
                while (matcher.find()) {
                    String tagName = matcher.group(1);
                    boolean isClosing = line.charAt(matcher.start() + 1) == '/';
                    String cleanTag = isClosing ? "</" + tagName + ">" : "<" + tagName + ">";
                    
                    if (SingletonTagEnum.isSingleton(tagName)) {
                        stack.push(cleanTag);
                    } else {
                        stack.push(cleanTag);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        ResultFileController.getResultFileController().treatResultFile();
        TableFileController.getTableFileController().treatTableFile();
    }

    public PilhaLista<String> getStack() {
        return stack;
    }
}
