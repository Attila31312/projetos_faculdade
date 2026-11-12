package servico;

import estruturas.arvore.ArvoreLivros;
import estruturas.pilha.PilhaDevolucoes;
import modelo.Livro;
import modelo.StatusLivro;

public class Biblioteca {
    private final ArvoreLivros acervo;
    private final PilhaDevolucoes devolucoesDoDia;

    public Biblioteca() {
        this.acervo = new ArvoreLivros();
        this.devolucoesDoDia = new PilhaDevolucoes();
    }

    public String cadastrarLivro(long isbn, String titulo, String autor) {
        if (acervo.inserir(new Livro(isbn, titulo, autor))) {
            return "Livro \"" + titulo + "\" cadastrado.";
        }
        return "Já existe um livro com o ISBN " + isbn + ".";
    }

    public Livro buscarLivro(long isbn) {
        return acervo.buscar(isbn);
    }

    public String removerLivro(long isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        if (!livro.estaDisponivel()) {
            return "Não é possível remover: o livro está " + livro.getStatus().getDescricao().toLowerCase() + ".";
        }
        if (!livro.getReservas().estaVazia()) {
            return "Não é possível remover: há leitores na fila de reserva.";
        }
        acervo.remover(isbn);
        return "Livro \"" + livro.getTitulo() + "\" removido do acervo.";
    }

    public void listarAcervo() {
        System.out.println("Total de livros: " + acervo.getQuantidade());
        acervo.emOrdem();
    }

    public void exibirPercursos() {
        acervo.percursos();
    }

    public String emprestarLivro(long isbn, String leitor) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        if (livro.estaDisponivel()) {
            livro.emprestarPara(leitor);
            return "Livro \"" + livro.getTitulo() + "\" emprestado para " + leitor + ".";
        }
        if (leitor.equalsIgnoreCase(livro.getLeitorAtual())) {
            return leitor + " já está com este livro.";
        }
        int posicaoAtual = livro.getReservas().buscar(leitor);
        if (posicaoAtual != -1) {
            return leitor + " já está na fila de reserva (posição " + posicaoAtual + ").";
        }
        livro.getReservas().inserir(leitor);
        return "Livro indisponível. " + leitor + " entrou na fila de reserva na posição "
                + livro.getReservas().getTamanho() + ".";
    }

    public String devolverLivro(long isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        if (livro.getStatus() != StatusLivro.EMPRESTADO) {
            return "Este livro não está emprestado.";
        }
        String leitor = livro.getLeitorAtual();
        livro.registrarDevolucao();
        devolucoesDoDia.inserir(livro);
        return "Devolução de " + leitor + " registrada. \"" + livro.getTitulo()
                + "\" foi para a pilha de reposição (" + devolucoesDoDia.getTamanho() + " livro(s) na pilha).";
    }

    public String reporProximoLivro() {
        Livro livro = devolucoesDoDia.remover();
        if (livro == null) {
            return "Não há livros aguardando reposição.";
        }
        String proximoLeitor = livro.getReservas().remover();
        if (proximoLeitor != null) {
            livro.emprestarPara(proximoLeitor);
            return "\"" + livro.getTitulo() + "\" tinha reserva e foi emprestado para " + proximoLeitor + ".";
        }
        livro.colocarNaPrateleira();
        return "\"" + livro.getTitulo() + "\" reposto na prateleira.";
    }

    public String consultarReserva(long isbn, String leitor) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        int posicao = livro.getReservas().buscar(leitor);
        if (posicao == -1) {
            return leitor + " não está na fila de reserva deste livro.";
        }
        return leitor + " está na posição " + posicao + " da fila de \"" + livro.getTitulo() + "\".";
    }

    public String cancelarReserva(long isbn, String leitor) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return "Livro não encontrado.";
        }
        if (livro.getReservas().removerLeitor(leitor)) {
            return "Reserva de " + leitor + " cancelada.";
        }
        return leitor + " não está na fila de reserva deste livro.";
    }

    public boolean listarReservas(long isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            return false;
        }
        System.out.println("Fila de reserva de \"" + livro.getTitulo() + "\":");
        livro.getReservas().listar();
        return true;
    }

    public void listarDevolucoes() {
        devolucoesDoDia.listar();
    }

    public String localizarDevolucao(long isbn) {
        int posicao = devolucoesDoDia.buscar(isbn);
        if (posicao == -1) {
            return "O livro não está na pilha de devoluções.";
        }
        return "O livro está na posição " + posicao + " da pilha (1 = topo, próximo a ser reposto).";
    }
}
