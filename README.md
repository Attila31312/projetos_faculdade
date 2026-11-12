# Sistema de Biblioteca Pública

Trabalho Final da disciplina **Estrutura de Dados (2026.2)**, da Universidade Católica do Salvador.
O projeto implementa o **Tema 09: Sistema de Biblioteca Pública**.

O sistema gerencia o acervo de uma biblioteca: cadastro e busca de livros, empréstimos, reservas de livros emprestados e a reposição dos livros devolvidos nas prateleiras. Todas as estruturas de dados foram implementadas manualmente, **sem usar Collections do Java** (`ArrayList`, `LinkedList`, `Stack`, `Queue` etc.).

## Estruturas de dados

| Estrutura | Uso no sistema | Classe |
|---|---|---|
| **Árvore Binária de Busca** (principal) | Acervo de livros, com busca por ISBN | `ArvoreLivros` |
| **Fila** | Fila de reserva de cada livro emprestado (o primeiro a reservar é o primeiro a receber) | `FilaReservas` |
| **Pilha** | Livros devolvidos no dia, aguardando reposição (o último devolvido é o primeiro reposto) | `PilhaDevolucoes` |

### Métodos implementados

**Árvore Binária de Busca**
- `inserir`: insere respeitando a regra da ABB (menor à esquerda, maior à direita) e recusa ISBN repetido.
- `buscar`: localiza um livro pelo ISBN.
- `remover`: remove um livro, tratando os três casos: nó folha, nó com um filho e nó com dois filhos (usando o sucessor).
- `percursos`: exibe o acervo em ordem, pré-ordem e pós-ordem.

**Fila de reservas**
- `inserir`, `buscar` (posição do leitor), `remover` (próximo da fila), `removerLeitor` (cancelar reserva) e `listar`.

**Pilha de devoluções**
- `inserir`, `buscar` (posição do livro), `remover` (livro do topo), `consultarTopo` e `listar`.

## Como as estruturas interagem

1. Todas as operações localizam o livro na **árvore** pelo ISBN.
2. Ao pedir um empréstimo, se o livro está disponível, ele é emprestado. Se não está, o leitor entra na **fila** de reserva daquele livro.
3. Na devolução, o livro vai para a **pilha** de devoluções do dia e fica aguardando reposição.
4. Na reposição, sai o livro do topo da pilha. Se houver alguém na fila de reserva, o livro é emprestado direto para o primeiro da fila; caso contrário, volta para a prateleira.

```
DISPONÍVEL --emprestar--> EMPRESTADO --devolver--> AGUARDANDO REPOSIÇÃO (pilha)
    ^                         ^                              |
    |                         +--- repor, com reserva -------+
    +------------------------------ repor, sem reserva ------+
```

## Organização do projeto

```
src/
├── app/
│   ├── Main.java              Ponto de entrada
│   └── MenuBiblioteca.java    Menu e leitura do teclado
├── servico/
│   └── Biblioteca.java        Regras de negócio e integração das estruturas
├── modelo/
│   ├── Livro.java
│   └── StatusLivro.java
└── estruturas/
    ├── arvore/   ArvoreLivros.java, NoLivro.java
    ├── fila/     FilaReservas.java, NoReserva.java
    └── pilha/    PilhaDevolucoes.java, NoDevolucao.java
```

As classes de nó (`NoLivro`, `NoReserva` e `NoDevolucao`) só são visíveis dentro do próprio pacote. O restante do sistema acessa as estruturas apenas pelos seus métodos.

## Como executar

Requisito: **Java 17 ou superior**.

### No IntelliJ IDEA

1. Abra a pasta do projeto (*File → Open*).
2. Se a pasta `src` não estiver marcada como código-fonte, clique nela com o botão direito e escolha *Mark Directory as → Sources Root*.
3. Execute a classe `app.Main`.

### Pelo terminal

No Linux, macOS ou Git Bash:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main
```

No PowerShell:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out app.Main
```

## Funcionalidades do menu

O sistema começa com 5 livros cadastrados para facilitar os testes.

| Opção | Funcionalidade | Estrutura |
|---|---|---|
| 1 | Cadastrar livro | Árvore |
| 2 | Buscar livro por ISBN | Árvore |
| 3 | Remover livro (somente se disponível e sem reservas) | Árvore |
| 4 | Listar acervo em ordem de ISBN | Árvore |
| 5 | Exibir percursos em ordem, pré-ordem e pós-ordem | Árvore |
| 6 | Emprestar livro ou entrar na fila de reserva | Árvore + Fila |
| 7 | Ver a fila de reserva de um livro | Fila |
| 8 | Consultar a posição de um leitor na fila | Fila |
| 9 | Cancelar reserva | Fila |
| 10 | Devolver livro | Árvore + Pilha |
| 11 | Repor o próximo livro na prateleira | Pilha + Fila |
| 12 | Ver a pilha de devoluções | Pilha |
| 13 | Localizar um livro na pilha | Pilha |
| 0 | Sair | |

## Integrantes

| Nome | GitHub |
|---|---|
| | |
