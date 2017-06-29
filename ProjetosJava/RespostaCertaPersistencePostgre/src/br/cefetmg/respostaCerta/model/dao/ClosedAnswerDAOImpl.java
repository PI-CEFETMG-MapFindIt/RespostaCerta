/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.inf.util.db.ConnectionManager;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author umcan
 */
public class ClosedAnswerDAOImpl implements ClosedAnswerDAO{
    private static ClosedAnswerDAOImpl closedDAO = null;         
    private static long closedCount;
    
    public ClosedAnswerDAOImpl() { 
        closedCount = 0;
    }

    /**
     *
     * @return
     */
    public static ClosedAnswerDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedAnswerDAOImpl();
        
        return  closedDAO;
    }

    @Override
    public Livro obterPorISBN(Long isbn) throws PersistenciaException{
        
    }

    @Override
    public List<Livro> obterTodosLivros() throws PersistenciaException{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM livro ORDER BY autor";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Livro> todosLivros = new ArrayList<>();
            if (rs.next()) {
                do {
                    Livro livro = new Livro();
                    livro.setIsbn(rs.getLong("isbn"));
                    livro.setNome(rs.getString("nome"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setData(rs.getDate("data"));
                    livro.setEditora(rs.getString("editora"));
                    livro.setNumPaginas(rs.getInt("numpaginas"));
                    livro.setVolume(rs.getInt("volume"));
                    todosLivros.add(livro);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return todosLivros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    /**
     *
     * @param respostaFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedAnswer respostaFechada) throws PersistenceException {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO resposta (idUsuario, idQuestao, idtResposta, dataResposta) VALUES(?, ?, ?, ?) RETURNING idResposta";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, respostaFechada.getAutor().getIdUsuario());
            pstmt.setLong(2, respostaFechada.getQuestao().getIdQuestao());
            pstmt.setString(3, String.valueOf(respostaFechada.getIdtResposta()));
            pstmt.setDate(4, java.sql.Date.valueOf(respostaFechada.getDataResposta()));
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Resposta Falhou");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    respostaFechada.setIdResposta(generatedKeys.getLong(1));
                }
                else {
                    throw new PersistenceException("Criação falhou, sem id's obtidos");
                }
            }
            sql = "INSERT INTO respostaFechada (idResposta, resposta) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, respostaFechada.getIdResposta());
            pstmt.setInt(2, respostaFechada.getResposta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param closedAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(ClosedAnswer closedAnswer) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE resposta SET idUsuario = ?, idQuestao = ?, idtResposta = ?, dataResposta = ? WHERE idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedAnswer.getAutor().getIdUsuario());
            pstmt.setLong(2, closedAnswer.getQuestao().getIdQuestao());
            pstmt.setString(3, String.valueOf(closedAnswer.getIdtResposta()));
            pstmt.setDate(4, java.sql.Date.valueOf(closedAnswer.getDataResposta()));
            pstmt.setLong(5, closedAnswer.getIdResposta());
            pstmt.executeUpdate();
            sql = "UPDATE respostaFechada SET resposta = ? WHERE idResposta = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, closedAnswer.getResposta());
            pstmt.setLong(2, closedAnswer.getIdResposta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public ClosedAnswer delete(Long closedId) throws PersistenceException {
        try {
            ClosedAnswer resposta= this.getClosedAnswerById(closedId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM resposta WHERE idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            pstmt.executeUpdate();
            sql = "DELETE FROM RespostaFechada WHERE idResposta = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    public ClosedAnswer getClosedAnswerById(Long closedId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM respostaFechada a JOIN resposta b ON a.idResposta=b.idResposta WHERE a.idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            ResultSet rs = pstmt.executeQuery();
            ClosedAnswer closed = new ClosedAnswer();
            /* TO DO */
            if (rs.next()) {
                
                livro.setIsbn(rs.getLong("isbn"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setData(rs.getDate("data"));
                livro.setEditora(rs.getString("editora"));
                livro.setNumPaginas(rs.getInt("numpaginas"));
                livro.setVolume(rs.getInt("volume"));
            }

            rs.close();
            pstmt.close();
            connection.close();
            
            return livro;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }       
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<ClosedAnswer> listAll() throws PersistenceException {
        List<ClosedAnswer> closedList = new ArrayList<>();
        
        Iterator<ClosedAnswer> iterator = closedAnswerDB.values().iterator();
	while (iterator.hasNext())
            closedList.add(iterator.next());
        
        return closedList;
    }    

    @Override
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException {
        List<ClosedAnswer> closedList = new ArrayList<>();
        Iterator<ClosedAnswer> iterator = closedAnswerDB.values().iterator();
	ClosedAnswer item;
        while (iterator.hasNext()){
            item=iterator.next();
            if(Objects.equals(item.getAutor().getIdUsuario(), userId)){
                closedList.add(item);
            }
        }
            
        
        return closedList;
    }
    
}
