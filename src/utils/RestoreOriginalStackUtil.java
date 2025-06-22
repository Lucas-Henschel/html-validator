/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import stack.main.PilhaLista;

/**
 * Utilitário para restaurar o conteúdo original de uma pilha
 * a partir de uma pilha temporária, invertendo a ordem dos elementos.
 */
public class RestoreOriginalStackUtil {

    /**
     * Restaura os elementos da pilha temporária para a pilha original,
     * garantindo que a ordem original seja mantida.
     *
     * @param stack pilha original que será restaurada
     * @param tempStack pilha temporária contendo os elementos a serem restaurados
     */
    public static void restoreOriginalStack(PilhaLista<String> stack, PilhaLista<String> tempStack) {
        while (!tempStack.estaVazia()) {
            stack.push(tempStack.pop());
        }
    }
}
