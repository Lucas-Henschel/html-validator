package stack.interfaces;

// Interface que define os métodos básicos de uma pilha genérica.
public interface Pilha<T> {
    // Adiciona um elemento no topo da pilha
    void push(T info);

    // Remove e retorna o elemento do topo da pilha
    T pop();

    // Retorna o elemento do topo sem removê-lo
    T peek();

    // Verifica se a pilha está vazia
    boolean estaVazia();

    // Limpa todos os elementos da pilha
    void liberar();
}
