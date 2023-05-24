package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // String de conexão com o SGBD.
    // private static final String HOSTNAME = "jdbc:mysql://localhost:3306/";
    private static final String HOSTNAME = "jdbc:sqlite:";

    // Banco de dados.
    private static final String DATABASE = "things.db";

    // Usuário com permissões para o banco de dados.
    private static final String USERNAME = "root";

    // Senha de acesso ao banco de dados.
    private static final String PASSWORD = "";

    // Retorna uma conexão com o banco de dados.
    public static Connection dbConnect() {
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
    
    public static void main(String[] args) {
        Connection conecta = DbConnection.dbConnect();
    }

}
