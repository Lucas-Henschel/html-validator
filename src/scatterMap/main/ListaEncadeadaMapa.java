/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.main;

import scatterMap.model.NoMapa;

// Implementação de lista encadeada simples usada para armazenar nós do mapa hash.
public class ListaEncadeadaMapa<T> {
    private NoMapa<T> primeiro;

    // Construtor que inicia a lista vazia
    public ListaEncadeadaMapa() {
        primeiro = null;
    }

    // Retorna o primeiro nó da lista
    public NoMapa getPrimeiro() {
        return primeiro;
    }

    // Insere um nó no início da lista
    public void inserir(NoMapa<T> no) {
        no.setProximo(primeiro);
        this.primeiro = no;
    }

    // Verifica se a lista está vazia
    public boolean estaVazia() {
        return primeiro == null;
    }

    // Busca um nó pela chave
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

    // Exibe os elementos da lista no console
    public void exibir() {
        NoMapa<T> current = primeiro;

        while (current != null) {
            System.out.println(current.getInfo() + " -> ");
            current = current.getProximo();
        }

        System.out.println("null");
    }

    // Remove o nó com a chave especificada
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

    // Retorna o tamanho da lista
    public int obterComprimento() {
        int count = 0;
        NoMapa<T> current = primeiro;

        while (current != null) {
            count++;
            current = current.getProximo();
        }
        return count;
    }

    // Retorna o nó na posição index
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

    // Retorna a representação em string da lista
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoMapa<T> current = primeiro;

        while (current != null) {
            sb.append(current.getInfo())
                    .append(" -> ");
            current = current.getProximo();
        }

        sb.append("null");

        return sb.toString();
    }
}
