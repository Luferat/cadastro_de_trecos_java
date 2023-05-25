package net.luferat.cadastro_de_trecos;

import net.luferat.cadastro_de_trecos.setup.AppSetup;
import net.luferat.cadastro_de_trecos.crud.Create;
import net.luferat.cadastro_de_trecos.crud.Read;
import net.luferat.cadastro_de_trecos.crud.Update;
import net.luferat.cadastro_de_trecos.crud.Delete;

public class Cadastro_de_trecos extends AppSetup {

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
                clearScreen();
                Read.readAll();
                break;
            case "2":
                clearScreen();
                Read.read();
                break;
            case "3":
                clearScreen();
                Create.create();
                break;
            case "4":
                clearScreen();
                Update.update();
                break;
            case "5":
                clearScreen();
                Delete.delete();
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
