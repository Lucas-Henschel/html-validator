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
                Pattern pattern = Pattern.compile("</?\\s*([a-zA-Z0-9]+)[^>]*>");
                Matcher matcher = pattern.matcher(line);
                
                while (matcher.find()) {
                    String tagName = matcher.group(1);
                    boolean isClosing = line.charAt(matcher.start() + 1) == '/';
                    String cleanTag = isClosing ? "</" + tagName + ">" : "<" + tagName + ">";
                    
                    allTags.push(cleanTag);
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

        PilhaLista<String> openTags = new PilhaLista<>();
        PilhaLista<String> tempStack = new PilhaLista<>();

        while (!allTags.estaVazia()) {
            tempStack.push(allTags.pop());
        }

        while (!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            allTags.push(tag);

            if (SingletonTagEnum.isSingleton(tag)) {
                tagsApproved.push(tag);
                continue;
            }

            if (!tag.startsWith("</")) {
                openTags.push(tag);
            } else {
                if (!openTags.estaVazia()) {
                    String topOpen = openTags.peek();
                    String openName = TagsUtil.getTagName(topOpen);
                    String closeName = TagsUtil.getTagName(tag);

                    if (openName.equals(closeName)) {
                        tagsApproved.push(openTags.pop());
                        tagsApproved.push(tag);
                    }
                }
            }
        }
    }

    private void fillTagsRepproved() {
        tagsRepproved.liberar();

        if (tagsApproved.estaVazia()) {
            fillTagsApproved();
        }

        PilhaLista<String> tempStack = new PilhaLista<>();
        while (!allTags.estaVazia()) {
            tempStack.push(allTags.pop());
        }
        
        while (!tempStack.estaVazia()) {
            String tag = tempStack.pop();
            allTags.push(tag);

            if (SingletonTagEnum.isSingleton(tag)) continue;

            if (!TagsUtil.isTagExist(tagsApproved, tag)) {
                tagsRepproved.push(tag);
            }
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
