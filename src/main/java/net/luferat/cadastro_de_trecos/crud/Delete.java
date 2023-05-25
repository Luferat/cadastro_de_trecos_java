package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Delete extends AppSetup {

    public static void delete() {

        // Inicializa parâmetros.
        int id = 0;
        String sql = "";

        try {

            // Prepara interface (view).
            System.out.println(appName + "\n" + line);
            System.out.println("Apagar um registro pelo Id");
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
                delete();
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
                delete();
            } else {
                
                // Exibe o registro.
                System.out.println(
                        "\nID: " + res.getString("id") + "\n"
                        + "  Nome: " + res.getString("name") + "\n"
                        + "  Descrição: " + res.getString("description") + "\n"
                );
                
                // Confirmação.
                System.out.print("Tem certeza que deseja apagar? [y/N] ");
                String option = scanner.next().toLowerCase();
                if ("y".equals(option)) {
                    
                    // Se confirmou, apagar registr, executa a query.
                    sql = "DELETE FROM " + dbTable + " WHERE id = ?";
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
            DbConnection.dbClose(conn, stmt, pstm, res);

            // Menu da seção.
            System.out.println(line);
            System.out.println("Menu:\n\t[1] Menu principal\t[2] Apagar\n\t[0] Sair");
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
                    delete();
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
