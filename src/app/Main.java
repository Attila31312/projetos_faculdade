package app;

import java.util.Scanner;
import servico.Biblioteca;

public class Main {
    public static void main(String[] args) {
        MenuBiblioteca menu = new MenuBiblioteca(new Biblioteca(), new Scanner(System.in));
        menu.iniciar();
    }
}
