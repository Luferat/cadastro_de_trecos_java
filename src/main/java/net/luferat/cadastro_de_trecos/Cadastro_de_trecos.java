package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Cadastro_de_trecos {

    // Objeto que obtém dados do teclado.
    private static Scanner scanner = new Scanner(System.in);

    // Atributos para uso com banco de dados.
    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;

    // Views.
    private static String appName = "CADASTRO DE TRECOS";
    private static String line = "------------------------------------------------";

    // Método principal.
    public static void main(String[] args) {
        clearScreen();
        mainMenu();
    }

    // Exibe o menu principal.
    public static void mainMenu() {
        System.out.println(appName + "\n" + line);
        System.out.println("Menu:");
        System.out.println("\t[1] Listar todos\t[4] Editar");
        System.out.println("\t[2] Listar\t\t[5] Apagar");
        System.out.println("\t[3] Novo\t\t[0] Sair");
        System.out.println(line + line);
        System.out.print("Opção: ");

        // Recebe a opção do teclado.
        String option = scanner.next();

        // Executa um método conforme a opção escolhida.
        switch (option) {
            case "0":
                exitProgram();
                break;
            case "1":
                listAll();
                break;
            case "2":
                listOne();
                break;
            case "3":
                newThing();
                break;
            case "4":
                editThing();
                break;
            case "5":
                deleteThing();
                break;
            default:
                reloadMenu();
                break;
        }
    }

    // Encerra o programa.
    public static void exitProgram() {
        scanner.close();
        clearScreen();
        System.out.println("\n\nFui!\n\n");
        System.exit(0);
    }

    // Lista todos os registros cadastrados.
    public static void listAll() {

        try {
            String sql = "SELECT * FROM things"; // Query proposta.
            conn = DbConnection.dbConnect();     // Abra conexão.
            stmt = conn.createStatement();       // Inicia estado da conexão.
            res = stmt.executeQuery(sql);        // Executa a query.

            // Prepara interface (view).
            clearScreen();
            System.out.println(appName + "\n" + line);
            System.out.println("Listando todos os trecos");
            System.out.println(line);
            System.out.println(" ");

            // Loop que obtém cada registro, formata e exibe.
            while (res.next()) {
                System.out.println(
                        "ID: " + res.getString("id") + "\n"
                        + "  Nome: " + res.getString("name") + "\n"
                        + "  Descrição: " + res.getString("description") + "\n"
                );
            }

            // Fecha conexões abertas.
            DbConnection.dbClose(conn, stmt, pstm, res);

            // Menu da seção.
            System.out.println(line);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[0] Sair");
            System.out.println(line);
            System.out.print("Opção: ");
            String option = scanner.next();

            // Executa método conforme opção escolhida.
            switch (option) {
                case "0":
                    exitProgram();
                    break;
                case "1":
                    clearScreen();
                    mainMenu();
                    break;
                default:
                    reloadMenu();
                    break;
            }

        } catch (SQLException error) {

            // Tratamento de erros.
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
