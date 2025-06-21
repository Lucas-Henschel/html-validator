/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.model;

// Nó genérico usado na lista encadeada do mapa hash, armazenando chave, valor, contador e referência para o próximo nó.
public class NoMapa<T> {
    private int chave;
    private T info;
    private int count;
    private NoMapa proximo;

    public NoMapa() {
    }

    // Construtor que inicializa nó com chave e valor, count começa em 1
    public NoMapa(int chave, T info) {
        this.chave = chave;
        this.info = info;
        this.count = 1;
    }

    // Incrementa o contador de ocorrências
    public void addCount() {
        this.count++;
    }

    // Retorna a chave do nó
    public int getChave() {
        return chave;
    }

    // Define a chave do nó
    public void setChave(int chave) {
        this.chave = chave;
    }

    // Retorna o valor armazenado no nó
    public T getInfo() {
        return info;
    }

    // Define o valor armazenado no nó
    public void setInfo(T info) {
        this.info = info;
    }

    // Retorna o próximo nó da lista encadeada
    public NoMapa getProximo() {
        return proximo;
    }

    // Define o próximo nó da lista encadeada
    public void setProximo(NoMapa proximo) {
        this.proximo = proximo;
    }

    // Retorna o contador de ocorrências do nó
    public int getCount() {
        return count;
    }

    // Define o contador de ocorrências do nó
    public void setCount(int count) {
        this.count = count;
    }

    // Retorna o código hash do objeto (aqui está fixo, pode ser melhorado)
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    // Verifica se dois nós são iguais comparando a chave
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NoMapa<?> other = (NoMapa<?>) obj;
        return this.chave == other.chave;
    }
}
