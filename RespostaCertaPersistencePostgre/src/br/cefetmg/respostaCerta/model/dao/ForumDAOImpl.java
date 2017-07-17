/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Vitor
 */
public class ForumDAOImpl implements ForumDAO{
    
    private static ForumDAOImpl forumDAO = null;        
  
    private static long forumCount;
    
    public ForumDAOImpl() { 
        forumCount = 0;
    }

    /**
     *
     * @return
     */
    public static ForumDAOImpl getInstance() {
        
        if (forumDAO == null)
            forumDAO = new ForumDAOImpl();
        
        return  forumDAO;
    }
    
    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Forum forum) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO forum (idQuestao, dataCriacao, status) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forum.getQuestao().getIdQuestao());
            pstmt.setBoolean(3, forum.isStatus());
            pstmt.setDate(2, java.sql.Date.valueOf(forum.getDataCriacao()));
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Forum forum) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE forum SET dataCriacao = ?, status = ? WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(3, forum.getIdForum());
            pstmt.setBoolean(2, forum.isStatus());
            pstmt.setDate(1, java.sql.Date.valueOf(forum.getDataCriacao()));
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Forum delete(Long forumId) throws PersistenceException {
        try {
            Forum forum= this.getForumById(forumId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM forum WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forumId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return forum;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Forum getForumById(Long forumId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM forum a "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE a.idQuestao = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forumId);
            ResultSet rs = pstmt.executeQuery();
            User autor = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            Question questao = new Question();
            Forum forum = new Forum();
            if (rs.next()) {
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                  
                BufferedImage image = ImageIO.read(blob);
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                if(blob!=null) {
                    image = ImageIO.read(blob);
                    questao.setQuestPhoto(image);
                }
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                forum.setQuestao(questao);
                forum.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                forum.setStatus(rs.getBoolean("status"));
                forum.setIdForum(rs.getLong("idQuestao"));
                
            }
            rs.close();
            pstmt.close();
            connection.close();
            return forum;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Forum> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM forum a "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Forum> lista = new ArrayList<>();
            while(rs.next()) {
                User autor = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                Forum forum = new Forum();
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                  
                BufferedImage image = ImageIO.read(blob);
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                forum.setQuestao(questao);
                forum.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                forum.setStatus(rs.getBoolean("status"));
                forum.setIdForum(rs.getLong("idQuestao"));
                lista.add(forum);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return lista;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
}
