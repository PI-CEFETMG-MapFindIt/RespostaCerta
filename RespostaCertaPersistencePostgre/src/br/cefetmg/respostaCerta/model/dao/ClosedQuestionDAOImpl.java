/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class ClosedQuestionDAOImpl implements ClosedQuestionDAO{
    private static ClosedQuestionDAOImpl closedDAO = null;        

    private static long closedCount;
    
    /**
     *
     */
    public ClosedQuestionDAOImpl() { 
        closedCount = 0;
    }

    /**
     *
     * @return
     */
    public static ClosedQuestionDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedQuestionDAOImpl();
        
        return  closedDAO;
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
     * @param questaoFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedQuestion questaoFechada) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO questao (idModulo, idUsuarioCriador, enunciadoQuestao, idtQuestao, dataCriacao, tituloQuestao, questPhoto, idtDificuldade) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, questaoFechada.getModulo().getIdModulo());
            pstmt.setLong(2, questaoFechada.getCriador().getIdUsuario());
            pstmt.setString(3, questaoFechada.getEnunciadoQuestao());
            pstmt.setBoolean(4, questaoFechada.isIdtQuestao());
            pstmt.setDate(5, java.sql.Date.valueOf(questaoFechada.getDataCriacao()));
            pstmt.setString(6, questaoFechada.getTituloQuestao());
            if(questaoFechada.getQuestPhoto()!=null){
                pstmt.setBinaryStream(7, imageToBlob(questaoFechada.getQuestPhoto()));
            }else{
                pstmt.setNull(7, Types.NULL);
            }
            pstmt.setString(8, String.valueOf(questaoFechada.getIdtDificuldade()));
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Questao Falhou");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    questaoFechada.setIdQuestao(generatedKeys.getLong(1));
                }
                else {
                    throw new PersistenceException("Criação falhou, sem id's obtidos");
                }
            }
            sql = "INSERT INTO questaoFechada (idQuestao, alt1, alt2, alt3, alt4, alt5, altCorreta) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questaoFechada.getIdQuestao());
            pstmt.setString(2, questaoFechada.getAlt1());
            pstmt.setString(3, questaoFechada.getAlt2());
            pstmt.setString(4, questaoFechada.getAlt3());
            pstmt.setString(5, questaoFechada.getAlt4());
            pstmt.setString(6, questaoFechada.getAlt5());
            pstmt.setInt(7, questaoFechada.getCorreta());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (PersistenceException | ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param closedQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(ClosedQuestion closedQuestion) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE questao SET idModulo = ?, idUsuarioCriador = ?, idtQuestao = ?, dataCriacao = ?, enunciadoQuestao = ?, tituloQuestao = ?, questPhoto = ?, idtDificuldade = ? WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedQuestion.getModulo().getIdModulo());
            pstmt.setLong(2, closedQuestion.getCriador().getIdUsuario());
            pstmt.setBoolean(3, closedQuestion.isIdtQuestao());
            pstmt.setDate(4, java.sql.Date.valueOf(closedQuestion.getDataCriacao()));
            pstmt.setString(5, closedQuestion.getEnunciadoQuestao());
            pstmt.setString(6, closedQuestion.getTituloQuestao());
            pstmt.setBinaryStream(7, imageToBlob(closedQuestion.getQuestPhoto()));
            pstmt.setString(9, String.valueOf(closedQuestion.getIdtDificuldade()));
            pstmt.setLong(10, closedQuestion.getIdQuestao());
            pstmt.executeUpdate();
            sql = "UPDATE respostaFechada SET alt1 = ?, alt2 = ?, alt3 = ?, alt4 = ?, alt5 = ?, altCorreta = ? WHERE idQuestao = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, closedQuestion.getAlt1());
            pstmt.setString(2, closedQuestion.getAlt2());
            pstmt.setString(3, closedQuestion.getAlt3());
            pstmt.setString(4, closedQuestion.getAlt4());
            pstmt.setString(5, closedQuestion.getAlt5());
            pstmt.setInt(6, closedQuestion.getCorreta());
            pstmt.setLong(7, closedQuestion.getIdQuestao());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException | IOException e) {
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
    synchronized public ClosedQuestion delete(Long closedId) throws PersistenceException {
        try {
            ClosedQuestion questao= this.getClosedQuestionById(closedId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM questao WHERE idQuestao = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            pstmt.executeUpdate();
            sql = "DELETE FROM QuestaoFechada WHERE idQuestao = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
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
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    public ClosedQuestion getClosedQuestionById(Long closedId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questaoFechada a "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE a.idQuestao = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            ResultSet rs = pstmt.executeQuery();
            User autor = new User();
            Subject sub = new Subject();
            Module mod = new Module();
            ClosedQuestion questao = new ClosedQuestion();
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
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
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
    public List<ClosedQuestion> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questaoFechada a "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ClosedQuestion> lista = new ArrayList<>();
            while(rs.next()){
                User autor = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                ClosedQuestion questao = new ClosedQuestion();
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
                questao.setIdtDificuldade(rs.getString("idtDificuldade").charAt(0));
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
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
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
    public List<ClosedQuestion> getClosedQuestionsByUser(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM questaoFechada a "
                    + "JOIN questao b ON a.idQuestao=b.idQuestao "
                    + "JOIN modulo c ON b.idModulo=c.idModulo "
                    + "JOIN dominio d ON c.idDominio=d.idDominio "
                    + "JOIN usuario e ON b.idUsuarioCriador=e.idUsuario "
                    + "WHERE b.idUsuarioCriador = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ClosedQuestion> lista = new ArrayList<>();
            while(rs.next()){
                User autor = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                ClosedQuestion questao = new ClosedQuestion();
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
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
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
