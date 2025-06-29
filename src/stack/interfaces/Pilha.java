package stack.interfaces;

/**
 * Interface que define os métodos básicos para uma pilha genérica.
 *
 * @param <T> tipo dos elementos armazenados na pilha
 */
public interface Pilha<T> {

    /**
     * Adiciona um elemento no topo da pilha.
     *
     * @param info elemento a ser adicionado
     */
    void push(T info);

    /**
     * Remove e retorna o elemento que está no topo da pilha.
     *
     * @return elemento removido do topo da pilha
     */
    T pop();

    /**
     * Retorna o elemento que está no topo da pilha sem removê-lo.
     *
     * @return elemento do topo da pilha
     */
    T peek();

    /**
     * Verifica se a pilha está vazia.
     *
     * @return {@code true} se a pilha estiver vazia, {@code false} caso contrário
     */
    boolean estaVazia();

    /**
     * Remove todos os elementos da pilha, limpando seu conteúdo.
     */
    void liberar();
}
