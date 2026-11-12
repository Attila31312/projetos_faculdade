package estruturas.arvore;

import modelo.Livro;

public class ArvoreLivros {
    private NoLivro raiz;
    private int quantidade;

    public ArvoreLivros() {
        this.raiz = null;
        this.quantidade = 0;
    }

    public boolean inserir(Livro livro) {
        if (buscar(livro.getIsbn()) != null) {
            return false;
        }
        raiz = inserirRec(raiz, livro);
        quantidade++;
        return true;
    }

    private NoLivro inserirRec(NoLivro atual, Livro livro) {
        if (atual == null) {
            return new NoLivro(livro);
        }
        if (livro.getIsbn() < atual.getLivro().getIsbn()) {
            atual.setEsquerda(inserirRec(atual.getEsquerda(), livro));
        } else {
            atual.setDireita(inserirRec(atual.getDireita(), livro));
        }
        return atual;
    }

    public Livro buscar(long isbn) {
        NoLivro atual = raiz;
        while (atual != null) {
            long isbnAtual = atual.getLivro().getIsbn();
            if (isbn == isbnAtual) {
                return atual.getLivro();
            }
            atual = isbn < isbnAtual ? atual.getEsquerda() : atual.getDireita();
        }
        return null;
    }

    public boolean remover(long isbn) {
        if (buscar(isbn) == null) {
            return false;
        }
        raiz = removerRec(raiz, isbn);
        quantidade--;
        return true;
    }

    private NoLivro removerRec(NoLivro atual, long isbn) {
        if (atual == null) {
            return null;
        }
        long isbnAtual = atual.getLivro().getIsbn();
        if (isbn < isbnAtual) {
            atual.setEsquerda(removerRec(atual.getEsquerda(), isbn));
            return atual;
        }
        if (isbn > isbnAtual) {
            atual.setDireita(removerRec(atual.getDireita(), isbn));
            return atual;
        }
        if (atual.getEsquerda() == null) {
            return atual.getDireita();
        }
        if (atual.getDireita() == null) {
            return atual.getEsquerda();
        }
        NoLivro sucessor = menorNo(atual.getDireita());
        atual.setLivro(sucessor.getLivro());
        atual.setDireita(removerRec(atual.getDireita(), sucessor.getLivro().getIsbn()));
        return atual;
    }

    private NoLivro menorNo(NoLivro atual) {
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public void percursos() {
        if (estaVazia()) {
            System.out.println("Acervo vazio.");
            return;
        }
        System.out.println("--- Em ordem ---");
        emOrdem(raiz);
        System.out.println("--- Pré-ordem ---");
        preOrdem(raiz);
        System.out.println("--- Pós-ordem ---");
        posOrdem(raiz);
    }

    public void emOrdem() {
        if (estaVazia()) {
            System.out.println("Acervo vazio.");
            return;
        }
        emOrdem(raiz);
    }

    private void emOrdem(NoLivro no) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getLivro());
        emOrdem(no.getDireita());
    }

    private void preOrdem(NoLivro no) {
        if (no == null) {
            return;
        }
        System.out.println(no.getLivro());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void posOrdem(NoLivro no) {
        if (no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getLivro());
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
