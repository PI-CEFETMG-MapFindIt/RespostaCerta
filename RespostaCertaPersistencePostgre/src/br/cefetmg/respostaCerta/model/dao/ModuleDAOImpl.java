/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
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
public class ModuleDAOImpl implements ModuleDAO{

    private static ModuleDAOImpl moduleDAO = null;        
  
    private static long moduleCount;
    
    /**
     *
     */
    public ModuleDAOImpl() { 
        moduleCount = 0;
    }

    /**
     *
     * @return
     */
    public static ModuleDAOImpl getInstance() {
        
        if (moduleDAO == null)
            moduleDAO = new ModuleDAOImpl();
        
        return  moduleDAO;
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Module module) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO Modulo (idDominio, nomeModulo) VALUES(?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, module.getDominio().getIdDominio());
            pstmt.setString(2, module.getNomeModulo());
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new PersistenceException("Criação da Questao Falhou");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    module.setIdModulo(generatedKeys.getLong(1));
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
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Module module) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE modulo SET idDominio = ?, nomeModulo = ? WHERE idModulo = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, module.getDominio().getIdDominio());
            pstmt.setString(2, module.getNomeModulo());
            pstmt.setLong(3, module.getIdModulo());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Module delete(Long moduleId) throws PersistenceException {
        try {
            Module modulo= this.getModuleById(moduleId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM modulo WHERE idModulo = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return modulo;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio "
                    + "WHERE a.idModulo = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            ResultSet rs = pstmt.executeQuery();
            Subject sub = new Subject();
            Module mod = new Module();
            if (rs.next()) {
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return mod;
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
    public List<Module> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio ORDER BY nomeModulo";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Module> lista = new ArrayList<>();
            while(rs.next()) {
                Subject sub = new Subject();
                Module mod = new Module();
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                lista.add(mod);
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
    public List<Module> getModulesSubject(long subjectId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio "
                    + "WHERE b.idDominio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, subjectId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Module> lista = new ArrayList<>();
            while(rs.next()) {
                Subject sub = new Subject();
                Module mod = new Module();
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                lista.add(mod);
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
    public List<Module> searchModules(String busca) throws PersistenceException {
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
            String SQL ="SELECT idModulo "
                    + "FROM Modulo "
                    + "WHERE "
                    +"to_tsquery('portuguese','"
                    + palavras
                    + "' ) @@ to_tsvector(nomeModulo)";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Module> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(getModuleById(rs.getLong("idModulo")));
            }
            return lista;
        }catch(ClassNotFoundException | SQLException e){
            throw new PersistenceException(e.getMessage());
        }
    }
    
}
