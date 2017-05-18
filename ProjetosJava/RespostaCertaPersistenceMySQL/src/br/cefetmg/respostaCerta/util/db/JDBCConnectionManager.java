/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.util.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class JDBCConnectionManager {
    private static JDBCConnectionManager connection;
    private static JDBCConnectionFactory conFactory;

    private JDBCConnectionManager() {
        this(new JDBCMySQLConnection("jdbc:mysql://localhost:3306/RespostaCerta", "root", ""));
    }
    
    private JDBCConnectionManager(JDBCConnectionFactory cf) {
        this.conFactory = cf;
    }

    public static JDBCConnectionManager getInstance() {
        if(connection == null)
            connection = new JDBCConnectionManager();
        return connection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return JDBCConnectionManager.conFactory.getConnection();
    }
}
