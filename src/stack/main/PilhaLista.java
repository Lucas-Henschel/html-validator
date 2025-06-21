package stack.main;

import stack.exceptions.PilhaVaziaException;
import stack.interfaces.Pilha;

// Implementação de pilha genérica usando uma lista encadeada como estrutura interna.
public class PilhaLista<T> implements Pilha<T> {
    private ListaEncadeadaPilha<T> listaEncadeada;

    public PilhaLista() {
        liberar();
    }

    @Override
    public void push(T info) {
        // Insere elemento no topo da pilha
        listaEncadeada.inserir(info);
    }

    @Override
    public T pop() {
        // Remove e retorna o elemento do topo; lança exceção se pilha vazia
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }
        T value = peek();
        listaEncadeada.retirar(value);
        return value;
    }

    @Override
    public T peek() {
        // Retorna o elemento do topo sem remover; lança exceção se pilha vazia
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }
        return listaEncadeada.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        // Verifica se a pilha está vazia
        return listaEncadeada.estaVazia();
    }

    @Override
    public void liberar() {
        // Inicializa ou limpa a pilha criando uma nova lista encadeada
        this.listaEncadeada = new ListaEncadeadaPilha<>();
    }

    @Override
    public String toString() {
        // Retorna representação em string da pilha
        return listaEncadeada.toString();
    }

    public ListaEncadeadaPilha<T> getListaEncadeada() {
        // Retorna a lista encadeada interna
        return listaEncadeada;
    }
}
