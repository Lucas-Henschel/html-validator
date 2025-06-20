/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author lucas
 */
public enum TagEnum {
    START_TAG("<"),
    END_TAG("</");
    
    private final String description;

    TagEnum(String description) {
        this.description = description;
    }
    
    public boolean isStartTag(String tag) {
        return tag.startsWith(START_TAG.description) && !tag.startsWith(END_TAG.description);
    }

    public boolean isEndTag(String tag) {
        return tag.startsWith(END_TAG.description);
    }
    
    @Override
    public String toString() {
        return description;
    }
}
