package estruturas.pilha;

import modelo.Livro;

class NoDevolucao {
    private final Livro livro;
    private NoDevolucao abaixo;

    NoDevolucao(Livro livro) {
        this.livro = livro;
        this.abaixo = null;
    }

    Livro getLivro() {
        return livro;
    }

    NoDevolucao getAbaixo() {
        return abaixo;
    }

    void setAbaixo(NoDevolucao abaixo) {
        this.abaixo = abaixo;
    }
}
