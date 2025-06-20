/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import enums.TagEnum;
import stack.main.PilhaLista;

/**
 *
 * @author lucas
 */
public class StartTagsUtil {
    public static int countStartTags(PilhaLista<String> stack) {
        PilhaLista<String> tempStack = new PilhaLista<>();
        int count = 0;
        
        while (!stack.estaVazia()) {
            String tag = stack.pop();
            tempStack.push(tag);
            
            if (TagEnum.START_TAG.isStartTag(tag)) {
                count++;
            }
        }
        
        RestoreOriginalStackUtil.restoreOriginalStack(stack, tempStack);
        
        return count;
    }
}
