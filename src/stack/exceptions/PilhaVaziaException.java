package stack.exceptions;

/**
 * Exceção personalizada lançada quando uma pilha está vazia.
 */
public class PilhaVaziaException extends RuntimeException {

    /**
     * Construtor padrão da exceção.
     */
    public PilhaVaziaException() {
    }

    /**
     * Construtor da exceção com mensagem personalizada.
     *
     * @param msg mensagem descritiva do erro
     */
    public PilhaVaziaException(String msg) {
        super(msg);
    }
}