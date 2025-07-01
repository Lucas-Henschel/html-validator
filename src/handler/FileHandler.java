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
 * Classe responsável por ler arquivos e extrair as tags HTML,
 * armazenando-as em uma pilha para posterior processamento.
 */
public class FileHandler {

    /**
     * Instância singleton de {@code FileHandler}.
     */
    public static FileHandler fileHandler;
    
    private final PilhaLista<String> allTags = new PilhaLista<>();
    private final PilhaLista<String> tagsApproved = new PilhaLista<>();
    private final PilhaLista<String> tagsRepproved = new PilhaLista<>();
    
    private final Pattern pattern = Pattern.compile("<[^>]+>");
    
    private StringBuilder sbError = new StringBuilder();
    
    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

    /**
     * Limpa a pilha, removendo todos os elementos armazenados.
     */
    public void resetInteractions() {
        allTags.liberar();
        tagsApproved.liberar();
        tagsRepproved.liberar();
    }

    /**
     * Lê o conteúdo de um arquivo, extrai todas as tags HTML e empilha cada uma delas.
     * Após a extração, envia os dados para os controladores de exibição de resultado e tabela.
     *
     * @param file arquivo a ser lido e processado
     */
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
        sbError.setLength(0);

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
                    sbError.append(
                        String.format(
                            "Tag final inesperada: encontrado %s mas nenhuma tag estava aberta.%n",
                            tag
                        )
                    );
                    
                    tagsRepproved.push(tag);           
            } else {
                    String closeName = TagsUtil.getTagName(tag);
                    PilhaLista<String> buffer = new PilhaLista<>();
                    boolean foundMatch = false;

                    while (!openTags.estaVazia()) {
                        String openTag = openTags.pop();
                        buffer.push(openTag);

                        if (TagsUtil.getTagName(openTag).equalsIgnoreCase(closeName)) {
                            buffer.pop();
                            aprovadasTemp.push(tag);
                            aprovadasTemp.push(openTag);
                            
                            foundMatch = true;
                            break;
                        }
                    }

                    if (foundMatch) {
                        while (!buffer.estaVazia()) {
                            String unclosed = buffer.pop();
                            
                            sbError.append(
                                String.format(
                                    "Falta tag final: esperava‑se </%s> para fechar %s.%n",
                                    TagsUtil.getTagName(unclosed), unclosed
                                )
                            );
                            
                            tagsRepproved.push(unclosed);
                        }
                    } else {
                        while (!buffer.estaVazia()) {
                            openTags.push(buffer.pop());
                        }
                        
                        sbError.append(
                            String.format(
                                "Tag final inesperada: encontrado %s mas nenhuma <%s> estava aberta.%n",
                                tag, closeName
                            )
                        );
                        
                        tagsRepproved.push(tag);
                    }
                }
            }
        }

        while (!openTags.estaVazia()) {
            String unclosed = openTags.pop();
            
            sbError.append(
                String.format(
                    "Falta tag final: esperava-se </%s> para fechar %s.\n", 
                    TagsUtil.getTagName(unclosed),
                    unclosed
                )
            );
            
            tagsRepproved.push(unclosed);
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

    public StringBuilder getSbError() {
        return sbError;
    }
}
