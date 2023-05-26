package net.luferat.cadastro_de_trecos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class DbConnection extends AppSetup {

    // Retorna uma conexão com o banco de dados.
    public static Connection dbConnect() {

        // Tratamento de erros.
        try {

            // Conecta ao banco de dados usando o driver JDBC adequado.
            conn = DriverManager.getConnection(
                    HOSTNAME + DATABASE,
                    USERNAME,
                    PASSWORD
            );

            // Se a conexão foi estabelecida, retorna ela.
            if (conn != null) {
                return conn;
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

        // Se a conexão falhou, retorna 'null'.
        return null;
    }

    // Fecha todos os recursos do banco de dados.
    public static void dbClose(
            ResultSet res,
            Statement stmt,
            PreparedStatement pstm,
            Connection conn
    ) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }

        try {
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }

    // Teste unitário de conexão com o banco de dados.
    public static void main(String[] args) {
        Connection conn = DbConnection.dbConnect();
        DbConnection.dbClose(res, stmt, pstm, conn);
    }

}
