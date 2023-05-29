package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Delete extends AppSetup {

    public static void delete() {

        // Reserva recursos para o banco de dados.
        int id = 0;
        String sql = "";

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
            // Verifica se registro existe no banco de dados.
            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();

            if (!res.next()) {
                // Registro não existe.
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                delete();
            } else {
                // Exibe o registro.
                System.out.println(
                        "\nID: " + res.getString("id") + "\n"
                        + "  Nome: " + res.getString("name") + "\n"
                        + "  Descrição: " + res.getString("description") + "\n"
                );

                // Confirmação.
                System.out.print("Tem certeza que deseja apagar? [s/N] ");
                if (scanner.next().trim().toLowerCase().equals("s")) {

                    // Apaga registro.
                    sql = "DELETE FROM " + DBTABLE + " WHERE id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, id);
                    int deleted = pstm.executeUpdate();
                    if (deleted > 0) {

                        // Se apagou.
                        System.out.println("\nRegistro apagado!");
                    } else {

                        // Se não apagou.
                        System.out.println("\nOooops! Falha ao apagar registro!");
                    }
                } else {

                    // Se não confirmou apagar o registro.
                    System.out.println("\nNão aconteceu nada!");
                }
            }

            // Fecha conexões abertas.
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
