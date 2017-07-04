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
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class TopicDAOImpl implements TopicDAO{

    private static TopicDAOImpl topicDAO = null;        

    private static long topicCount;
    
    /**
     *
     */
    public TopicDAOImpl() { 
        topicCount = 0;
    }

    /**
     *
     * @return
     */
    public static TopicDAOImpl getInstance() {
        
        if (topicDAO == null)
            topicDAO = new TopicDAOImpl();
        
        return  topicDAO;
    }
    
    private ByteArrayInputStream imageToBlob(Image img) throws IOException{
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
        } finally {
            baos.close();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return bais;
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Topic topic) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO Mensagem (idQuestao, idUsuario, mensagem, dataPost, image) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topic.getForum().getIdForum());
            pstmt.setLong(2, topic.getAutor().getIdUsuario());
            pstmt.setString(3, topic.getTxtMensagem());
            pstmt.setDate(4, java.sql.Date.valueOf(topic.getDataPostagem()));
            pstmt.setBinaryStream(5, imageToBlob(topic.getMsgPhoto()));
            pstmt.executeQuery();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Topic topic) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE Mensagem SET idQuestao = ?, idUsuario = ?, mensagem = ?, dataPost = ?, image = ? WHERE idMensagem = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topic.getForum().getIdForum());
            pstmt.setLong(2, topic.getAutor().getIdUsuario());
            pstmt.setString(3, topic.getTxtMensagem());
            pstmt.setDate(4, java.sql.Date.valueOf(topic.getDataPostagem()));
            pstmt.setBinaryStream(5, imageToBlob(topic.getMsgPhoto()));
            pstmt.setLong(6, topic.getTopicoId());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Topic delete(Long topicId) throws PersistenceException {
        try {
            Topic topic= this.getTopicById(topicId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM Mensagem WHERE idMensagem = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return topic;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Topic getTopicById(Long topicId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.enunciadoQuestao enunciadoQuestao, b.idtDificuldade idtDificuldade, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM Mensagem g "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE a.idQuestao = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicId);
            ResultSet rs = pstmt.executeQuery();
            User autorTopico = new User();
            User autorQuestao = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            Question questao = new Question();
            Forum forum = new Forum();
            Topic topico = new Topic();
            if (rs.next()) {
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoM");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                
                mod.setNomeModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setDominio(sub);
                
                questao.setCriador(autorQuestao);
                questao.setModulo(mod);
                questao.setDataCriacao(rs.getDate("dataCriacaoQuestao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                
                forum.setQuestao(questao);
                forum.setDataCriacao(rs.getDate("dataCriacaoForum").toLocalDate());
                forum.setIdForum(rs.getLong("idQuestaoForum"));
                forum.setStatus(rs.getBoolean("statusForum"));
                
                topico.setAutor(autorTopico);
                topico.setDataPostagem(rs.getDate("dataPost").toLocalDate());
                topico.setForum(forum);
                blob = rs.getBinaryStream("topicImage");  
                  
                image = ImageIO.read(blob);
                topico.setMsgPhoto(image);
                topico.setTopicoId(rs.getLong("idTopic"));
                topico.setTxtMensagem(rs.getString("topicMensagem"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return topico;
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
    public List<Topic> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.idtDificuldade idtDificuldade, b.enunciadoQuestao enunciadoQuestao, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM Mensagem g "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Topic> lista = new ArrayList<>();
            if (rs.next()) {
                User autorTopico = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                Forum forum = new Forum();
                Topic topico = new Topic();
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoM");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                
                mod.setNomeModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setDominio(sub);
                
                questao.setCriador(autorQuestao);
                questao.setModulo(mod);
                questao.setDataCriacao(rs.getDate("dataCriacaoQuestao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                
                forum.setQuestao(questao);
                forum.setDataCriacao(rs.getDate("dataCriacaoForum").toLocalDate());
                forum.setIdForum(rs.getLong("idQuestaoForum"));
                forum.setStatus(rs.getBoolean("statusForum"));
                
                topico.setAutor(autorTopico);
                topico.setDataPostagem(rs.getDate("dataPost").toLocalDate());
                topico.setForum(forum);
                blob = rs.getBinaryStream("topicImage");  
                  
                image = ImageIO.read(blob);
                topico.setMsgPhoto(image);
                topico.setTopicoId(rs.getLong("idTopic"));
                topico.setTxtMensagem(rs.getString("topicMensagem"));
                
                lista.add(topico);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return lista;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    
    @Override
    public List<Topic> getForumTopic(Long forumID) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.idtDificuldade idtDificuldade, b.enunciadoQuestao enunciadoQuestao, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM Mensagem g "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE a.idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forumID);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Topic> lista = new ArrayList<>();
            if (rs.next()) {
                User autorTopico = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                Forum forum = new Forum();
                Topic topico = new Topic();
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoM");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                ;
                mod.setNomeModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setDominio(sub);
                
                questao.setCriador(autorQuestao);
                questao.setModulo(mod);
                questao.setDataCriacao(rs.getDate("dataCriacaoQuestao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                
                forum.setQuestao(questao);
                forum.setDataCriacao(rs.getDate("dataCriacaoForum").toLocalDate());
                forum.setIdForum(rs.getLong("idQuestaoForum"));
                forum.setStatus(rs.getBoolean("statusForum"));
                
                topico.setAutor(autorTopico);
                topico.setDataPostagem(rs.getDate("dataPost").toLocalDate());
                topico.setForum(forum);
                blob = rs.getBinaryStream("topicImage");  
                  
                image = ImageIO.read(blob);
                topico.setMsgPhoto(image);
                topico.setTopicoId(rs.getLong("idTopic"));
                topico.setTxtMensagem(rs.getString("topicMensagem"));
                
                lista.add(topico);
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
