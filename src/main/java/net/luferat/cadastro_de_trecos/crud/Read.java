package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import net.luferat.cadastro_de_trecos.setup.AppSetup;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;

public class Read extends AppSetup {

    // Lista todos os registros.
    public static void readAll() {

        // Inicializa parâmetros.
        String sql = "";

        try {

            // Prepara interface (view).
            System.out.println(appName + "\n" + line);
            System.out.println("Listando todos os registros");
            System.out.println(line);
            System.out.println(" ");

            // Consulta ao banco de dados.
            sql = "SELECT * FROM " + dbTable; // Query de consulta.
            conn = DbConnection.dbConnect();         // Abre uma conexão.
            stmt = conn.createStatement();           // Prepara o estado da conexão atual.
            res = stmt.executeQuery(sql);            // Executa a query.

            if (!res.next()) {

                // Se não encontrou um registro.
                clearScreen();
                System.out.println("Ooops! Não encontrei!\n");
            } else {

                // Se encontrou 1 ou mais registros, exibe.
                do {
                    System.out.println(
                            "ID: " + res.getString("id") + "\n"
                            + "  Nome: " + res.getString("name") + "\n"
                            + "  Descrição: " + res.getString("description") + "\n"
                    );
                } while (res.next());
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

    // Lista um registro pelo Id.
    public static void read() {

        // Inicializa parâmetros.
        int id = 0;
        String sql = "";

        try {

            // Prepara interface (view).
            System.out.println(appName + "\n" + line);
            System.out.println("Listar registro pelo Id");
            System.out.println(line);
            System.out.print("ID do registro ou [0] para voltar ao menu: ");

            try {
                // Se digitou um valor válido.
                id = Integer.parseInt(scanner.next());

                // Se digitou [0], carrega o menu principal.
                if (id == 0) {
                    clearScreen();
                    mainMenu();
                }
            } catch (NumberFormatException error) {

                // Se digitou um valor inválido.
                clearScreen();
                System.out.println("Oooops! Opção inválida!\n");
                read();
            }

            // Query de consulta.
            sql = "SELECT * FROM " + dbTable + " WHERE id = ?";

            // Transação com banco de dados.
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();

            if (!res.next()) {

                // Se o registro não existe.
                clearScreen();
                System.out.println("Ooops! Não encontrei!\n");
                read();
            } else {

                // Exibe o registro.
                System.out.println(
                        "\nID: " + res.getString("id") + "\n"
                        + "  Nome: " + res.getString("name") + "\n"
                        + "  Descrição: " + res.getString("description") + "\n"
                );
            }

            // Fecha conexões abertas.
            DbConnection.dbClose(conn, stmt, pstm, res);

            // Menu da seção.
            System.out.println(line);
            System.out.println("Menu:\n\t[1] Menu principal\t[2] Listar\n\t[0] Sair");
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
                case "2":
                    clearScreen();
                    read();
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
}
