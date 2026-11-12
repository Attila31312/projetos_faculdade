package estruturas.pilha;

import modelo.Livro;

public class PilhaDevolucoes {
    private NoDevolucao topo;
    private int tamanho;

    public PilhaDevolucoes() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void inserir(Livro livro) {
        NoDevolucao novo = new NoDevolucao(livro);
        novo.setAbaixo(topo);
        topo = novo;
        tamanho++;
    }

    public Livro remover() {
        if (estaVazia()) {
            return null;
        }
        Livro livro = topo.getLivro();
        topo = topo.getAbaixo();
        tamanho--;
        return livro;
    }

    public Livro consultarTopo() {
        return estaVazia() ? null : topo.getLivro();
    }

    public int buscar(long isbn) {
        int posicao = 1;
        NoDevolucao atual = topo;
        while (atual != null) {
            if (atual.getLivro().getIsbn() == isbn) {
                return posicao;
            }
            atual = atual.getAbaixo();
            posicao++;
        }
        return -1;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nenhuma devolução pendente de reposição.");
            return;
        }
        int posicao = 1;
        NoDevolucao atual = topo;
        while (atual != null) {
            String marcador = posicao == 1 ? " <- topo" : "";
            System.out.println(posicao + ". " + atual.getLivro().getTitulo()
                    + " (ISBN " + atual.getLivro().getIsbn() + ")" + marcador);
            atual = atual.getAbaixo();
            posicao++;
        }
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
