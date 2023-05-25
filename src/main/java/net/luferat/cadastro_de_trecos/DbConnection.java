package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    // Fecha todos os recursos porventuda abertos.
    public static void dbClose(
            Connection conn,
            Statement stmt,
            PreparedStatement pstm,
            ResultSet res
    ) {
        if (res != null) try {
            res.close();
        } catch (SQLException ignore) {
        }

        if (stmt != null) try {
            stmt.close();
        } catch (SQLException ignore) {
        }

        if (pstm != null) try {
            pstm.close();
        } catch (SQLException ignore) {
        }

        if (conn != null) try {
            conn.close();
        } catch (SQLException ignore) {
        }
    }

    // Teste unitário.
    public static void main(String[] args) {
        Connection conn = DbConnection.dbConnect();
        if (conn != null) {
            System.out.println("Conectou!");
        }
        DbConnection.dbClose(conn, null, null, null);
    }

}
