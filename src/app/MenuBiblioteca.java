package app;

import java.util.Scanner;
import modelo.Livro;
import servico.Biblioteca;

public class MenuBiblioteca {
    private final Scanner entrada;
    private final Biblioteca biblioteca;

    public MenuBiblioteca(Biblioteca biblioteca, Scanner entrada) {
        this.biblioteca = biblioteca;
        this.entrada = entrada;
    }

    public void iniciar() {
        carregarAcervoInicial();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Opção: ");
            System.out.println();
            executar(opcao);
            System.out.println();
        } while (opcao != 0);
    }

    private void carregarAcervoInicial() {
        biblioteca.cadastrarLivro(9788535914849L, "Dom Casmurro", "Machado de Assis");
        biblioteca.cadastrarLivro(9788503012302L, "Grande Sertão: Veredas", "João Guimarães Rosa");
        biblioteca.cadastrarLivro(9788535921236L, "Vidas Secas", "Graciliano Ramos");
        biblioteca.cadastrarLivro(9788520932070L, "Capitães da Areia", "Jorge Amado");
        biblioteca.cadastrarLivro(9788582850350L, "A Hora da Estrela", "Clarice Lispector");
    }

    private void exibirMenu() {
        System.out.println("===== BIBLIOTECA PÚBLICA =====");
        System.out.println("-- Acervo (Árvore) --");
        System.out.println(" 1. Cadastrar livro");
        System.out.println(" 2. Buscar livro por ISBN");
        System.out.println(" 3. Remover livro");
        System.out.println(" 4. Listar acervo");
        System.out.println(" 5. Exibir percursos da árvore");
        System.out.println("-- Empréstimos e Reservas (Fila) --");
        System.out.println(" 6. Emprestar / reservar livro");
        System.out.println(" 7. Ver fila de reserva de um livro");
        System.out.println(" 8. Consultar posição na fila");
        System.out.println(" 9. Cancelar reserva");
        System.out.println("-- Devoluções do dia (Pilha) --");
        System.out.println("10. Devolver livro");
        System.out.println("11. Repor próximo livro na prateleira");
        System.out.println("12. Ver pilha de devoluções");
        System.out.println("13. Localizar livro na pilha");
        System.out.println(" 0. Sair");
    }

    private void executar(int opcao) {
        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> buscar();
            case 3 -> System.out.println(biblioteca.removerLivro(lerIsbn()));
            case 4 -> biblioteca.listarAcervo();
            case 5 -> biblioteca.exibirPercursos();
            case 6 -> {
                long isbn = lerIsbn();
                System.out.println(biblioteca.emprestarLivro(isbn, lerTexto("Nome do leitor: ")));
            }
            case 7 -> {
                if (!biblioteca.listarReservas(lerIsbn())) {
                    System.out.println("Livro não encontrado.");
                }
            }
            case 8 -> {
                long isbn = lerIsbn();
                System.out.println(biblioteca.consultarReserva(isbn, lerTexto("Nome do leitor: ")));
            }
            case 9 -> {
                long isbn = lerIsbn();
                System.out.println(biblioteca.cancelarReserva(isbn, lerTexto("Nome do leitor: ")));
            }
            case 10 -> System.out.println(biblioteca.devolverLivro(lerIsbn()));
            case 11 -> System.out.println(biblioteca.reporProximoLivro());
            case 12 -> biblioteca.listarDevolucoes();
            case 13 -> System.out.println(biblioteca.localizarDevolucao(lerIsbn()));
            case 0 -> System.out.println("Encerrando o sistema.");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void cadastrar() {
        long isbn = lerIsbn();
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        System.out.println(biblioteca.cadastrarLivro(isbn, titulo, autor));
    }

    private void buscar() {
        Livro livro = biblioteca.buscarLivro(lerIsbn());
        System.out.println(livro == null ? "Livro não encontrado." : livro);
    }

    private long lerIsbn() {
        while (true) {
            String texto = lerTexto("ISBN: ").replace("-", "");
            try {
                long isbn = Long.parseLong(texto);
                if (isbn > 0) {
                    return isbn;
                }
            } catch (NumberFormatException ignorada) {
            }
            System.out.println("ISBN inválido. Digite apenas números.");
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            if (!entrada.hasNextLine()) {
                System.exit(0);
            }
            texto = entrada.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }
}
