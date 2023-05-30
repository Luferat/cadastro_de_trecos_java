package net.luferat.cadastro_de_trecos.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppSetup {

    // /////////////////////////////////////////// //
    // Configurações de conexão com Bacos de Dados //
    // /////////////////////////////////////////// //
    //
    // String de conexão com o banco de dados SQLite
    //                                             tipo   caminho       database
    //                                              ↓      ↓             ↓ 
    protected static final String SQLITEURL = "jdbc:sqlite:src/resources/things.db";

    // String de conexão com o banco de dados MySQL
    //                                            tipo    servidor  porta database   usuário       senha
    //                                             ↓       ↓         ↓     ↓           ↓             ↓ 
    protected static final String MYSQLURL = "jdbc:mysql://localhost:3306/things?user=root&password=";

    // String de conexão com o banco de dados PostGreeSQL
    //                                               tipo         servidor  porta database   usuário      senha
    //                                                ↓            ↓         ↓     ↓           ↓             ↓       
    protected static final String POSTGREEURL = "jdbc:postgresql://localhost:5432/things?user=root&password=root";

    // Tabela principal.
    protected static final String DBTABLE = "things";

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
