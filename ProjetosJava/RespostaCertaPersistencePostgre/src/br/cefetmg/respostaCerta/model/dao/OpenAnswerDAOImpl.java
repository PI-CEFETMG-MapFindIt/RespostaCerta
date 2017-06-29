/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class OpenAnswerDAOImpl implements OpenAnswerDAO{

    private static OpenAnswerDAOImpl openAnswerDAO = null;        
   
    private static long openAnswerCount;
    
    public OpenAnswerDAOImpl() { 
        openAnswerCount = 0;
    }

    /**
     *
     * @return
     */
    public static OpenAnswerDAOImpl getInstance() {
        
        if (openAnswerDAO == null)
            openAnswerDAO = new OpenAnswerDAOImpl();
        
        return  openAnswerDAO;
    }
    
    /**
     *
     * @param openAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(OpenAnswer openAnswer) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO resposta (idUsuario, idQuestao, idtResposta, dataResposta) VALUES(?, ?, ?, ?) RETURNING idResposta";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswer.getAutor().getIdUsuario());
            pstmt.setLong(2, openAnswer.getQuestao().getIdQuestao());
            pstmt.setString(3, String.valueOf(openAnswer.getIdtResposta()));
            pstmt.setDate(4, java.sql.Date.valueOf(openAnswer.getDataResposta()));
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Resposta Falhou");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    openAnswer.setIdResposta(generatedKeys.getLong(1));
                }
                else {
                    throw new PersistenceException("Criação falhou, sem id's obtidos");
                }
            }
            sql = "INSERT INTO respostaAberta (idResposta, resposta) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswer.getIdResposta());
            pstmt.setString(2, openAnswer.getResposta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param openAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(OpenAnswer openAnswer) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE resposta SET idUsuario = ?, idQuestao = ?, idtResposta = ?, dataResposta = ? WHERE idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswer.getAutor().getIdUsuario());
            pstmt.setLong(2, openAnswer.getQuestao().getIdQuestao());
            pstmt.setString(3, String.valueOf(openAnswer.getIdtResposta()));
            pstmt.setDate(4, java.sql.Date.valueOf(openAnswer.getDataResposta()));
            pstmt.setLong(5, openAnswer.getIdResposta());
            pstmt.executeUpdate();
            sql = "UPDATE respostaAberta SET resposta = ? WHERE idResposta = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, openAnswer.getResposta());
            pstmt.setLong(2, openAnswer.getIdResposta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param openAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public OpenAnswer delete(Long openAnswerId) throws PersistenceException {
        try {
            OpenAnswer resposta= this.getOpenAnswerById(openAnswerId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM resposta WHERE idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswerId);
            pstmt.executeUpdate();
            sql = "DELETE FROM RespostaAberta WHERE idResposta = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswerId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return resposta;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param openAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer getOpenAnswerById(Long openAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT a.resposta resposta, b.idResposta idResposta, b.idtResposta idtResposta, b.dataResposta dataResposta, "
                    + "c.idUsuario idUsuario, c.nomeUsuario nomeUsuario, c.loginUsuario loginUsuario, c.senhaUsuario senhaUsuario, c.idtUsuario idtUsuario, "
                    + "c.userPhoto userPhoto, d.enunciadoQuestao enunciadoQuestao, d.idtQuestao idtQuestao, d.dataCriacao dataCriacao, d.idQuestao idQuestao "
                    + "d.tituloQuestao tituloQuestao, d.questPhoto questPhoto, e.nomeModulo nomeModulo, e.descModulo descModulo, e.idModulo idModulo "
                    + "f.nomeDominio nomeDominio, f.descDominio descDominio, f.idDominio idDominio "
                    + "g.idUsuario idUsuarioQuestao, g.nomeUsuario nomeUsuarioQuestao, g.loginUsuario loginUsuarioQuestao, g.senhaUsuario senhaUsuarioQuestao, "
                    + "g.idtUsuario idtUsuarioQuestao, g.userPhoto userPhotoQuestao, "
                    + "FROM respostaAberta a "
                    + "JOIN resposta b ON a.idResposta=b.idResposta "
                    + "JOIN Usuario c ON c.idUsuario=b.idUsuario "
                    + "JOIN Questao d ON d.idQuestao=b.idQuestao "
                    + "JOIN Modulo e ON e.idModulo=d.idModulo "
                    + "JOIN Dominio f ON f.idDominio=e.idDominio"
                    + "JOIN Usuario g ON g.idUsuario=d.idUsuarioCriador "
                    + "WHERE a.idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openAnswerId);
            ResultSet rs = pstmt.executeQuery();
            OpenAnswer open = new OpenAnswer();
            User autor = new User();
            User autorQuestao = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            Question questao = new Question();
            if (rs.next()) {
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                Blob blob = rs.getBlob("userPhoto");  
                InputStream in = blob.getBinaryStream();  
                BufferedImage image = ImageIO.read(in);
                autor.setFotoUsuario(image);
                open.setAutor(autor);
                open.setDataResposta(rs.getDate("dataRespostaQuestao").toLocalDate());
                open.setIdResposta(rs.getLong("idRespostaQuestao"));
                open.setIdtResposta(rs.getString("idtRespostaQuestao").charAt(0));
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQuestao"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQuestao"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQuestao"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQuestao").charAt(0));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQuestao"));
                blob = rs.getBlob("userPhotoQuestao");  
                in = blob.getBinaryStream();  
                image = ImageIO.read(in);
                autorQuestao.setFotoUsuario(image);
                questao.setCriador(autorQuestao);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBlob("questPhoto");  
                in = blob.getBinaryStream();  
                image = ImageIO.read(in);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                open.setQuestao(questao);
                open.setResposta(rs.getString("resposta"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return open;
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
    public List<OpenAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT a.resposta resposta, b.idResposta idResposta, b.idtResposta idtResposta, b.dataResposta dataResposta, "
                    + "c.idUsuario idUsuario, c.nomeUsuario nomeUsuario, c.loginUsuario loginUsuario, c.senhaUsuario senhaUsuario, c.idtUsuario idtUsuario, "
                    + "c.userPhoto userPhoto, d.enunciadoQuestao enunciadoQuestao, d.idtQuestao idtQuestao, d.dataCriacao dataCriacao, d.idQuestao idQuestao "
                    + "d.tituloQuestao tituloQuestao, d.questPhoto questPhoto, e.nomeModulo nomeModulo, e.descModulo descModulo, e.idModulo idModulo "
                    + "f.nomeDominio nomeDominio, f.descDominio descDominio, f.idDominio idDominio "
                    + "g.idUsuario idUsuarioQuestao, g.nomeUsuario nomeUsuarioQuestao, g.loginUsuario loginUsuarioQuestao, g.senhaUsuario senhaUsuarioQuestao, "
                    + "g.idtUsuario idtUsuarioQuestao, g.userPhoto userPhotoQuestao, "
                    + "FROM respostaAberta a "
                    + "JOIN resposta b ON a.idResposta=b.idResposta "
                    + "JOIN Usuario c ON c.idUsuario=b.idUsuario "
                    + "JOIN Questao d ON d.idQuestao=b.idQuestao "
                    + "JOIN Modulo e ON e.idModulo=d.idModulo "
                    + "JOIN Dominio f ON f.idDominio=e.idDominio"
                    + "JOIN Usuario g ON g.idUsuario=d.idUsuarioCriador";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<OpenAnswer> lista = new ArrayList();
            while(rs.next()) {
                OpenAnswer open = new OpenAnswer();
                User autor = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                Blob blob = rs.getBlob("userPhoto");  
                InputStream in = blob.getBinaryStream();  
                BufferedImage image = ImageIO.read(in);
                autor.setFotoUsuario(image);
                open.setAutor(autor);
                open.setDataResposta(rs.getDate("dataRespostaQuestao").toLocalDate());
                open.setIdResposta(rs.getLong("idRespostaQuestao"));
                open.setIdtResposta(rs.getString("idtRespostaQuestao").charAt(0));
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQuestao"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQuestao"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQuestao"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQuestao").charAt(0));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQuestao"));
                blob = rs.getBlob("userPhotoQuestao");  
                in = blob.getBinaryStream();  
                image = ImageIO.read(in);
                autorQuestao.setFotoUsuario(image);
                questao.setCriador(autorQuestao);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBlob("questPhoto");  
                in = blob.getBinaryStream();  
                image = ImageIO.read(in);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                open.setQuestao(questao);
                open.setResposta(rs.getString("resposta"));
                lista.add(open);
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
