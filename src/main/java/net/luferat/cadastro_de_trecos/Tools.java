package net.luferat.cadastro_de_trecos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tools {

    public static void showRes(ResultSet res) {
        try {
            System.out.println(
                    "ID: " + res.getString("id") + "\n"
                    + "  Nome: " + res.getString("name") + "\n"
                    + "  Descrição: " + res.getString("description") + "\n"
            );
        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
