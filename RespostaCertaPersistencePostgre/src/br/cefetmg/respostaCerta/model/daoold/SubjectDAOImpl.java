/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.daoold;

import br.cefetmg.respostaCerta.model.dao.*;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitor
 */
public class SubjectDAOImpl implements SubjectDAO{

    private static SubjectDAOImpl subjectDAO = null;        
  
    private static long subjectCount;
    
    /**
     *
     */
    public SubjectDAOImpl() { 
        subjectCount = 0;
    }

    /**
     *
     * @return
     */
    public static SubjectDAOImpl getInstance() {
        
        if (subjectDAO == null)
            subjectDAO = new SubjectDAOImpl();
        
        return  subjectDAO;
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Subject subject) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO Dominio (nomeDominio) VALUES(?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, subject.getNomeDominio());
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Questao Falhou");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setIdDominio(generatedKeys.getLong(1));
                }
                else {
                    throw new PersistenceException("Criação falhou, sem id's obtidos");
                }
            }
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Subject subject) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE Dominio SET nomeDominio = ? WHERE idDominio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(2, subject.getIdDominio());
            pstmt.setString(1, subject.getNomeDominio());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Subject delete(Long subjectId) throws PersistenceException {
        try {
            Subject dominio= this.getSubjectById(subjectId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM Dominio WHERE idDominio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, subjectId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return dominio;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Subject getSubjectById(Long subjectId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Dominio WHERE idDominio = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, subjectId);
            ResultSet rs = pstmt.executeQuery(); 
            Subject sub = new Subject();
            if (rs.next()) {
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return sub;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Subject> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Dominio ORDER BY nomeDominio";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); 
            ArrayList<Subject> lista = new ArrayList<>();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                lista.add(sub);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return lista;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Subject> searchSubjects(String busca) throws PersistenceException {
        String[] textoSeparado = busca.split(" ");
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String palavras="";
            for(int i=0; i<textoSeparado.length; i++){
                if(i==0){
                    palavras += textoSeparado[i];
                }else{
                    palavras += " & " +textoSeparado[i];
                }
            }
            String SQL ="SELECT idDominio "
                    + "FROM Dominio "
                    + "WHERE "
                    +"to_tsquery('portuguese','"
                    + palavras
                    + "' ) @@ to_tsvector(nomeDominio)";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Subject> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(getSubjectById(rs.getLong("idDominio")));
            }
            return lista;
        }catch(ClassNotFoundException | SQLException e){
            throw new PersistenceException(e.getMessage());
        }
    }
    
}
