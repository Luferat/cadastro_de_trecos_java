package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.clearScreen;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.mainMenu;
import static net.luferat.cadastro_de_trecos.crud.Read.read;
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

            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ?";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();

            if (res.next()) {
            } else {
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                delete();
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
