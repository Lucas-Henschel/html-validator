package enums;

/**
 * Enum que representa os tipos de tags HTML: de abertura e de fechamento.
 */
public enum TagEnum {

    /**
     * Tag de abertura, iniciada com {@code <}.
     */
    START_TAG("<"),

    /**
     * Tag de fechamento, iniciada com {@code </}.
     */
    END_TAG("</");

    /**
     * Prefixo da tag correspondente.
     */
    private final String description;

    /**
     * Construtor do enum.
     *
     * @param description prefixo que identifica o tipo de tag
     */
    TagEnum(String description) {
        this.description = description;
    }

    /**
     * Verifica se a tag informada é uma tag de abertura.
     *
     * @param tag a tag HTML a ser verificada
     * @return {@code true} se for uma tag de abertura, {@code false} caso contrário
     */
    public boolean isStartTag(String tag) {
        return tag.startsWith(START_TAG.description) && !tag.startsWith(END_TAG.description);
    }

    /**
     * Verifica se a tag informada é uma tag de fechamento.
     *
     * @param tag a tag HTML a ser verificada
     * @return {@code true} se for uma tag de fechamento, {@code false} caso contrário
     */
    public boolean isEndTag(String tag) {
        return tag.startsWith(END_TAG.description);
    }

    /**
     * Retorna a descrição (prefixo) do tipo de tag.
     *
     * @return descrição da tag
     */
    @Override
    public String toString() {
        return description;
    }
}
