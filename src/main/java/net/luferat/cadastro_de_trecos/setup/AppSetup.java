package net.luferat.cadastro_de_trecos.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppSetup {

    protected static Scanner scanner = new Scanner(System.in);
    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static PreparedStatement pstm = null;
    protected static ResultSet res = null;
    protected static String appName = "CADASTRO DE TRECOS";
    protected static String line = "------------------------------------------------";
    protected static String dbTable = "things";

    // ////////////////////////////////// //
    // Configurações de conexão com MySQL //
    // ////////////////////////////////// //
    // protected static final String HOSTNAME = "jdbc:mysql://localhost:3306/"; // Conexão com o servidor.
    // protected static final String DATABASE = "things";                       // Banco de dados.
    // protected static final String USERNAME = "root";                         // Usuário do banco de dados.
    // protected static final String PASSWORD = "";                             // Senha do banco de dados.
    //
    // ////////////////////////////////////// //
    // Configurações de conexão com o SQLite. //
    // ////////////////////////////////////// //
    protected static final String HOSTNAME = "jdbc:sqlite:"; // Conexão com o servidor.
    protected static final String DATABASE = "things.db";    // Banco de dados.
    protected static final String USERNAME = "";             // Usuário do banco de dados. Não usa no SQLite.
    protected static final String PASSWORD = "";             // Senha do banco de dados. Não usa no SQLite.

}
