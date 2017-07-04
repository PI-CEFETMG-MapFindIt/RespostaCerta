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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class TopicAnswerDAOImpl implements TopicAnswerDAO{

    private static TopicAnswerDAOImpl topicAnswerDAO = null;        

    private static long topicAnswerCount;
    
    /**
     *
     */
    public TopicAnswerDAOImpl() { 
        topicAnswerCount = 0;
    }

    /**
     *
     * @return
     */
    public static TopicAnswerDAOImpl getInstance() {
        
        if (topicAnswerDAO == null)
            topicAnswerDAO = new TopicAnswerDAOImpl();
        
        return  topicAnswerDAO;
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(TopicAnswer topicAnswer) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO MensagemResposta (idMensagem, idUsuario, mensagem, dataResposta) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicAnswer.getMensagem().getTopicoId());
            pstmt.setLong(2, topicAnswer.getAutor().getIdUsuario());
            pstmt.setString(3, topicAnswer.getTxtMensagem());
            pstmt.setDate(4, java.sql.Date.valueOf(topicAnswer.getDataResposta()));
            pstmt.executeQuery();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(TopicAnswer topicAnswer) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE MensagemResposta SET idMensagem = ?, idUsuario = ?, mensagem = ?, dataResposta = ? WHERE idMensagemResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicAnswer.getMensagem().getTopicoId());
            pstmt.setLong(2, topicAnswer.getAutor().getIdUsuario());
            pstmt.setString(3, topicAnswer.getTxtMensagem());
            pstmt.setDate(4, java.sql.Date.valueOf(topicAnswer.getDataResposta()));
            pstmt.setLong(5, topicAnswer.getIdMensagemResposta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public TopicAnswer delete(Long topicAnswerId) throws PersistenceException {
        try {
            TopicAnswer topicAnswer= this.getTopicAnswerById(topicAnswerId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM MensagemResposta WHERE idMensagemResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicAnswerId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return topicAnswer;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicAnswerById(Long topicAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT f.idMensagemResposta idMensagemResposta, f.mensagem respMensagem, f.dataResposta dataResposta, "
                    + "h.idUsuario idUsuarioMR, h.nomeUsuario nomeUsuarioMR, h.loginUsuario loginUsuarioMR, h.senhaUsuario senhaUsuarioMR, h.idtUsuario idtUsuarioMR, h.userPhoto userPhotoMR, "
                    + "g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.idtDificuldade idtDificuldade, b.enunciadoQuestao enunciadoQuestao, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, c.descModulo descModulo "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, d.descDominio descDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM MensagemResposta f "
                    + "JOIN usuario h ON h.idUsuario = f.idUsuario "
                    + "JOIN Mensagem g ON f.idMensagem = g.idMensagem "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE a.idQuestao = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, topicAnswerId);
            ResultSet rs = pstmt.executeQuery();
            User autorResposta = new User();
            User autorTopico = new User();
            User autorQuestao = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            Question questao = new Question();
            Forum forum = new Forum();
            Topic topico = new Topic();
            TopicAnswer resposta = new TopicAnswer();
            if (rs.next()) {
                autorResposta.setIdUsuario(rs.getLong("idUsuarioMR"));
                autorResposta.setNomeUsuario(rs.getString("nomeUsuarioMR"));
                autorResposta.setLoginUsuario(rs.getString("loginUsuarioMR"));
                autorResposta.setSenhaUsuario(rs.getString("senhaUsuarioMR"));
                autorResposta.setIdtUsuario(rs.getString("idtUsuarioMR").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoMR");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorResposta.setFotoUsuario(image);
                
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                blob = rs.getBinaryStream("userPhotoM");  
                  
                image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                
                mod.setDescModulo(rs.getString("descModulo"));
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
                
                resposta.setAutor(autorResposta);
                resposta.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                resposta.setIdMensagemResposta(rs.getLong("idMensagemResposta"));
                resposta.setMensagem(topico);
                resposta.setTxtMensagem(rs.getString("respMensagem"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return resposta;
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
    public List<TopicAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT f.idMensagemResposta idMensagemResposta, f.mensagem respMensagem, f.dataResposta dataResposta, "
                    + "h.idUsuario idUsuarioMR, h.nomeUsuario nomeUsuarioMR, h.loginUsuario loginUsuarioMR, h.senhaUsuario senhaUsuarioMR, h.idtUsuario idtUsuarioMR, h.userPhoto userPhotoMR, "
                    + "g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.idtDificuldade idtDificuldade, b.enunciadoQuestao enunciadoQuestao, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, c.descModulo descModulo "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, d.descDominio descDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM MensagemResposta f "
                    + "JOIN usuario h ON h.idUsuario = f.idUsuario "
                    + "JOIN Mensagem g ON f.idMensagem = g.idMensagem "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TopicAnswer> lista = new ArrayList<>();
            while(rs.next()) {
                User autorResposta = new User();
                User autorTopico = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                Forum forum = new Forum();
                Topic topico = new Topic();
                TopicAnswer resposta = new TopicAnswer();
                autorResposta.setIdUsuario(rs.getLong("idUsuarioMR"));
                autorResposta.setNomeUsuario(rs.getString("nomeUsuarioMR"));
                autorResposta.setLoginUsuario(rs.getString("loginUsuarioMR"));
                autorResposta.setSenhaUsuario(rs.getString("senhaUsuarioMR"));
                autorResposta.setIdtUsuario(rs.getString("idtUsuarioMR").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoMR");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorResposta.setFotoUsuario(image);
                
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                blob = rs.getBinaryStream("userPhotoM");  
                  
                image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                
                mod.setDescModulo(rs.getString("descModulo"));
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
                
                resposta.setAutor(autorResposta);
                resposta.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                resposta.setIdMensagemResposta(rs.getLong("idMensagemResposta"));
                resposta.setMensagem(topico);
                resposta.setTxtMensagem(rs.getString("respMensagem"));
                
                lista.add(resposta);
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
    public List<TopicAnswer> getTopicAnswer(Long mensagemID) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT f.idMensagemResposta idMensagemResposta, f.mensagem respMensagem, f.dataResposta dataResposta, "
                    + "h.idUsuario idUsuarioMR, h.nomeUsuario nomeUsuarioMR, h.loginUsuario loginUsuarioMR, h.senhaUsuario senhaUsuarioMR, h.idtUsuario idtUsuarioMR, h.userPhoto userPhotoMR, "
                    + "g.mensagem topicMensagem, g.dataPost dataPost, g.image topicImage, g.idMensagem idTopic "
                    + "i.idUsuario idUsuarioM, i.nomeUsuario nomeUsuarioM, i.loginUsuario loginUsuarioM, i.senhaUsuario senhaUsuarioM, i.idtUsuario idtUsuarioM, i.userPhoto userPhotoM, "
                    + "a.idQuestao idQuestaoForum, a.dataCriacao dataCriacaoForum, a.status statusForum, "
                    + "b.idQuestao idQuestao, b.idtDificuldade idtDificuldade, b.enunciadoQuestao enunciadoQuestao, b.idtQuestao idtQuestao, b.dataCriacao dataCriacaoQuestao, b.tituloQuestao tituloQuestao, b.questPhoto questPhoto "
                    + "c.idModulo idModulo, c.nomeModulo nomeModulo, c.descModulo descModulo "
                    + "d.idDominio idDominio, d.nomeDominio nomeDominio, d.descDominio descDominio, "
                    + "e.idUsuario idUsuarioQ, e.nomeUsuario nomeUsuarioQ, e.loginUsuario loginUsuarioQ, e.senhaUsuario senhaUsuarioQ, e.idtUsuario idtUsuarioQ, e.userPhoto userPhotoQ "
                    + "FROM MensagemResposta f "
                    + "JOIN usuario h ON h.idUsuario = f.idUsuario "
                    + "JOIN Mensagem g ON f.idMensagem = g.idMensagem "
                    + "JOIN usuario i ON i.idUsuario = g.idUsuario "
                    + "JOIN forum a ON a.idQuestao = g.idQuestao "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE g.idMensagem = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, mensagemID);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TopicAnswer> lista = new ArrayList<>();
            while(rs.next()) {
                User autorResposta = new User();
                User autorTopico = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                Forum forum = new Forum();
                Topic topico = new Topic();
                TopicAnswer resposta = new TopicAnswer();
                autorResposta.setIdUsuario(rs.getLong("idUsuarioMR"));
                autorResposta.setNomeUsuario(rs.getString("nomeUsuarioMR"));
                autorResposta.setLoginUsuario(rs.getString("loginUsuarioMR"));
                autorResposta.setSenhaUsuario(rs.getString("senhaUsuarioMR"));
                autorResposta.setIdtUsuario(rs.getString("idtUsuarioMR").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhotoMR");  
                  
                BufferedImage image = ImageIO.read(blob);
                autorResposta.setFotoUsuario(image);
                
                autorTopico.setIdUsuario(rs.getLong("idUsuarioM"));
                autorTopico.setNomeUsuario(rs.getString("nomeUsuarioM"));
                autorTopico.setLoginUsuario(rs.getString("loginUsuarioM"));
                autorTopico.setSenhaUsuario(rs.getString("senhaUsuarioM"));
                autorTopico.setIdtUsuario(rs.getString("idtUsuarioM").charAt(0));
                blob = rs.getBinaryStream("userPhotoM");  
                  
                image = ImageIO.read(blob);
                autorTopico.setFotoUsuario(image);
                
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQ"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQ"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQ"));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQ"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQ").charAt(0));
                blob = rs.getBinaryStream("userPhotoQ");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setNomeModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setDominio(sub);
                
                questao.setCriador(autorQuestao);
                questao.setModulo(mod);
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                questao.setDataCriacao(rs.getDate("dataCriacaoQuestao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
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
                
                resposta.setAutor(autorResposta);
                resposta.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                resposta.setIdMensagemResposta(rs.getLong("idMensagemResposta"));
                resposta.setMensagem(topico);
                resposta.setTxtMensagem(rs.getString("respMensagem"));
                
                lista.add(resposta);
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
