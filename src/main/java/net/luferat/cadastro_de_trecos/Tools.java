package net.luferat.cadastro_de_trecos;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Tools extends AppSetup {

    public static void showRes(ResultSet res) {

        String viewStatus;

        try {

            switch (res.getString("status")) {
                case "0":
                    viewStatus = "APAGADO";
                    break;
                case "1":
                    viewStatus = "BLOQUEADO";
                    break;
                case "2":
                    viewStatus = "ATIVO";
                    break;
                default:
                    viewStatus = "INDEFINIDO";
            }

            System.out.println(
                    "ID: " + res.getString("id") + "\n"
                    + "  Data: " + res.getString("databr") + "\n"
                    + "  Nome: " + res.getString("nome") + "\n"
                    + "  Descrição: " + res.getString("descricao") + "\n"
                    + "  Localização: " + res.getString("localizacao") + "\n"
                    + "  Status: " + viewStatus + "\n"
            );

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

    // Encerra o programa.
    public static void exitProgram() {
        scanner.close();
        clearScreen();
        System.out.println("\n\nFui!\n\n");
        System.exit(0);
    }

    // Limpa a tela do terminal.
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }
    }

}
