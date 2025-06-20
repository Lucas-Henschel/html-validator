/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.model;

/**
 *
 * @author lucas
 */
public class NoMapa<T> {
    private int chave;
    private T info;
    private int count;
    
    private NoMapa proximo;

    public NoMapa() {
    }

    public NoMapa(int chave, T info) {
        this.chave = chave;
        this.info = info;
        this.count = 1;
    }
    
    public void addCount() {
        this.count++;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoMapa getProximo() {
        return proximo;
    }

    public void setProximo(NoMapa proximo) {
        this.proximo = proximo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

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
