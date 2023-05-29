package net.luferat.cadastro_de_trecos.crud;

import java.sql.SQLException;
import java.util.Scanner;
import static net.luferat.cadastro_de_trecos.Cadastro_de_trecos.*;
import net.luferat.cadastro_de_trecos.db.DbConnection;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Create extends AppSetup {

    public static void create() {

        try {

            // Reserva recursos para o banco de dados.
            String sql;

            // Cabeçalho da seção.
            System.out.println(appName + "\n" + appSep);
            System.out.println("Novo registro");
            System.out.println(appSep);

            // Recebe entradas do terminal com acentuação.
            Scanner keyboard = new Scanner(System.in, "latin1");
            System.out.print("\n\tNome: ");
            String itemName = keyboard.nextLine().trim();
            System.out.print("\tDescrição: ");
            String itemDescription = keyboard.nextLine().trim();

            System.out.print("\nOs dados acima estão corretos? [s/N] ");
            if (keyboard.next().trim().toLowerCase().equals("s")) {

                sql = "INSERT INTO " + DBTABLE + " (name, description) VALUES (?, ?)";
                conn = DbConnection.dbConnect();
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, itemName);
                pstm.setString(2, itemDescription);
                int created = pstm.executeUpdate();
                if (created > 0) {

                    // Se cadastrou.
                    System.out.println("\nRegistro cadastrado!");
                } else {

                    // Se não cadastrou.
                    System.out.println("\nOooops! Falha ao cadastrar registro!");
                }

            } else {
                // Se não confirmou cadastrar o registro.
                System.out.println("\nNão aconteceu nada!");
            }

            // Fecha conexões abertas.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Cadastrar outro\n\t[0] Sair");
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
                    create();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    create();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}
