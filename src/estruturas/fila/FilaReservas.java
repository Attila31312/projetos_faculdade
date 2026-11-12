package estruturas.fila;

public class FilaReservas {
    private NoReserva inicio;
    private NoReserva fim;
    private int tamanho;

    public FilaReservas() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void inserir(String leitor) {
        NoReserva novo = new NoReserva(leitor);
        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.setProximo(novo);
        }
        fim = novo;
        tamanho++;
    }

    public String remover() {
        if (estaVazia()) {
            return null;
        }
        String leitor = inicio.getLeitor();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return leitor;
    }

    public boolean removerLeitor(String leitor) {
        NoReserva anterior = null;
        NoReserva atual = inicio;
        while (atual != null && !atual.getLeitor().equalsIgnoreCase(leitor)) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual == null) {
            return false;
        }
        if (anterior == null) {
            inicio = atual.getProximo();
        } else {
            anterior.setProximo(atual.getProximo());
        }
        if (atual == fim) {
            fim = anterior;
        }
        tamanho--;
        return true;
    }

    public int buscar(String leitor) {
        int posicao = 1;
        NoReserva atual = inicio;
        while (atual != null) {
            if (atual.getLeitor().equalsIgnoreCase(leitor)) {
                return posicao;
            }
            atual = atual.getProximo();
            posicao++;
        }
        return -1;
    }

    public String consultarPrimeiro() {
        return estaVazia() ? null : inicio.getLeitor();
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nenhuma reserva.");
            return;
        }
        int posicao = 1;
        NoReserva atual = inicio;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.getLeitor());
            atual = atual.getProximo();
            posicao++;
        }
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
