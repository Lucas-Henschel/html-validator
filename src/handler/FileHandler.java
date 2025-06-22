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

/**
 * Classe responsável por ler arquivos e extrair as tags HTML,
 * armazenando-as em uma pilha para posterior processamento.
 */
public class FileHandler {

    /**
     * Instância singleton de {@code FileHandler}.
     */
    public static FileHandler fileHandler;

    /**
     * Pilha que armazena as tags HTML extraídas do arquivo.
     */
    private final PilhaLista<String> stack = new PilhaLista<>();

    /**
     * Retorna a instância singleton de {@code FileHandler}.
     *
     * @return instância de {@code FileHandler}
     */
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
        stack.liberar();
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

    /**
     * Retorna a pilha contendo as tags HTML extraídas.
     *
     * @return pilha de tags HTML
     */
    public PilhaLista<String> getStack() {
        return stack;
    }
}