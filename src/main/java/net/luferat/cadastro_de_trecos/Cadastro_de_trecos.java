package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Cadastro_de_trecos {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearScreen();
        mainMenu();
    }

    // Método que exibe o menu principal.
    public static void mainMenu() {
        System.out.println("Cadastro de Trecos\n");
        System.out.println("Menu:");
        System.out.println("\t[1] Listar todos");
        System.out.println("\t[2] Listar");
        System.out.println("\t[3] Novo");
        System.out.println("\t[4] Editar");
        System.out.println("\t[5] Apagar");
        System.out.println("\t[0] Sair");
        System.out.print("\nOpção: ");

        // Recebe a opção do teclado.
        String option = scanner.next();

        // Executa um método conforme a opção escolhida.
        switch (option) {
            case "0" ->
                exitProgram();
            case "1" ->
                listAll();
            case "2" ->
                listOne();
            case "3" ->
                newThing();
            case "4" ->
                editThing();
            case "5" ->
                deleteThing();
            default ->
                reloadMenu();
        }
    }

    // Encerra o programa.
    public static void exitProgram() {
        scanner.close();
        clearScreen();
        System.out.println("\n\nFui!\n\n");
        System.exit(0);
    }

    // Lista todos os trecos cadastrados.
    public static void listAll() {

        try {
            String sql = "SELECT * FROM things";
            Connection conn = DbConnection.dbConnect();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            System.out.println(" ");
            while (res.next()) {
                System.out.println(
                        "ID: " + res.getString("id") + "\n"
                        + "Nome: " + res.getString("name") + "\n"
                        + "Descrição: " + res.getString("description") + "\n"
                );
            }

            conn.close();
            stmt.close();
            res.close();

            System.out.println("Menu:\n\t[1] Menu principal\n\t[0] Sair\n");
            System.out.print("Opção: ");

            String option = scanner.next();

            switch (option) {
                case "0" ->
                    exitProgram();
                case "1" -> {
                    clearScreen();
                    mainMenu();
                }
                default ->
                    reloadMenu();
            }

        } catch (SQLException error) {
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

    // Lista um treco específico pelo Id.
    public static void listOne() {
    }

    // Cadastra um novo treco.
    public static void newThing() {
    }

    // Edita um treco pelo Id.
    public static void editThing() {
    }

    // Apaga um treco pelo Id.
    public static void deleteThing() {
    }

    // Recarrega o menu principal.
    public static void reloadMenu() {
        clearScreen(); // Limpa o terminal.
        System.out.println("Oooops! Opção inválida!\n");
        mainMenu();    // Mostra o menu.
    }

    // Limpa a tela do terminal.
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }
    }

}
