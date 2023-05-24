package net.luferat.cadastro_de_trecos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "things";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection dbConnect() {
        try {
            Connection conn = DriverManager.getConnection(
                    HOSTNAME + DATABASE,
                    USERNAME,
                    PASSWORD
            );
            if (conn != null) {
                return conn;
            }
        } catch (SQLException error) {
            System.out.println("Oooops! " + error.getMessage());
        }
        return null;
    }

}
