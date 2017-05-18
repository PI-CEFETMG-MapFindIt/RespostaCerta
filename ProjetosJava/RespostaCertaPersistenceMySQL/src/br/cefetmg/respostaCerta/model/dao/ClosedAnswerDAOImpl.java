/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.util.db.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            String sql = "INSERT INTO respostaFechada (idUsuario, idQuestao, dataResposta, resposta) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, respostaFechada.getAutor().getIdUsuario());
            pstmt.setLong(2, respostaFechada.getQuestao().getIdQuestao());
            pstmt.setDate(3, java.sql.Date.valueOf(respostaFechada.getDataResposta()));
            pstmt.setInt(4, respostaFechada.getResposta());
            boolean res = pstmt.execute();
            if(!res){
                throw new PersistenceException("Comando SQL falhou!");
            }
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
        try{
            String sql = "UPDATE respostaFechada SET resposta=?, dataResposta=?, idUsuario=?, idQuestao=? WHERE idResposta=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, respostaFechada.getResposta());
            pstmt.setDate(2, java.sql.Date.valueOf(respostaFechada.getDataResposta()));
            pstmt.setLong(3, respostaFechada.getAutor().getIdUsuario());
            pstmt.setLong(4, respostaFechada.getQuestao().getIdQuestao());
            pstmt.setLong(5, respostaFechada.getIdResposta());
            boolean res = pstmt.execute();
            if(!res){
                throw new PersistenceException("Comando SQL falhou!");
            }
        }catch(SQLException ex){
            throw new PersistenceException(ex.getMessage());
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
        try{
            String sqlResp = "SELECT * FROM respostaFechada WHERE idResposta=? LIMIT 1";
            PreparedStatement statementResp = con.prepareStatement(sqlResp);
            statementResp.setLong(1, respostaId);
            ClosedAnswer deletado;
            ResultSet resResp = statementResp.executeQuery();
            resResp.next();
            User autor;
            UserDAO autorDAO = new UserDAOImpl();
            QuestionDAO questaoDAO = new QuestionDAOImpl();
            Question questao = questaoDAO.getQuestionById(resResp.getLong("idQuestao"));
            autor = autorDAO.getUserById(resResp.getLong("idQuestao"));
            //deletado = new ClosedAnswer(resResp.getInt("resposta"), autor, questao, );
            String sql = "DELETE FROM respostaFechada WHERE idResposta=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, respostaId);
            boolean res = pstmt.execute();
            if(!res){
                throw new PersistenceException("Comando SQL falhou!");
            }
            return null;
        }catch(SQLException ex){
            throw new PersistenceException(ex.getMessage());
        }
        
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
        try{
            String sql = "DELETE FROM respostaFechada WHERE idResposta=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, respostaId);
            boolean res = pstmt.execute();
            if(!res){
                throw new PersistenceException("Comando SQL falhou!");
            }
            return null;
        }catch(SQLException ex){
            throw new PersistenceException(ex.getMessage());
        }
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
