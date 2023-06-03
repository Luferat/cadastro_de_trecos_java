package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import net.luferat.cadastro_de_trecos.setup.AppSetup;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import static net.luferat.cadastro_de_trecos.setup.AppSetup.*;
import static net.luferat.cadastro_de_trecos.Main.*;
import static net.luferat.cadastro_de_trecos.Tools.*;

public class Read extends AppSetup {

    // Lista todos os registros.
    public static void readAll() {

        // Reserva recursos.
        String sql;

        // Cabeçalho da view.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Lista todos os registros");
        System.out.println(appSep + "\n");

        try {

            // Consulta o banco de dados.
            sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y às %H:%i') AS databr FROM " + DBTABLE + " WHERE status != '0' ORDER BY nome";
            conn = DbConnection.dbConnect();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            if (res.next()) {

                // Se encontrou registros, exibe na view.
                do {
                    showRes(res);
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
                case "0":
                    exitProgram();
                    break;
                case "1":
                    clearScreen();
                    mainMenu();
                    break;
                default:
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    readAll();
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
        String sql;

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

            System.out.println(" ");

            // Faz consulta no banco de dados usando "preparedStatement".
            sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y às %H:%i') AS databr FROM " + DBTABLE + " WHERE status != '0' AND id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);

            // Passa o Id para a query.
            pstm.setInt(1, id);

            // Executa a query.
           

            if (res.next()) {
                showRes(res);
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
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    read();
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
