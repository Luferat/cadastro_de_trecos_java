package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // ////////////////////////////////// //
    // Configurações de conexão com MySQL //
    // ////////////////////////////////// //
    // private static final String HOSTNAME = "jdbc:mysql://localhost:3306/"; // Conexão com o servidor.
    // private static final String DATABASE = "things";                       // Banco de dados.
    // private static final String USERNAME = "root";                         // Usuário do banco de dados.
    // private static final String PASSWORD = "";                             // Senha do banco de dados.
    //
    // ////////////////////////////////////// //
    // Configurações de conexão com o SQLite. //
    // ////////////////////////////////////// //
    private static final String HOSTNAME = "jdbc:sqlite:"; // Conexão com o servidor.
    private static final String DATABASE = "things.db";    // Banco de dados.
    private static final String USERNAME = "";             // Usuário do banco de dados. Não usa no MySQL.
    private static final String PASSWORD = "";             // Senha do banco de dados. Não usa no MySQL.

    // Retorna uma conexão com o banco de dados.
    public static Connection dbConnect() {

        // Tratamento de erros.
        try {

            // Conecta ao banco de dados usando o driver JDBC adequado.
            Connection conn = DriverManager.getConnection(
                    HOSTNAME + DATABASE,
                    USERNAME,
                    PASSWORD
            );

            // Se a conexão foi estabelecida, retorna ela.
            if (conn != null) {
                return conn;
            }

        } catch (SQLException error) {
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

        // Se a conexão falhou, retorna 'null'.
        return null;
    }

    // Teste unitário de conexão com o banco de dados.
    public static void main(String[] args) {
        try {
            Connection conn = DbConnection.dbConnect();
            conn.close();
        } catch (SQLException error) {
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }
    }

}
