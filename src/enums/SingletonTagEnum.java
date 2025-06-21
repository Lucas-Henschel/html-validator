/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

// Enum que representa tags HTML que não possuem fechamento (self-closing).
public enum SingletonTagEnum {
    META("meta"),
    BASE("base"),
    BR("br"),
    COL("col"),
    COMMAND("command"),
    EMBED("embed"),
    HR("hr"),
    IMG("img"),
    INPUT("input"),
    LINK("link"),
    PARAM("param"),
    SOURCE("source"),
    DOCTYPE("!DOCTYPE");

    private final String tagName;

    SingletonTagEnum(String tagName) {
        this.tagName = tagName.toLowerCase();
    }

    // Retorna o nome da tag
    public String getTagName() {
        return tagName;
    }

    // Verifica se a tag informada é singleton
    public static boolean isSingleton(String tag) {
        if (tag == null) return false;

        String normalized = tag.replaceAll("<|>|/|\\s.*", "").toLowerCase();

        for (SingletonTagEnum tagName : SingletonTagEnum.values()) {
            if (tagName.getTagName().equals(normalized)) {
                return true;
            }
        }

        return false;
    }
}
