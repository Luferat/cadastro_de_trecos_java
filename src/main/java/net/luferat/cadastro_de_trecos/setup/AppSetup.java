package net.luferat.cadastro_de_trecos.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppSetup {

    // String de conexão com o banco de dados MySQL.
    protected static final String MYSQLURL = "jdbc:mysql://localhost:3306/crudinho?user=root&password=";

    // Tabela principal.
    protected static final String DBTABLE = "trecos";

    // Objeto que recebe dados do teclado.
    protected static Scanner scanner = new Scanner(System.in, "latin1");

    // Reserva recursos para o banco de dados.
    protected static Connection conn = null;        // Conexão com o banco de dados.
    protected static PreparedStatement pstm = null; // Preparação da query.
    protected static Statement stmt = null;         // Stado da query.
    protected static ResultSet res = null;          // Resultado da busca (SELECT).

    // Dados do aplicativo.
    protected static String appName = "CADASTRO DE TRECOS";
    protected static String appSep = "-----------------------------------";

}
