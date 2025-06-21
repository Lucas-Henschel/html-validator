package stack.main;

// Nó genérico que armazena um valor e referência para o próximo nó em uma lista encadeada.
public class NoLista<T> {
    private T info;
    private NoLista<T> proximo;

    // Construtor padrão
    public NoLista() {
    }

    // Construtor que inicializa o nó com um valor
    public NoLista(T info) {
        this.info = info;
        proximo = null;
    }

    // Retorna o valor armazenado no nó
    public T getInfo() {
        return info;
    }

    // Define o valor armazenado no nó
    public void setInfo(T info) {
        this.info = info;
    }

    // Retorna o próximo nó da lista
    public NoLista<T> getProximo() {
        return proximo;
    }

    // Define o próximo nó da lista
    public void setProximo(NoLista<T> proximo) {
        this.proximo = proximo;
    }
}
