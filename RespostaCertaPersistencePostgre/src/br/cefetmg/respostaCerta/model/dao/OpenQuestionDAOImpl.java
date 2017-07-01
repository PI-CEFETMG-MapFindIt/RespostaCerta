/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class OpenQuestionDAOImpl implements OpenQuestionDAO{
    private static OpenQuestionDAOImpl openQuestionDAO = null;        
   
    private static long openQuestionCount;
    
    /**
     *
     */
    public OpenQuestionDAOImpl() { 
        openQuestionCount = 0;
    }

    /**
     *
     * @return
     */
    public static OpenQuestionDAOImpl getInstance() {
        
        if (openQuestionDAO == null)
            openQuestionDAO = new OpenQuestionDAOImpl();
        
        return  openQuestionDAO;
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
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Question openQuestion) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO questao (idModulo, idUsuarioCriador, enunciadoQuestao, idtQuestao, dataCriacao, tituloQuestao, questPhoto) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openQuestion.getModulo().getIdModulo());
            pstmt.setLong(2, openQuestion.getCriador().getIdUsuario());
            pstmt.setString(3, openQuestion.getEnunciadoQuestao());
            pstmt.setBoolean(4, openQuestion.isIdtQuestao());
            pstmt.setDate(5, java.sql.Date.valueOf(openQuestion.getDataCriacao()));
            pstmt.setString(6, openQuestion.getTituloQuestao());   
            pstmt.setBinaryStream(7, imageToBlob(openQuestion.getQuestPhoto()));
            pstmt.executeQuery();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Question openQuestion) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE questao SET idModulo = ?, idUsuarioCriador = ?, idtQuestao = ?, dataCriacao = ?, enunciadoQuestao = ?, tituloQuestao = ?, questPhoto = ? WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openQuestion.getModulo().getIdModulo());
            pstmt.setLong(2, openQuestion.getCriador().getIdUsuario());
            pstmt.setBoolean(3, openQuestion.isIdtQuestao());
            pstmt.setDate(4, java.sql.Date.valueOf(openQuestion.getDataCriacao()));
            pstmt.setString(5, openQuestion.getEnunciadoQuestao());
            pstmt.setString(6, openQuestion.getTituloQuestao());
            pstmt.setBinaryStream(7, imageToBlob(openQuestion.getQuestPhoto()));
            pstmt.setLong(8, openQuestion.getIdQuestao());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Question delete(Long openQuestionId) throws PersistenceException {
        try {
            Question questao= this.getOpenQuestionById(openQuestionId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM questao WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openQuestionId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return questao;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Question getOpenQuestionById(Long openQuestionId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questao a "
                    + "JOIN modulo b ON b.idModulo=a.idModulo "
                    + "JOIN dominio c ON b.idDominio=c.idDominio "
                    + "JOIN usuario d ON a.idUsuarioCriador=d.idUsuario "
                    + "WHERE a.idQuestao = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openQuestionId);
            ResultSet rs = pstmt.executeQuery();
            User autor = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            Question questao = new Question();
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
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return questao;
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
    public List<Question> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questao a "
                    + "JOIN modulo b ON b.idModulo=a.idModulo "
                    + "JOIN dominio c ON b.idDominio=c.idDominio "
                    + "JOIN usuario d ON a.idUsuarioCriador=d.idUsuario ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Question> lista = new ArrayList<>();
            while(rs.next()) {
                User autor = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
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
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                lista.add(questao);
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
    public List<Question> getOpenQuestionsByUser(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questao a "
                    + "JOIN modulo b ON b.idModulo=a.idModulo "
                    + "JOIN dominio c ON b.idDominio=c.idDominio "
                    + "JOIN usuario d ON a.idUsuarioCriador=d.idUsuario "
                    + "WHERE b.idUsuarioCriador = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Question> lista = new ArrayList<>();
            while(rs.next()) {
                User autor = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                Question questao = new Question();
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
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                image = ImageIO.read(blob);
                questao.setQuestPhoto(image);
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                lista.add(questao);
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
