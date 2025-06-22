package stack.main;

/**
 * Implementação de lista encadeada simples para uso em pilha genérica.
 *
 * @param <T> tipo dos elementos armazenados na lista
 */
public class ListaEncadeadaPilha<T> {

    /**
     * Referência para o primeiro nó da lista.
     */
    private NoLista<T> primeiro;

    /**
     * Construtor que inicializa a lista vazia.
     */
    public ListaEncadeadaPilha() {
        primeiro = null;
    }

    /**
     * Retorna o primeiro nó da lista.
     *
     * @return primeiro nó da lista
     */
    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um novo nó com o valor informado no início da lista.
     *
     * @param value valor a ser inserido
     */
    public void inserir(T value) {
        NoLista<T> no = new NoLista<>(value);
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
     * Busca um nó contendo o valor informado.
     *
     * @param info valor a ser buscado
     * @return nó contendo o valor, ou {@code null} se não encontrado
     */
    public NoLista<T> buscar(T info) {
        NoLista<T> current = primeiro;
        while (current != null) {
            if (current.getInfo().equals(info)) {
                return current;
            }
            current = current.getProximo();
        }
        return null;
    }

    /**
     * Exibe os elementos da lista no console, no formato "valor -> ... -> null".
     */
    public void exibir() {
        NoLista<T> current = primeiro;
        while (current != null) {
            System.out.println(current.getInfo() + " -> ");
            current = current.getProximo();
        }
        System.out.println("null");
    }

    /**
     * Remove o nó que contém o valor especificado.
     *
     * @param value valor do nó a ser removido
     */
    public void retirar(T value) {
        NoLista<T> last = null;
        NoLista<T> current = primeiro;
        while (current != null && !current.getInfo().equals(value)) {
            last = current;
            current = current.getProximo();
        }
        if (current != null) {
            if (last == null) {
                primeiro = current.getProximo();
            } else {
                last.setProximo(current.getProximo());
            }
        }
    }

    /**
     * Retorna o tamanho da lista, ou seja, o número de nós.
     *
     * @return quantidade de nós na lista
     */
    public int obterComprimento() {
        int count = 0;
        NoLista<T> current = primeiro;
        while (current != null) {
            count++;
            current = current.getProximo();
        }
        return count;
    }

    /**
     * Retorna o nó na posição especificada.
     *
     * @param index índice do nó (base zero)
     * @return nó na posição index
     * @throws IndexOutOfBoundsException se a posição for inválida
     */
    public NoLista<T> obterNo(int index) {
        if (index < 0 || index >= obterComprimento()) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        int count = 0;
        NoLista<T> current = primeiro;
        while (count < index) {
            count++;
            current = current.getProximo();
        }
        return current;
    }

    /**
     * Retorna a representação em string da lista, no formato "valor1 -> valor2 -> ... -> null".
     *
     * @return string representando a lista encadeada
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoLista<T> current = primeiro;
        while (current != null) {
            sb.append(current.getInfo()).append(" -> ");
            current = current.getProximo();
        }
        sb.append("null");
        return sb.toString();
    }
}
