/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import stack.main.PilhaLista;

/**
 *
 * @author lucas
 */
public class RestoreOriginalStackUtil {
    public static void restoreOriginalStack(PilhaLista<String> stack, PilhaLista<String> tempStack) {
        while (!tempStack.estaVazia()) {
            stack.push(tempStack.pop());
        }
    }
}
