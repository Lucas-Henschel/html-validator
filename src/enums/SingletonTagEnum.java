/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 * Enum que representa tags HTML que não possuem fechamento (self-closing).
 */
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

    /**
     * Nome da tag HTML representada.
     */
    private final String tagName;

    /**
     * Construtor do enum.
     *
     * @param tagName nome da tag HTML
     */
    SingletonTagEnum(String tagName) {
        this.tagName = tagName.toLowerCase();
    }

    /**
     * Retorna o nome da tag HTML.
     *
     * @return nome da tag
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Verifica se a tag informada é uma tag singleton (self-closing).
     *
     * <p>O método ignora símbolos como &lt;, &gt;, / e espaços, e faz a comparação em letras minúsculas.</p>
     *
     * @param tag string com a tag a ser verificada
     * @return {@code true} se a tag for singleton, {@code false} caso contrário
     */
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
