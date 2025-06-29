/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scatterMap.main;

import scatterMap.model.NoMapa;

/**
 * Tabela hash que armazena pares chave-valor, utilizando
 * listas encadeadas para tratamento de colisões (hashing com encadeamento).
 *
 * @param <T> tipo dos valores armazenados nos nós
 */
public class MapaDispersao<T> {

    /**
     * Array de listas encadeadas que compõem a tabela hash.
     */
    private ListaEncadeadaMapa<T>[] info;

    /**
     * Construtor que inicializa o mapa com o tamanho especificado.
     *
     * @param tamanho tamanho da tabela hash (número de posições)
     */
    public MapaDispersao(int tamanho) {
        this.info = new ListaEncadeadaMapa[tamanho];
    }

    /**
     * Calcula o índice do array com base na chave informada,
     * utilizando a função de dispersão (função hash).
     *
     * @param chave chave a ser convertida em índice
     * @return índice correspondente na tabela hash
     */
    private int calcularHash(int chave) {
        return Math.floorMod(chave, info.length);
    }

    /**
     * Insere um par chave-valor no mapa.
     * Se houver colisão, o valor é inserido na lista encadeada correspondente.
     *
     * @param chave chave do elemento
     * @param value valor associado à chave
     */
    public void inserir(int chave, T value) {
        int indice = calcularHash(chave);

        if (info[indice] == null) {
            info[indice] = new ListaEncadeadaMapa<>();
        }

        NoMapa<T> noMapa = new NoMapa<>(chave, value);
        info[indice].inserir(noMapa);
    }

    public void remover(int chave) {
        int pos = calcularHash(chave);

        if (info[pos] != null) {
            info[pos].retirar(chave);
        }
    }

    /**
     * Busca e retorna o nó correspondente à chave informada.
     *
     * @param chave chave a ser buscada
     * @return nó com a chave correspondente ou {@code null} se não encontrado
     */
    public NoMapa<T> buscar(int chave){
        int indice = calcularHash(chave);
        
        if (info[indice] != null) {
            NoMapa<T> noMapa = new NoMapa<>();
            noMapa.setChave(chave);
            
            NoMapa<T> no = info[indice].buscar(chave);
            
            if (no != null) {
                return no;
            }
        }

        return null;
    }

    /**
     * Retorna o array interno de listas encadeadas (estrutura da tabela hash).
     *
     * @return array de listas encadeadas
     */
    public ListaEncadeadaMapa<T>[] getInfo() {
        return info;
    }
}
