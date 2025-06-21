/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package stack.exceptions;

// Exceção personalizada lançada quando uma pilha está vazia.
public class PilhaVaziaException extends RuntimeException {

    // Construtor padrão sem mensagem personalizada.
    public PilhaVaziaException() {
    }

    // Construtor que permite definir uma mensagem de erro personalizada.
    public PilhaVaziaException(String msg) {
        super(msg);
    }
}
