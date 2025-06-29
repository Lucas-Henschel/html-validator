/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import controller.ResultFileController;
import controller.TableFileController;
import enums.SingletonTagEnum;
import enums.TagEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import stack.main.PilhaLista;
import utils.RestoreOriginalStackUtil;
import utils.TagsUtil;

/**
 *
 * @author lucas
 */
public class FileHandler {
    public static FileHandler fileHandler;
    
    private final PilhaLista<String> allTags = new PilhaLista<>();
    private final PilhaLista<String> tagsApproved = new PilhaLista<>();
    private final PilhaLista<String> tagsRepproved = new PilhaLista<>();
    
    private final Pattern pattern = Pattern.compile("<[^>]+>");
    
    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        
        return fileHandler;
    }
    
    public void resetInteractions() {
        allTags.liberar();
        tagsApproved.liberar();
        tagsRepproved.liberar();
    }
    
    public void treatFile(File file) {
        resetInteractions();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                
                while (matcher.find()) {
                    String rawTag = matcher.group();
                    String tagName = TagsUtil.getTagName(rawTag);
                    String normalized;
                    
                    if (TagEnum.END_TAG.isEndTag(rawTag)) {
                        normalized = "</" + tagName + ">";
                    } else if (SingletonTagEnum.isSingleton(rawTag)) {
                        normalized = "<" + tagName + ">";
                    } else {
                        normalized = "<" + tagName + ">";
                    }
                    
                    allTags.push(normalized);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        handleHtmlTags();
        
        ResultFileController.getResultFileController().treatResultFile();
        
        if (getTagsRepproved().estaVazia()) {
            TableFileController.getTableFileController().treatTableFile();
        }
    }
    
    private void handleHtmlTags() {
        tagsApproved.liberar();
        tagsRepproved.liberar();

        PilhaLista<String> tempStack = new PilhaLista<>();
        PilhaLista<String> openTags = new PilhaLista<>();
        PilhaLista<String> aprovadasTemp = new PilhaLista<>();

        RestoreOriginalStackUtil.restoreOriginalStack(tempStack, allTags);

        while (!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            allTags.push(tag);

            if (SingletonTagEnum.isSingleton(tag)) {
                aprovadasTemp.push(tag);
            } else if (TagEnum.START_TAG.isStartTag(tag)) {
                openTags.push(tag);
            } else {
                if (openTags.estaVazia()) {
                    tagsRepproved.push(tag);
                    continue;
                }

                String lastOpen = openTags.peek();
                String closeName = TagsUtil.getTagName(tag);
                String openName = TagsUtil.getTagName(lastOpen);

                if (openName.equalsIgnoreCase(closeName)) {
                    openTags.pop();
                    aprovadasTemp.push(tag);
                    aprovadasTemp.push(lastOpen);
                } else {
                    tagsRepproved.push(tag);
                    tagsRepproved.push(openTags.pop());
                }
            }
        }

        while (!openTags.estaVazia()) {
            tagsRepproved.push(openTags.pop());
        }
        
        RestoreOriginalStackUtil.restoreOriginalStack(tagsApproved, aprovadasTemp);
    }
   
    public PilhaLista<String> getAllTags() {
        return allTags;
    }

    public PilhaLista<String> getTagsApproved() {
        return tagsApproved;
    }

    public PilhaLista<String> getTagsRepproved() {
        return tagsRepproved;
    }
}
