/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.daoold;

import br.cefetmg.respostaCerta.model.dao.*;
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
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Vitor
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
        if(img!=null){
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
        }else{
            return null;
        }
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
            String sql = "INSERT INTO questao (idModulo, idUsuarioCriador, enunciadoQuestao, idtQuestao, dataCriacao, tituloQuestao, questPhoto, idtDificuldade) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, openQuestion.getModulo().getIdModulo());
            pstmt.setLong(2, openQuestion.getCriador().getIdUsuario());
            pstmt.setString(3, openQuestion.getEnunciadoQuestao());
            pstmt.setBoolean(4, openQuestion.isIdtQuestao());
            pstmt.setDate(5, java.sql.Date.valueOf(openQuestion.getDataCriacao()));
            pstmt.setString(6, openQuestion.getTituloQuestao());
            if(openQuestion.getQuestPhoto()!=null){
                pstmt.setBinaryStream(7, imageToBlob(openQuestion.getQuestPhoto()));
            }else{
                pstmt.setNull(7, Types.NULL);
            }
            pstmt.setString(8, String.valueOf(openQuestion.getIdtDificuldade()));
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Questao Falhou");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    openQuestion.setIdQuestao(generatedKeys.getLong(1));
                }
                else {
                    throw new PersistenceException("Criação falhou, sem id's obtidos");
                }
            }
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
            String sql = "UPDATE questao SET idModulo = ?, idUsuarioCriador = ?, idtQuestao = ?, dataCriacao = ?, enunciadoQuestao = ?, tituloQuestao = ?, questPhoto = ?, idtDificuldade = ? WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, openQuestion.getModulo().getIdModulo());
            pstmt.setLong(2, openQuestion.getCriador().getIdUsuario());
            pstmt.setBoolean(3, openQuestion.isIdtQuestao());
            pstmt.setDate(4, java.sql.Date.valueOf(openQuestion.getDataCriacao()));
            pstmt.setString(5, openQuestion.getEnunciadoQuestao());
            pstmt.setString(6, openQuestion.getTituloQuestao());
            if(openQuestion.getQuestPhoto()!=null){
                pstmt.setBinaryStream(7, imageToBlob(openQuestion.getQuestPhoto()));
            }else{
                pstmt.setNull(7, Types.NULL);
            }
            pstmt.setString(8, String.valueOf(openQuestion.getIdtDificuldade()));
            pstmt.setLong(9, openQuestion.getIdQuestao());
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
                    + "WHERE a.idQuestao = ? AND a.idtQuestao='1' ";
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
                  
                BufferedImage image;
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
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
                  
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
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
                    + "JOIN usuario d ON a.idUsuarioCriador=d.idUsuario "
                    + "WHERE a.idtQuestao='1' "
                    + "ORDER BY a.dataCriacao DESC";
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
                BufferedImage image;
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                  
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
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
                    + "WHERE a.idUsuarioCriador = ? AND a.idtQuestao='1'";
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
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                if(blob!=null)  
                    image = ImageIO.read(blob);
                else
                    image=null;
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
    public List<Question> searchQuestion(String parameter) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String SQL ="SELECT idQuestao "
                    + "FROM questao "
                    + "WHERE idtQuestao='1' AND "
                    +"plainto_tsquery('portuguese','"
                    + parameter
                    + "' ) @@ to_tsvector(tituloQuestao || ' ' || enunciadoQuestao)";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Question> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(getOpenQuestionById(rs.getLong("idQuestao")));
            }
            return lista;
        }catch(ClassNotFoundException | SQLException e){
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Question> getOpenQuestionByModule(Long id) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questao a "
                    + "JOIN modulo b ON b.idModulo=a.idModulo "
                    + "JOIN dominio c ON b.idDominio=c.idDominio "
                    + "JOIN usuario d ON a.idUsuarioCriador=d.idUsuario "
                    + "WHERE b.idModulo = ? AND a.idtQuestao='1'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
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
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                else
                    image=null;
                autor.setFotoUsuario(image);
                questao.setCriador(autor);
                questao.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
                questao.setIdQuestao(rs.getLong("idQuestao"));
                questao.setIdtQuestao(rs.getBoolean("idtQuestao"));
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                questao.setModulo(mod);
                blob = rs.getBinaryStream("questPhoto");  
                if(blob!=null)  
                    image = ImageIO.read(blob);
                else
                    image=null;
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
