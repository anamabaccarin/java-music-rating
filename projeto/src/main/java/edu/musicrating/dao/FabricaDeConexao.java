package edu.musicrating.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

    private static String usuario = "admin";

    private static String senha = "P@ssw0rd";

    private static String host = "localhost";

    private static String porta = "3306";

    private static String db = "musicas";

    private FabricaDeConexao() {
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + porta + "/" + db,
                usuario,
                senha
        );
    }
}
