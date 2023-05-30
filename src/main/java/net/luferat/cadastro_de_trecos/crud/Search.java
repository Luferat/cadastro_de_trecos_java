package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import java.util.Scanner;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;
import static net.luferat.cadastro_de_trecos.Tools.showRes;

public class Search extends AppSetup {

    public static void search() {

        // Reserva recursos.
        String searchString;
        String sql;
        Scanner keyb = new Scanner(System.in, "latin1");

        // Cabeçalho da view.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Pesquisa registros");
        System.out.println(appSep + "\n");

        // Recebe string de pesquisa.
        System.out.print("Digite o termo para buscar ou [0] para sair: ");
        searchString = keyb.nextLine().trim();
        if (searchString.equals("0")) {

            // Se digitou "0", sai para o menu principal.
            clearScreen();
            mainMenu();

        } else {

            // Se digitou uma string.
            try {

                sql = "SELECT * FROM " + DBTABLE + " WHERE name LIKE ? OR description LIKE ?";
                conn = DbConnection.dbConnect();
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + searchString + "%");
                pstm.setString(2, "%" + searchString + "%");
                res = pstm.executeQuery();
                if (res.next()) {

                    System.out.println(" ");

                    // Se encontrou registros, exibe na view.
                    do {
                        showRes(res);
                    } while (res.next());

                } else {

                    // Se não tem registro.
                    clearScreen();
                    System.out.println("Oooops! Não achei nada!\n");
                    search();
                }

                // Fecha recursos.
                DbConnection.dbClose(res, stmt, pstm, conn);

                // Menu inferior da seção.
                System.out.println(appSep);
                System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Procurar outro\n\t[0] Sair");
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
                    case "2":
                        clearScreen();
                        search();
                        break;
                    default:
                        clearScreen();
                        System.out.println("Oooops! Opção inválida!\n");
                        search();
                }

            } catch (SQLException error) {

                // Tratamento de erros.
                System.out.println("Oooops! " + error.getMessage());
                System.exit(0);
            }

        }

    }

}
