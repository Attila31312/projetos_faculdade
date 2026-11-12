package modelo;

import estruturas.fila.FilaReservas;

public class Livro {
    private final long isbn;
    private String titulo;
    private String autor;
    private StatusLivro status;
    private String leitorAtual;
    private final FilaReservas reservas;

    public Livro(long isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.status = StatusLivro.DISPONIVEL;
        this.leitorAtual = null;
        this.reservas = new FilaReservas();
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public String getLeitorAtual() {
        return leitorAtual;
    }

    public FilaReservas getReservas() {
        return reservas;
    }

    public boolean estaDisponivel() {
        return status == StatusLivro.DISPONIVEL;
    }

    public void emprestarPara(String leitor) {
        this.status = StatusLivro.EMPRESTADO;
        this.leitorAtual = leitor;
    }

    public void registrarDevolucao() {
        this.status = StatusLivro.AGUARDANDO_REPOSICAO;
        this.leitorAtual = null;
    }

    public void colocarNaPrateleira() {
        this.status = StatusLivro.DISPONIVEL;
    }

    @Override
    public String toString() {
        String texto = "ISBN " + isbn + " | " + titulo + " - " + autor + " | " + status.getDescricao();
        if (leitorAtual != null) {
            texto += " (com " + leitorAtual + ")";
        }
        if (!reservas.estaVazia()) {
            texto += " | Reservas: " + reservas.getTamanho();
        }
        return texto;
    }
}
