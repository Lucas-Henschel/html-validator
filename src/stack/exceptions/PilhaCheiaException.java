package stack.exceptions;

/**
 * Exceção personalizada lançada quando uma pilha está cheia.
 */
public class PilhaCheiaException extends RuntimeException {

    /**
     * Construtor padrão da exceção.
     */
    public PilhaCheiaException() {
    }

    /**
     * Construtor da exceção com mensagem personalizada.
     *
     * @param msg mensagem descritiva do erro
     */
    public PilhaCheiaException(String msg) {
        super(msg);
    }
}
