/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.main;

import scatterMap.model.NoMapa;

// Tabela hash que armazena pares chave-valor, utilizando listas encadeadas para resolver colisões.
public class MapaDispersao<T> {
    private ListaEncadeadaMapa<T>[] info;

    // Inicializa o mapa com o tamanho especificado
    public MapaDispersao(int tamanho) {
        this.info = new ListaEncadeadaMapa[tamanho];
    }

    // Calcula o índice no array usando a função hash
    private int calcularHash(int chave) {
        return chave % info.length;
    }

    // Insere um par chave-valor no mapa
    public void inserir(int chave, T value) {
        int indice = calcularHash(chave);

        if (info[indice] == null) {
            info[indice] = new ListaEncadeadaMapa<>();
        }

        NoMapa<T> noMapa = new NoMapa<>(chave, value);
        info[indice].inserir(noMapa);
    }

    // Remove o nó com a chave especificada
    public void remover(int chave) {
        int pos = calcularHash(chave);
        info[pos].retirar(chave);
    }

    // Busca e retorna o nó correspondente à chave, ou null se não existir
    public NoMapa<T> buscar(int chave){
        int indice = calcularHash(chave);

        if (info[indice] != null) {
            NoMapa<T> no = info[indice].buscar(chave);
            if (no != null) {
                return no;
            }
        }

        return null;
    }

    // Retorna o array de listas encadeadas (tabela hash)
    public ListaEncadeadaMapa<T>[] getInfo() {
        return info;
    }
}
