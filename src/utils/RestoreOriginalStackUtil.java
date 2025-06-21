/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import stack.main.PilhaLista;

// Utilitário para restaurar o conteúdo original de uma pilha a partir de uma pilha temporária.
public class RestoreOriginalStackUtil {
    // Restaura os elementos da pilha temporária para a pilha original, invertendo a ordem.
    public static void restoreOriginalStack(PilhaLista<String> stack, PilhaLista<String> tempStack) {
        while (!tempStack.estaVazia()) {
            stack.push(tempStack.pop());
        }
    }
}
