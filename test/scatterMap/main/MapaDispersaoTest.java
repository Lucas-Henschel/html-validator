package scatterMap.main;

import org.junit.Before;
import org.junit.Test;
import scatterMap.model.NoMapa;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a funcionalidade da classe {@link MapaDispersao}, que implementa um mapa baseado em hashing
 * com tratamento de colisões via listas encadeadas.
 */
public class MapaDispersaoTest {
    private MapaDispersao<String> mapa;

    /**
     * Inicializa o mapa com tamanho 10 antes de cada teste.
     */
    @Before
    public void setUp() {
        mapa = new MapaDispersao<>(10);
    }

    /**
     * Testa inserção e busca de elementos sem colisão.
     */
    @Test
    public void testInserirEBuscar() {
        mapa.inserir(15, "valor1");
        mapa.inserir(25, "valor2");

        NoMapa<String> resultado1 = mapa.buscar(15);
        assertNotNull(resultado1);
        assertEquals("valor1", resultado1.getInfo());

        NoMapa<String> resultado2 = mapa.buscar(25);
        assertNotNull(resultado2);
        assertEquals("valor2", resultado2.getInfo());
    }

    /**
     * Testa a remoção de um elemento e garante que não pode mais ser buscado.
     */
    @Test
    public void testRemover() {
        mapa.inserir(12, "valorRemover");
        assertNotNull(mapa.buscar(12));

        mapa.remover(12);
        assertNull(mapa.buscar(12));
    }

    /**
     * Testa a busca por uma chave que nunca foi inserida.
     */
    @Test
    public void testBuscarInexistente() {
        assertNull(mapa.buscar(999));
    }

    /**
     * Testa inserção de chaves que causam colisão e garante que ambas são armazenadas corretamente.
     */
    @Test
    public void testColisao() {
        mapa.inserir(1, "valor1");
        mapa.inserir(11, "valor2");

        assertEquals("valor1", mapa.buscar(1).getInfo());
        assertEquals("valor2", mapa.buscar(11).getInfo());
    }

    /**
     * Testa sobrescrita de uma chave já existente no mapa (atualmente, insere duplicado? depende da implementação).
     */
    @Test
    public void testSobrescreverValor() {
        mapa.inserir(5, "valor1");
        mapa.inserir(5, "valor2");

        NoMapa<String> resultado = mapa.buscar(5);
        assertEquals("valor2", resultado.getInfo());
    }

    /**
     * Testa inserção e remoção de múltiplas chaves no mesmo índice (testa estrutura de colisão).
     */
    @Test
    public void testRemoverComColisao() {
        mapa.inserir(3, "A");
        mapa.inserir(13, "B");

        mapa.remover(3);

        assertNull(mapa.buscar(3));
        assertNotNull(mapa.buscar(13));
        assertEquals("B", mapa.buscar(13).getInfo());
    }

    /**
     * Testa a remoção de uma chave inexistente (não deve causar erro).
     */
    @Test
    public void testRemoverChaveInexistente() {
        mapa.inserir(2, "X");
        mapa.remover(999);
        assertEquals("X", mapa.buscar(2).getInfo());
    }

    /**
     * Testa se o array interno do mapa tem o tamanho esperado.
     */
    @Test
    public void testTamanhoDoArray() {
        assertEquals(10, mapa.getInfo().length);
    }

    /**
     * Testa inserção de valor nulo (depende da regra do sistema, aqui consideramos válido).
     */
    @Test
    public void testInserirValorNulo() {
        mapa.inserir(7, null);
        assertNull(mapa.buscar(7).getInfo());
    }
}
