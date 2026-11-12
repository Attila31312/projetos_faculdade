package estruturas.fila;

class NoReserva {
    private final String leitor;
    private NoReserva proximo;

    NoReserva(String leitor) {
        this.leitor = leitor;
        this.proximo = null;
    }

    String getLeitor() {
        return leitor;
    }

    NoReserva getProximo() {
        return proximo;
    }

    void setProximo(NoReserva proximo) {
        this.proximo = proximo;
    }
}
