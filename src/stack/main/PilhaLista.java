package stack.main;

import stack.exceptions.PilhaVaziaException;
import stack.interfaces.Pilha;

public class PilhaLista<T> implements Pilha<T> {
    private ListaEncadeadaPilha<T> listaEncadeada;

    public PilhaLista() {
        liberar();
    }

    @Override
    public void push(T info) {
        listaEncadeada.inserir(info);
    }

    @Override
    public T pop() {
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }

        T value = peek();
        listaEncadeada.retirar(value);

        return value;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }

        return (T) listaEncadeada.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return listaEncadeada.estaVazia();
    }

    @Override
    public void liberar() {
        this.listaEncadeada = new ListaEncadeadaPilha<>();
    }

    @Override
    public String toString() {
        return listaEncadeada.toString();
    }

    public ListaEncadeadaPilha<T> getListaEncadeada() {
        return listaEncadeada;
    }
}
