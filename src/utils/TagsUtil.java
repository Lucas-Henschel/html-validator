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
public class TagsUtil {
    public static String getTagName(String tag) {
        if (tag == null) return "";
        
        return tag.replaceAll("</?|>", "")
            .replaceAll("\\s.*", "")
            .toLowerCase();
    }
    
    public static boolean isTagExist(PilhaLista<String> stack, String value) {
        PilhaLista<String> temp = new PilhaLista<>();
        
        boolean found = false;

        while (!stack.estaVazia()) {
            String current = stack.pop();
            if (current.equals(value)) found = true;
            
            temp.push(current);
        }

        while (!temp.estaVazia()) {
            stack.push(temp.pop());
        }
        
        return found;
    }
}
