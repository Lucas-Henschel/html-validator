package scatterMap.main;

import scatterMap.model.NoMapa;

/**
 * Implementação de uma lista encadeada simples utilizada
 * para armazenar nós do mapa hash.
 *
 * @param <T> tipo do valor armazenado em cada nó
 */
public class ListaEncadeadaMapa<T> {

    /**
     * Referência para o primeiro nó da lista.
     */
    private NoMapa<T> primeiro;

    /**
     * Construtor que inicializa a lista como vazia.
     */
    public ListaEncadeadaMapa() {
        primeiro = null;
    }

    /**
     * Retorna o primeiro nó da lista.
     *
     * @return primeiro nó da lista, ou {@code null} se estiver vazia
     */
    public NoMapa<T> getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um nó no início da lista.
     *
     * @param no nó a ser inserido
     */
    public void inserir(NoMapa<T> no) {
        no.setProximo(primeiro);
        this.primeiro = no;
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return {@code true} se a lista estiver vazia, {@code false} caso contrário
     */
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * Busca um nó com base na chave informada.
     *
     * @param chave chave a ser buscada
     * @return nó correspondente à chave, ou {@code null} se não encontrado
     */
    public NoMapa<T> buscar(int chave) {
        NoMapa<T> current = primeiro;

        while (current != null) {
            if (current.getChave() == chave) {
                return current;
            }
            current = current.getProximo();
        }
        return null;
    }

    /**
     * Exibe os elementos da lista no console.
     */
    public void exibir() {
        NoMapa<T> current = primeiro;

        while (current != null) {
            System.out.println(current.getInfo() + " -> ");
            current = current.getProximo();
        }

        System.out.println("null");
    }

    /**
     * Remove o nó que possui a chave especificada.
     *
     * @param chave chave do nó a ser removido
     */
    public void retirar(int chave) {
        NoMapa<T> anterior = null;
        NoMapa<T> atual = primeiro;

        while (atual != null && atual.getChave() != chave) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual != null) {
            if (anterior == null) {
                primeiro = atual.getProximo();
            } else {
                anterior.setProximo(atual.getProximo());
            }
        }
    }

    /**
     * Retorna a quantidade de nós presentes na lista.
     *
     * @return número de elementos na lista
     */
    public int obterComprimento() {
        int count = 0;
        NoMapa<T> current = primeiro;

        while (current != null) {
            count++;
            current = current.getProximo();
        }
        return count;
    }

    /**
     * Retorna o nó localizado na posição informada.
     *
     * @param index posição do nó (0-based)
     * @return nó na posição indicada
     * @throws IndexOutOfBoundsException se a posição for inválida
     */
    public NoMapa<T> obterNo(int index) {
        if (index < 0 || index >= obterComprimento()) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        int count = 0;
        NoMapa<T> current = primeiro;

        while (count < index) {
            count++;
            current = current.getProximo();
        }
        return current;
    }

    /**
     * Retorna a representação textual da lista encadeada.
     *
     * @return string com os elementos da lista no formato "elem1 -> elem2 -> ... -> null"
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoMapa<T> current = primeiro;

        while (current != null) {
            sb.append(current.getInfo()).append(" -> ");
            current = current.getProximo();
        }

        sb.append("null");
        return sb.toString();
    }
}