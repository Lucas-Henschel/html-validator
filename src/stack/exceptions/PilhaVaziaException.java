/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package stack.exceptions;

public class PilhaVaziaException extends RuntimeException {

    public PilhaVaziaException() {
    }

    /**
     * Constructs an instance of <code>PilhaVaziaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PilhaVaziaException(String msg) {
        super(msg);
    }
}
