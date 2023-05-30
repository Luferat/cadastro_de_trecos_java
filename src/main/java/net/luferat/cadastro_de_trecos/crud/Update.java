package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import java.util.Scanner;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;
import static net.luferat.cadastro_de_trecos.Tools.showRes;

public class Update extends AppSetup {

    public static void update() {

        // Inicializa e reserva recursos.
        int id = 0;
        String sql;

        // Cabeçalho da view.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Editar registro");
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
            update();
        }

        try {

            System.out.println(" ");

            // Obtém o registro solicitado do banco de dados.
            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            if (res.next()) {

                // Se tem registro, exibe na view.
                showRes(res);

                System.out.println("Insira os novos dados ou deixe em branco para manter os atuais:\n");

                // Instância de Scanner para obter dados do teclado.
                Scanner keyboard = new Scanner(System.in, "latin1");

                System.out.print("\tNome: ");
                String itemName = keyboard.nextLine().trim();

                System.out.print("\tDescrição: ");
                String itemDescription = keyboard.nextLine().trim();

                // Pede confirmação.
                System.out.print("\nOs dados acima estão corretos? [s/N] ");
                if (keyboard.next().trim().toLowerCase().equals("s")) {

                    String saveName = (itemName.equals("")) ? res.getString("name") : itemName;
                    String saveDescription = (itemDescription.equals("")) ? res.getString("description") : itemDescription;

                    // Atualiza registro no banco de dados.
                    sql = "UPDATE " + DBTABLE + " SET name = ?, description = ? WHERE id = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, saveName);
                    pstm.setString(2, saveDescription);
                    pstm.setInt(3, id);
                    if (pstm.executeUpdate() == 1) {

                        // Se o registro foi criado.
                        System.out.println("\nRegistro atualizado!");
                    } else {

                        // Falha ao criar registro.
                        System.out.println("Nada aconteceu!");
                    }

                } else {
                    System.out.println("\nNada aconteceu!");
                }

                // Fecha banco de dados.
                DbConnection.dbClose(res, stmt, pstm, conn);

                // Menu inferior da seção.
                System.out.println(appSep);
                System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Editar outro\n\t[0] Sair");
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
                        update();
                        break;
                    default:
                        clearScreen();
                        System.out.println("Oooops! Opção inválida!\n");
                        update();
                }

            } else {
                // Se não tem registro.
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                update();
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
