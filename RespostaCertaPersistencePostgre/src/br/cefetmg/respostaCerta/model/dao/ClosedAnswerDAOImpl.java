/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.util.db.ConnectionManager;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

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
    /**
     *
     * @param respostaFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedAnswer respostaFechada) throws PersistenceException {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO resposta (idUsuario, idQuestao, idtResposta, dataResposta) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        try{
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
        } catch (ClassNotFoundException | SQLException e) {
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
            return resposta;
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
    public ClosedAnswer getClosedAnswerById(Long closedId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT a.resposta resposta, b.idResposta idResposta, b.idtResposta idtResposta, b.dataResposta dataResposta, "
                    + "c.idUsuario idUsuario, c.nomeUsuario nomeUsuario, c.loginUsuario loginUsuario, c.senhaUsuario senhaUsuario, c.idtUsuario idtUsuario, "
                    + "c.userPhoto userPhoto, d.enunciadoQuestao enunciadoQuestao, d.idtQuestao idtQuestao, d.dataCriacao dataCriacao, d.idQuestao idQuestao, "
                    + "d.tituloQuestao tituloQuestao, d.idtDificuldade idtDificuldade, d.questPhoto questPhoto, e.nomeModulo nomeModulo, e.idModulo idModulo, "
                    + "f.nomeDominio nomeDominio, f.idDominio idDominio, "
                    + "g.idUsuario idUsuarioQuestao, g.nomeUsuario nomeUsuarioQuestao, g.loginUsuario loginUsuarioQuestao, g.senhaUsuario senhaUsuarioQuestao, "
                    + "g.idtUsuario idtUsuarioQuestao, g.userPhoto userPhotoQuestao, "
                    + "h.alt1 alt1, h.alt2 alt2, h.alt3 alt3, h.alt4 alt4, h.alt5 alt5, h.altCorreta altCorreta FROM respostaFechada a "
                    + "JOIN resposta b ON a.idResposta=b.idResposta "
                    + "JOIN Usuario c ON c.idUsuario=b.idUsuario "
                    + "JOIN Questao d ON d.idQuestao=b.idQuestao "
                    + "JOIN Modulo e ON e.idModulo=d.idModulo "
                    + "JOIN Dominio f ON f.idDominio=e.idDominio"
                    + "JOIN Usuario g ON g.idUsuario=d.idUsuarioCriador "
                    + "JOIN QuestaoFechada h ON d.idQuestao=h.idQuestao "
                    + "WHERE a.idResposta = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedId);
            ResultSet rs = pstmt.executeQuery();
            ClosedAnswer closed = new ClosedAnswer();
            User autor = new User();
            User autorQuestao = new User();
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
                  
                BufferedImage image = ImageIO.read(blob);
                autor.setFotoUsuario(image);
                closed.setAutor(autor);
                closed.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                closed.setIdResposta(rs.getLong("idResposta"));
                closed.setIdtResposta(rs.getString("idtResposta").charAt(0));
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQuestao"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQuestao"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQuestao"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQuestao").charAt(0));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQuestao"));
                blob = rs.getBinaryStream("userPhotoQuestao");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                questao.setCriador(autorQuestao);
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
                  
                if(blob!=null){
                    image = ImageIO.read(blob);
                    questao.setQuestPhoto(image);
                }
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
                closed.setQuestao(questao);
                closed.setResposta(rs.getInt("resposta"));
                closed.setCorreta(closed.getResposta()==closed.getQuestao().getCorreta());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return closed;
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
    public List<ClosedAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT a.resposta resposta, b.idResposta idResposta, b.idtResposta idtResposta, b.dataResposta dataResposta, "
                    + "c.idUsuario idUsuario, c.nomeUsuario nomeUsuario, c.loginUsuario loginUsuario, c.senhaUsuario senhaUsuario, c.idtUsuario idtUsuario, "
                    + "c.userPhoto userPhoto, d.enunciadoQuestao enunciadoQuestao, d.idtQuestao idtQuestao, d.dataCriacao dataCriacao, d.idQuestao idQuestao, "
                    + "d.tituloQuestao tituloQuestao, d.idtDificuldade idtDificuldade, d.questPhoto questPhoto, e.nomeModulo nomeModulo, e.idModulo idModulo, "
                    + "f.nomeDominio nomeDominio, f.idDominio idDominio, "
                    + "g.idUsuario idUsuarioQuestao, g.nomeUsuario nomeUsuarioQuestao, g.loginUsuario loginUsuarioQuestao, g.senhaUsuario senhaUsuarioQuestao, "
                    + "g.idtUsuario idtUsuarioQuestao, g.userPhoto userPhotoQuestao, "
                    + "h.alt1 alt1, h.alt2 alt2, h.alt3 alt3, h.alt4 alt4, h.alt5 alt5, h.altCorreta altCorreta FROM respostaFechada a "
                    + "JOIN resposta b ON a.idResposta=b.idResposta "
                    + "JOIN Usuario c ON c.idUsuario=b.idUsuario "
                    + "JOIN Questao d ON d.idQuestao=b.idQuestao "
                    + "JOIN Modulo e ON e.idModulo=d.idModulo "
                    + "JOIN Dominio f ON f.idDominio=e.idDominio "
                    + "JOIN Usuario g ON g.idUsuario=d.idUsuarioCriador "
                    + "JOIN QuestaoFechada h ON d.idQuestao=h.idQuestao";
            ArrayList<ClosedAnswer> lista = new ArrayList<>();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ClosedAnswer closed = new ClosedAnswer();
                User autor = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                ClosedQuestion questao = new ClosedQuestion();
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                  
                BufferedImage image = ImageIO.read(blob);
                autor.setFotoUsuario(image);
                closed.setAutor(autor);
                closed.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                closed.setIdResposta(rs.getLong("idResposta"));
                closed.setIdtResposta(rs.getString("idtResposta").charAt(0));
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQuestao"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQuestao"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQuestao"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQuestao").charAt(0));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQuestao"));
                blob = rs.getBinaryStream("userPhotoQuestao");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                questao.setCriador(autorQuestao);
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
                  
                if(blob!=null){
                    image = ImageIO.read(blob);
                    questao.setQuestPhoto(image);
                }
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
                closed.setQuestao(questao);
                closed.setResposta(rs.getInt("resposta"));
                closed.setCorreta(closed.getResposta()==closed.getQuestao().getCorreta());
                lista.add(closed);
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
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT a.resposta resposta, b.idResposta idResposta, b.idtResposta idtResposta, b.dataResposta dataResposta, "
                    + "c.idUsuario idUsuario, c.nomeUsuario nomeUsuario, c.loginUsuario loginUsuario, c.senhaUsuario senhaUsuario, c.idtUsuario idtUsuario, "
                    + "c.userPhoto userPhoto, d.enunciadoQuestao enunciadoQuestao, d.idtQuestao idtQuestao, d.dataCriacao dataCriacao, d.idQuestao idQuestao, "
                    + "d.tituloQuestao tituloQuestao, d.idtDificuldade idtDificuldade, d.questPhoto questPhoto, e.nomeModulo nomeModulo, e.idModulo idModulo, "
                    + "f.nomeDominio nomeDominio, f.idDominio idDominio, "
                    + "g.idUsuario idUsuarioQuestao, g.nomeUsuario nomeUsuarioQuestao, g.loginUsuario loginUsuarioQuestao, g.senhaUsuario senhaUsuarioQuestao, "
                    + "g.idtUsuario idtUsuarioQuestao, g.userPhoto userPhotoQuestao, "
                    + "h.alt1 alt1, h.alt2 alt2, h.alt3 alt3, h.alt4 alt4, h.alt5 alt5, h.altCorreta altCorreta FROM respostaFechada a "
                    + "JOIN resposta b ON a.idResposta=b.idResposta "
                    + "JOIN Usuario c ON c.idUsuario=b.idUsuario "
                    + "JOIN Questao d ON d.idQuestao=b.idQuestao "
                    + "JOIN Modulo e ON e.idModulo=d.idModulo "
                    + "JOIN Dominio f ON f.idDominio=e.idDominio "
                    + "JOIN Usuario g ON g.idUsuario=d.idUsuarioCriador "
                    + "JOIN QuestaoFechada h ON d.idQuestao=h.idQuestao "
                    + "WHERE c.idUsuario=?";
            ArrayList<ClosedAnswer> lista = new ArrayList<>();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ClosedAnswer closed = new ClosedAnswer();
                User autor = new User();
                User autorQuestao = new User();
                Subject sub = new Subject();
                Module mod = new Module();
                ClosedQuestion questao = new ClosedQuestion();
                autor.setIdUsuario(rs.getLong("idUsuario"));
                autor.setNomeUsuario(rs.getString("nomeUsuario"));
                autor.setLoginUsuario(rs.getString("loginUsuario"));
                autor.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                autor.setSenhaUsuario(rs.getString("senhaUsuario"));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                  
                BufferedImage image = ImageIO.read(blob);
                autor.setFotoUsuario(image);
                closed.setAutor(autor);
                closed.setDataResposta(rs.getDate("dataResposta").toLocalDate());
                closed.setIdResposta(rs.getLong("idResposta"));
                closed.setIdtResposta(rs.getString("idtResposta").charAt(0));
                autorQuestao.setIdUsuario(rs.getLong("idUsuarioQuestao"));
                autorQuestao.setNomeUsuario(rs.getString("nomeUsuarioQuestao"));
                autorQuestao.setLoginUsuario(rs.getString("loginUsuarioQuestao"));
                autorQuestao.setIdtUsuario(rs.getString("idtUsuarioQuestao").charAt(0));
                autorQuestao.setSenhaUsuario(rs.getString("senhaUsuarioQuestao"));
                blob = rs.getBinaryStream("userPhotoQuestao");  
                  
                image = ImageIO.read(blob);
                autorQuestao.setFotoUsuario(image);
                questao.setCriador(autorQuestao);
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
                if(blob!=null){
                    image = ImageIO.read(blob);
                    questao.setQuestPhoto(image);
                }
                questao.setTituloQuestao(rs.getString("tituloQuestao"));
                questao.setAlt1(rs.getString("alt1"));
                questao.setAlt2(rs.getString("alt2"));
                questao.setAlt3(rs.getString("alt3"));
                questao.setAlt4(rs.getString("alt4"));
                questao.setAlt5(rs.getString("alt5"));
                questao.setCorreta(rs.getInt("altCorreta"));
                closed.setQuestao(questao);
                closed.setResposta(rs.getInt("resposta"));
                closed.setCorreta(closed.getResposta()==closed.getQuestao().getCorreta());
                lista.add(closed);
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