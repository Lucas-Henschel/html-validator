/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
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