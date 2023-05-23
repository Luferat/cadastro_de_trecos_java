package net.luferat.cadastro_de_trecos;

import java.util.Scanner;

public class Cadastro_de_trecos {

    public static void main(String[] args) {
        System.out.println("Cadastro de Trecos\n");
        System.out.println("Menu:");
        System.out.println("\t[1] Listar todos");
        System.out.println("\t[2] Listar");
        System.out.println("\t[3] Novo");
        System.out.println("\t[4] Editar");
        System.out.println("\t[5] Apagar");
        System.out.println("\t[0] Sair");
        System.out.print("\nOpção: ");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        
        if(option.length() != 1) {
            System.out.println("Bizonhou!!!");
        }

    }
}
