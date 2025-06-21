package handler;

import controller.ResultFileController;
import controller.TableFileController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import stack.main.PilhaLista;

// Classe responsável por ler o arquivo e extrair as tags HTML, armazenando em uma pilha
public class FileHandler {
    public static FileHandler fileHandler;

    private final PilhaLista<String> stack = new PilhaLista<>();

    // Retorna a instância singleton do FileHandler
    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

    // Limpa a pilha
    public void resetInteractions() {
        stack.liberar();
    }

    // Lê o arquivo, extrai as tags HTML e envia os dados para os controladores de resultado
    public void treatFile(File file) {
        resetInteractions();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("<[^>]+>"); // Regex para capturar tags HTML
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    stack.push(matcher.group()); // Empilha cada tag encontrada
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Atualiza as interfaces com os dados extraídos
        ResultFileController.getResultFileController().treatResultFile();
        TableFileController.getTableFileController().treatTableFile();
    }

    // Retorna a pilha de tags
    public PilhaLista<String> getStack() {
        return stack;
    }
}
