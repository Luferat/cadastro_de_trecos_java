package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import static net.luferat.cadastro_de_trecos.Tools.showRes;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Delete extends AppSetup {

    public static void delete() {

        // Reserva recursos para o banco de dados.
        int id = 0;
        String sql;

        // Cabeçalho da seção.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Apaga um registro");
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
            delete();
        }

        try {
            
            System.out.println(" ");

            // Verifica se o registro existe.
            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();

            if (res.next()) {

                // Se encontrou o registro, exibe na view.
                showRes(res);

                System.out.print("Tem certeza que deseja apagar o registro? [s/N] ");
                if (scanner.next().trim().toLowerCase().equals("s")) {

                    sql = "DELETE FROM " + DBTABLE + " WHERE id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, id);
                    if (pstm.executeUpdate() == 1) {
                        // Registro apagado.
                        System.out.println("\nRegistro apagado!");
                    } else {
                        System.out.println("Oooops! Algo deu errado!");
                    }
                } else {
                    System.out.println("\nNada aconteceu!");
                }

            } else {
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                delete();
            }

            // Fecha banco de dados.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Apagar outro\n\t[0] Sair");
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
                    delete();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    delete();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
