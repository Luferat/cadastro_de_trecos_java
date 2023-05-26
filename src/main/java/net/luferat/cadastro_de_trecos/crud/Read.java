package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import net.luferat.cadastro_de_trecos.setup.AppSetup;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import static net.luferat.cadastro_de_trecos.setup.AppSetup.*;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;

public class Read extends AppSetup {

    // Lista todos os registros.
    public static void readAll() {

        // Cabeçalho da view.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Lista todos os registros");
        System.out.println(appSep);

        try {

            // Consulta o banco de dados.
            String sql = "SELECT * FROM " + DBTABLE;
            conn = DbConnection.dbConnect();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            System.out.println(" ");
            if (res.next()) {

                // Se encontrou registros.
                do {

                    // Exibe registro na view.
                    System.out.println(
                            "ID: " + res.getString("id") + "\n"
                            + "  Nome: " + res.getString("name") + "\n"
                            + "  Descrição: " + res.getString("description") + "\n"
                    );
                } while (res.next());
            } else {

                // Se não encontrou registro.
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
            }

            // Fecha recursos.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[0] Sair");
            System.out.println(appSep);

            // Recebe opção do teclaso.
            System.out.print("Opção: ");
            String option = scanner.next();

            // Executa conforme a opção.
            switch (option) {
                case "0" ->
                    exitProgram();
                case "1" -> {
                    clearScreen();
                    mainMenu();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    readAll();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

    // Lista um único registro pelo id.
    public static void read() {

        // Reserva recursos para o banco de dados.
        int id = 0;
        String sql = "";

        // Cabeçalho da seção.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Lista um registro");
        System.out.println(appSep);

        try {

            // Recebe o Id do teclado.
            System.out.print("Digite o ID ou [0] para retornar: ");
            id = Integer.parseInt(scanner.next());
            if (id == 0) {
                clearScreen();
                mainMenu();
            }
        } catch (NumberFormatException e) {

            // Quando opção é inválida.
            clearScreen();
            System.out.println("Oooops! Opção inválida!\n");
            read();
        }

        try {

            // Faz consulta no banco de dados usando "preparedStatement".
            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);

            // Passa o Id para a query.
            pstm.setInt(1, id);

            // Executa a query.
            res = pstm.executeQuery();

            if (res.next()) {

                // Se tem registro, exibe na view.
                System.out.println(
                        "\nID: " + res.getString("id") + "\n"
                        + "  Nome: " + res.getString("name") + "\n"
                        + "  "
                        + "Descrição: " + res.getString("description") + "\n"
                );
            } else {

                // Se não tem registro.
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                read();
            }

            // Fecha banco de dados.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Listar outro\n\t[0] Sair");
            System.out.println(appSep);

            // Recebe opção do teclado.            
            System.out.print("Opção: ");
            String option = scanner.next();

            // Executa conforme a opção.
            switch (option) {
                case "0" ->
                    exitProgram();
                case "1" -> {
                    clearScreen();
                    mainMenu();
                }
                case "2" -> {
                    clearScreen();
                    read();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    read();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
