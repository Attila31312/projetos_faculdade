package modelo;

public enum StatusLivro {
    DISPONIVEL("Disponível"),
    EMPRESTADO("Emprestado"),
    AGUARDANDO_REPOSICAO("Aguardando reposição");

    private final String descricao;

    StatusLivro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
