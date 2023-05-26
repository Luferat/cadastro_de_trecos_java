package net.luferat.cadastro_de_trecos;

import net.luferat.cadastro_de_trecos.setup.AppSetup;
import net.luferat.cadastro_de_trecos.crud.Read;
import java.util.Scanner;
import net.luferat.cadastro_de_trecos.crud.Delete;

public class Cadastro_de_trecos extends AppSetup {

    public static void main(String[] args) {
        clearScreen();
        mainMenu();
    }

    // Método que exibe o menu principal.
    public static void mainMenu() {

        System.out.println(appName + "\n" + appSep);
        System.out.println("Menu:");
        System.out.println("\t[1] Listar todos");
        System.out.println("\t[2] Listar");
        System.out.println("\t[3] Novo");
        System.out.println("\t[4] Editar");
        System.out.println("\t[5] Apagar");
        System.out.println("\t[0] Sair");
        System.out.println(appSep);
        System.out.print("Opção: ");

        // Recebe a opção do teclado.
        String option = scanner.next();

        // Executa um método conforme a opção escolhida.
        switch (option) {
            case "0" ->
                exitProgram();
            case "1" -> {
                clearScreen();
                Read.readAll();
            }
            case "2" -> {
                clearScreen();
                Read.read();
            }
            case "3" ->
                newThing();
            case "4" ->
                editThing();
            case "5" -> {
                clearScreen();
                Delete.delete();
            }
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

    // Cadastra um novo registro.
    public static void newThing() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Item ID: ");
        String itemID = scan.nextLine();
        System.out.print("Enter Item price: ");
        String priceStr = scan.nextLine();
        double price = Double.valueOf(priceStr);
        System.out.println("Price of Item " + itemID + " is $" + price);
        scan.close();
    }

    // Edita um registro pelo Id.
    public static void editThing() {
    }

    // Apaga um registro pelo Id.
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
