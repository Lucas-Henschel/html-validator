package stack.main;

import stack.exceptions.PilhaVaziaException;
import stack.interfaces.Pilha;

/**
 * Implementação de pilha genérica usando uma lista encadeada como estrutura interna.
 *
 * @param <T> tipo dos elementos armazenados na pilha
 */
public class PilhaLista<T> implements Pilha<T> {

    /**
     * Lista encadeada interna que representa a pilha.
     */
    private ListaEncadeadaPilha<T> listaEncadeada;

    /**
     * Construtor que inicializa a pilha vazia.
     */
    public PilhaLista() {
        liberar();
    }

    /**
     * Insere um elemento no topo da pilha.
     *
     * @param info elemento a ser inserido
     */
    @Override
    public void push(T info) {
        listaEncadeada.inserir(info);
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     *
     * @return elemento removido do topo da pilha
     * @throws PilhaVaziaException se a pilha estiver vazia
     */
    @Override
    public T pop() {
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }
        T value = peek();
        listaEncadeada.retirar(value);
        return value;
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
     *
     * @return elemento no topo da pilha
     * @throws PilhaVaziaException se a pilha estiver vazia
     */
    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }
        return listaEncadeada.getPrimeiro().getInfo();
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return {@code true} se a pilha estiver vazia, {@code false} caso contrário
     */
    @Override
    public boolean estaVazia() {
        return listaEncadeada.estaVazia();
    }

    /**
     * Remove todos os elementos da pilha, limpando seu conteúdo.
     */
    @Override
    public void liberar() {
        this.listaEncadeada = new ListaEncadeadaPilha<>();
    }

    /**
     * Retorna a representação em string da pilha.
     *
     * @return string representando a pilha
     */
    @Override
    public String toString() {
        return listaEncadeada.toString();
    }

    /**
     * Retorna a lista encadeada interna que implementa a pilha.
     *
     * @return lista encadeada interna
     */
    public ListaEncadeadaPilha<T> getListaEncadeada() {
        return listaEncadeada;
    }
}
