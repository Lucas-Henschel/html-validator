package stack.main;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilhaListaTest {

    /**
     * Test of push method, of class PilhaLista.
     */
    @Test
    public void testPush() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        assertEquals(false, pilhaLista.estaVazia());
    }

    /**
     * Test of pop method, of class PilhaLista.
     */
    @Test
    public void testPop() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        assertEquals(Integer.valueOf(30), pilhaLista.pop());
        assertEquals(Integer.valueOf(20), pilhaLista.pop());
        assertEquals(Integer.valueOf(10), pilhaLista.pop());

        assertEquals(true, pilhaLista.estaVazia());
    }

    /**
     * Test of peek method, of class PilhaLista.
     */
    @Test
    public void testPeek() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        assertEquals(Integer.valueOf(30), pilhaLista.peek());
        assertEquals(Integer.valueOf(30), pilhaLista.pop());
    }

    /**
     * Test of estaVazia method, of class PilhaLista.
     */
    @Test
    public void testEstaVazia() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        assertEquals(true, pilhaLista.estaVazia());
    }

    /**
     * Test of estaVazia method, of class PilhaLista.
     */
    @Test
    public void testNaoEstaVazia() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);

        assertEquals(false, pilhaLista.estaVazia());
    }

    /**
     * Test of liberar method, of class PilhaLista.
     */
    @Test
    public void testLiberar() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        pilhaLista.liberar();

        assertEquals(true, pilhaLista.estaVazia());
    }

    /**
     * Test of toString method, of class PilhaLista.
     */
    @Test
    public void testToString() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        assertEquals("30 -> 20 -> 10 -> null", pilhaLista.toString());
    }

    /**
     * Testa pop em pilha vazia - deve lançar exceção (se for essa a regra).
     */
    @Test(expected = RuntimeException.class)
    public void testPopEmPilhaVazia() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();
        pilhaLista.pop();
    }

    /**
     * Testa peek em pilha vazia - deve lançar exceção (se for essa a regra).
     */
    @Test(expected = RuntimeException.class)
    public void testPeekEmPilhaVazia() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();
        pilhaLista.peek();
    }

    /**
     * Testa push/pop alternado.
     */
    @Test
    public void testPushPopAlternado() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(1);
        assertEquals(Integer.valueOf(1), pilhaLista.pop());

        pilhaLista.push(2);
        pilhaLista.push(3);
        assertEquals(Integer.valueOf(3), pilhaLista.pop());

        pilhaLista.push(4);
        assertEquals(Integer.valueOf(4), pilhaLista.peek());
        assertEquals(Integer.valueOf(4), pilhaLista.pop());
        assertEquals(Integer.valueOf(2), pilhaLista.pop());

        assertTrue(pilhaLista.estaVazia());
    }

    /**
     * Testa reutilização da pilha após liberar.
     */
    @Test
    public void testReutilizarDepoisDeLiberar() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(5);
        pilhaLista.push(10);
        pilhaLista.liberar();
        assertTrue(pilhaLista.estaVazia());

        pilhaLista.push(20);
        assertFalse(pilhaLista.estaVazia());
        assertEquals(Integer.valueOf(20), pilhaLista.peek());
    }

    /**
     * Testa múltiplas liberações.
     */
    @Test
    public void testLiberarMultiplo() {
        PilhaLista<Integer> pilhaLista = new PilhaLista<>();

        pilhaLista.push(1);
        pilhaLista.push(2);
        pilhaLista.liberar();
        assertTrue(pilhaLista.estaVazia());

        pilhaLista.liberar();
        assertTrue(pilhaLista.estaVazia());
    }
}