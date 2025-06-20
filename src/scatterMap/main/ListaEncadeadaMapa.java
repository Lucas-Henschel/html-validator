/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.main;

import scatterMap.model.NoMapa;

/**
 *
 * @author lucas
 */
public class ListaEncadeadaMapa<T> {
    private NoMapa<T> primeiro;

    public ListaEncadeadaMapa() {
        primeiro = null;
    }

    public NoMapa getPrimeiro() {
        return primeiro;
    }
    
    public void inserir (NoMapa<T> no) {        
        no.setProximo(primeiro);
        this.primeiro = no;
    }
    
    public boolean estaVazia() {
        return primeiro == null;
    }
    
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
    
    public void exibir() {
        NoMapa<T> current = primeiro;
        
        while (current != null) {
            System.out.println(current.getInfo() + " -> ");
            current = current.getProximo();
        }
        
        System.out.println("null");
    }
    
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
    
    public int obterComprimento() {
        int count = 0;
        NoMapa<T> current = primeiro;
        
        while (current != null) {
            count++;
            current = current.getProximo();
        }
        
        return count;
    }
    
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
