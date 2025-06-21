package enums;

// Enum que representa tipos de tags HTML: de abertura e de fechamento
public enum TagEnum {
    START_TAG("<"),     // Tag de abertura
    END_TAG("</");      // Tag de fechamento

    private final String description;

    TagEnum(String description) {
        this.description = description;
    }

    // Verifica se é uma tag de abertura
    public boolean isStartTag(String tag) {
        return tag.startsWith(START_TAG.description) && !tag.startsWith(END_TAG.description);
    }

    // Verifica se é uma tag de fechamento
    public boolean isEndTag(String tag) {
        return tag.startsWith(END_TAG.description);
    }

    // Retorna a descrição da tag
    @Override
    public String toString() {
        return description;
    }
}
