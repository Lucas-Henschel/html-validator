/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import enums.TagEnum;
import stack.main.PilhaLista;

// Utilitário para contar quantas tags de início existem em uma pilha de strings.
public class StartTagsUtil {
    // Conta o número de tags que são tags de início na pilha fornecida.
    public static int countStartTags(PilhaLista<String> stack) {
        PilhaLista<String> tempStack = new PilhaLista<>();
        int count = 0;

        // Percorre a pilha original, contando tags de início e copiando para tempStack
        while (!stack.estaVazia()) {
            String tag = stack.pop();
            tempStack.push(tag);

            if (TagEnum.START_TAG.isStartTag(tag)) {
                count++;
            }
        }

        // Restaura a pilha original com os elementos da pilha temporária
        RestoreOriginalStackUtil.restoreOriginalStack(stack, tempStack);

        return count;
    }
}