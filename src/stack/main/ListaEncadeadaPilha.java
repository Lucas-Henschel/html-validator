package stack.main;

// Implementação de lista encadeada para uso em pilha genérica.
public class ListaEncadeadaPilha<T> {
    private NoLista<T> primeiro;

    // Inicializa lista vazia
    public ListaEncadeadaPilha() {
        primeiro = null;
    }

    // Retorna o primeiro nó da lista
    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    // Insere novo nó no início da lista
    public void inserir(T value) {
        NoLista<T> no = new NoLista<>(value);
        no.setProximo(primeiro);
        this.primeiro = no;
    }

    // Verifica se a lista está vazia
    public boolean estaVazia() {
        return primeiro == null;
    }

    // Busca nó contendo o valor informado
    public NoLista<T> buscar(T info) {
        NoLista<T> current = primeiro;
        while (current != null) {
            if (current.getInfo().equals(info)) {
                return current;
            }
            current = current.getProximo();
        }
        return null;
    }

    // Exibe os elementos da lista no console
    public void exibir() {
        NoLista<T> current = primeiro;
        while (current != null) {
            System.out.println(current.getInfo() + " -> ");
            current = current.getProximo();
        }
        System.out.println("null");
    }

    // Remove o nó com o valor especificado
    public void retirar(T value) {
        NoLista<T> last = null;
        NoLista<T> current = primeiro;
        while (current != null && !current.getInfo().equals(value)) {
            last = current;
            current = current.getProximo();
        }
        if (current != null) {
            if (last == null) {
                primeiro = current.getProximo();
            } else {
                last.setProximo(current.getProximo());
            }
        }
    }

    // Retorna o tamanho da lista
    public int obterComprimento() {
        int count = 0;
        NoLista<T> current = primeiro;
        while (current != null) {
            count++;
            current = current.getProximo();
        }
        return count;
    }

    // Retorna o nó na posição index
    public NoLista<T> obterNo(int index) {
        if (index < 0 || index >= obterComprimento()) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        int count = 0;
        NoLista<T> current = primeiro;
        while (count < index) {
            count++;
            current = current.getProximo();
        }
        return current;
    }

    // Representação em string da lista
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoLista<T> current = primeiro;
        while (current != null) {
            sb.append(current.getInfo()).append(" -> ");
            current = current.getProximo();
        }
        sb.append("null");
        return sb.toString();
    }
}
