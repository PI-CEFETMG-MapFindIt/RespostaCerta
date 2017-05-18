/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umcan
 */
public class ClosedAnswerDAOImpl implements ClosedAnswerDAO{
    Connection con;
    
    public ClosedAnswerDAOImpl(){
        
    }
    
    @Override
    public void insert(ClosedAnswer respostaFechada) throws PersistenceException {
        if(con==null){
            try {
                con= JDBCConnectionManager.getInstance().getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
        }
        try{
            String sql = "INSERT INTO respostaFechada (idUsuario, idQuestao, dataResposta, resposta, boolCorreta) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, respostaFechada.getAutor().getIdUsuario());
            pstmt.setLong(2, respostaFechada.getQuestao().getIdQuestao());
            pstmt.setDate(3, java.sql.Date.valueOf(respostaFechada.getDataResposta()));
            pstmt.setInt(4, respostaFechada.getResposta());
            ResultSet rs = pstmt.executeQuery();
        }catch(SQLException ex){
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(ClosedAnswer respostaFechada) throws PersistenceException {
        if(con==null){
            try {
                con= JDBCConnectionManager.getInstance().getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
        }

    }

    @Override
    public ClosedAnswer delete(Long respostaId) throws PersistenceException {
        if(con==null){
            try {
                con= JDBCConnectionManager.getInstance().getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public ClosedAnswer getClosedAnswerById(Long respostaId) throws PersistenceException {
        if(con==null){
            try {
                con= JDBCConnectionManager.getInstance().getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<ClosedAnswer> listAll() throws PersistenceException {
        if(con==null){
            try {
                con= JDBCConnectionManager.getInstance().getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
        }
        return null;
    }
    
}
