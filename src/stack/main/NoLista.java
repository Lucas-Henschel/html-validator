package stack.main;

/**
 * Nó genérico que armazena um valor e referência para o próximo nó
 * em uma lista encadeada.
 *
 * @param <T> tipo do valor armazenado no nó
 */
public class NoLista<T> {

    /**
     * Valor armazenado no nó.
     */
    private T info;

    /**
     * Referência para o próximo nó na lista encadeada.
     */
    private NoLista<T> proximo;

    /**
     * Construtor padrão.
     */
    public NoLista() {
    }

    /**
     * Construtor que inicializa o nó com um valor.
     *
     * @param info valor a ser armazenado no nó
     */
    public NoLista(T info) {
        this.info = info;
        proximo = null;
    }

    /**
     * Retorna o valor armazenado no nó.
     *
     * @return valor do nó
     */
    public T getInfo() {
        return info;
    }

    /**
     * Define o valor armazenado no nó.
     *
     * @param info novo valor do nó
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Retorna o próximo nó da lista encadeada.
     *
     * @return próximo nó
     */
    public NoLista<T> getProximo() {
        return proximo;
    }

    /**
     * Define o próximo nó da lista encadeada.
     *
     * @param proximo referência para o próximo nó
     */
    public void setProximo(NoLista<T> proximo) {
        this.proximo = proximo;
    }
}
