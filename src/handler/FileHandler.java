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
        
        fillTagsApproved();
        fillTagsRepproved();
        
        ResultFileController.getResultFileController().treatResultFile();
        TableFileController.getTableFileController().treatTableFile();
    }
    
    private void fillTagsApproved() {
        tagsApproved.liberar();

        PilhaLista<String> tempStack = new PilhaLista<>();
        PilhaLista<String> openTags = new PilhaLista<>();
        PilhaLista<String> aprovadasTemp = new PilhaLista<>();

        while (!allTags.estaVazia()) {
            tempStack.push(allTags.pop());
        }

        while (!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            allTags.push(tag);

            if (SingletonTagEnum.isSingleton(tag)) {
                aprovadasTemp.push(tag);
            } else if (!tag.startsWith("</")) {
                openTags.push(tag);
                aprovadasTemp.push(tag);
            } else {
                if (!openTags.estaVazia()) {
                    String lastOpen = openTags.peek();

                    String openName  = TagsUtil.getTagName(lastOpen).toLowerCase();
                    String closeName = TagsUtil.getTagName(tag).toLowerCase();

                    if (openName.equals(closeName)) {
                        openTags.pop();
                    }
                }
            }
        }

        PilhaLista<String> cleanStack = new PilhaLista<>();

        while (!aprovadasTemp.estaVazia()) {
            String tag = aprovadasTemp.pop();

            if (!tag.startsWith("</") && !SingletonTagEnum.isSingleton(tag)) {
                boolean found = false;
                PilhaLista<String> aux = new PilhaLista<>();

                while (!openTags.estaVazia()) {
                    String t = openTags.pop();
                    
                    if (!found && t.equals(tag)) {
                        found = true;
                    } else {
                        aux.push(t);
                    }
                }

                while (!aux.estaVazia()) {
                    openTags.push(aux.pop());
                }

                if (!found) {
                    cleanStack.push(tag);
                }
            } else {
                cleanStack.push(tag);
            }
        }

        while (!cleanStack.estaVazia()) {
            tagsApproved.push(cleanStack.pop());
        }
    }

    private void fillTagsRepproved() {
        tagsRepproved.liberar();

        if (tagsApproved.estaVazia()) {
            fillTagsApproved();
        }

        PilhaLista<String> tempStack = new PilhaLista<>();
        PilhaLista<String> openStack = new PilhaLista<>();
        PilhaLista<String> reprovTemp = new PilhaLista<>();

        while (!allTags.estaVazia()) {
            tempStack.push(allTags.pop());
        }

        while (!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            allTags.push(tag);

            if (SingletonTagEnum.isSingleton(tag)) continue;

            if (!tag.startsWith("</")) {
                openStack.push(tag);
                reprovTemp.push(tag);
                
                continue;
            }

            if (!openStack.estaVazia()) {
                String topOpen = openStack.peek();
                String openName = TagsUtil.getTagName(topOpen).toLowerCase();
                String closeName = TagsUtil.getTagName(tag).toLowerCase();

                if (openName.equals(closeName)) {
                    openStack.pop();

                    PilhaLista<String> aux = new PilhaLista<>();
                    
                    while (!reprovTemp.estaVazia()) {
                        String reprovTag = reprovTemp.pop();
                        
                        if (reprovTag.equals(topOpen)) {
                            break;
                        }
                        
                        aux.push(reprovTag);
                    }
                    
                    while (!aux.estaVazia()) reprovTemp.push(aux.pop());
                } else {
                    reprovTemp.push(tag);
                }
            } else {
                reprovTemp.push(tag);
            }
        }

        PilhaLista<String> finalStack = new PilhaLista<>();
        while (!reprovTemp.estaVazia()) {
            finalStack.push(reprovTemp.pop());
        }
        
        while (!finalStack.estaVazia()) {
            tagsRepproved.push(finalStack.pop());
        }
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
