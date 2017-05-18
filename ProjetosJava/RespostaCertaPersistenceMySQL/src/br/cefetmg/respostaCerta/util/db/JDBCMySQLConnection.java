/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class JDBCMySQLConnection implements JDBCConnectionFactory{
    private final String dbDriver;
    private String dbURL;
    private String user;
    private String password;
    
    public JDBCMySQLConnection(String dbURL, String user, String password){
        this.dbURL=dbURL;
        this.user=user;
        this.password=password;
        this.dbDriver="com.mysql.jdbc.Driver";
    }
    
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, password);
    }
}
