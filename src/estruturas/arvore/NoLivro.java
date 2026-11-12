package estruturas.arvore;

import modelo.Livro;

class NoLivro {
    private Livro livro;
    private NoLivro esquerda;
    private NoLivro direita;

    NoLivro(Livro livro) {
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }

    Livro getLivro() {
        return livro;
    }

    void setLivro(Livro livro) {
        this.livro = livro;
    }

    NoLivro getEsquerda() {
        return esquerda;
    }

    void setEsquerda(NoLivro esquerda) {
        this.esquerda = esquerda;
    }

    NoLivro getDireita() {
        return direita;
    }

    void setDireita(NoLivro direita) {
        this.direita = direita;
    }
}
