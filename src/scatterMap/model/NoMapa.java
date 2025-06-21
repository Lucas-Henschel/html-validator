package scatterMap.model;

/**
 * Nó genérico usado na lista encadeada da tabela hash,
 * armazenando uma chave, um valor, um contador de ocorrências
 * e a referência para o próximo nó.
 *
 * @param <T> tipo do valor armazenado no nó
 */
public class NoMapa<T> {

    /**
     * Chave associada ao nó.
     */
    private int chave;

    /**
     * Valor armazenado no nó.
     */
    private T info;

    /**
     * Contador de ocorrências ou usos do nó.
     */
    private int count;

    /**
     * Referência para o próximo nó na lista encadeada.
     */
    private NoMapa<T> proximo;

    /**
     * Construtor padrão.
     */
    public NoMapa() {
    }

    /**
     * Construtor que inicializa o nó com a chave e o valor.
     * O contador de ocorrências é inicializado com 1.
     *
     * @param chave chave do nó
     * @param info  valor armazenado no nó
     */
    public NoMapa(int chave, T info) {
        this.chave = chave;
        this.info = info;
        this.count = 1;
    }

    /**
     * Incrementa o contador de ocorrências do nó.
     */
    public void addCount() {
        this.count++;
    }

    /**
     * Retorna a chave do nó.
     *
     * @return chave do nó
     */
    public int getChave() {
        return chave;
    }

    /**
     * Define a chave do nó.
     *
     * @param chave nova chave
     */
    public void setChave(int chave) {
        this.chave = chave;
    }

    /**
     * Retorna o valor armazenado no nó.
     *
     * @return valor do nó
     */
    public T getInfo() {
        return info;
    }

    /**
     * Define o valor armazenado no nó.
     *
     * @param info novo valor
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Retorna o próximo nó da lista encadeada.
     *
     * @return próximo nó
     */
    public NoMapa<T> getProximo() {
        return proximo;
    }

    /**
     * Define o próximo nó da lista encadeada.
     *
     * @param proximo referência ao próximo nó
     */
    public void setProximo(NoMapa<T> proximo) {
        this.proximo = proximo;
    }

    /**
     * Retorna o contador de ocorrências do nó.
     *
     * @return contador do nó
     */
    public int getCount() {
        return count;
    }

    /**
     * Define o contador de ocorrências do nó.
     *
     * @param count novo valor do contador
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retorna um código hash fixo.
     * <p><b>Nota:</b> Este método pode ser melhorado para gerar
     * um código mais representativo com base nos atributos do nó.</p>
     *
     * @return valor fixo de hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Compara este nó com outro objeto com base na chave.
     *
     * @param obj objeto a ser comparado
     * @return {@code true} se as chaves forem iguais, {@code false} caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final NoMapa<?> other = (NoMapa<?>) obj;
        return this.chave == other.chave;
    }
}