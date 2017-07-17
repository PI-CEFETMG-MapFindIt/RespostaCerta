/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author umcan
 */
public class UserDAOImpl implements UserDAO{

    private static UserDAOImpl userDAO = null;        

    private static long userCount;
    
    /**
     *
     */
    public UserDAOImpl() { 
        userCount = 0;
    }

    /**
     *
     * @return
     */
    public static UserDAOImpl getInstance() {
        
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        
        return  userDAO;
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
     * @param user
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(User user) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO Usuario (nomeUsuario, loginUsuario, senhaUsuario, idtUsuario, userPhoto) VALUES(?, ?, ?, ?, ?) RETURNING idUsuario";
            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getNomeUsuario());
            pstmt.setString(2, user.getLoginUsuario());
            pstmt.setString(3, user.getSenhaUsuario());
            pstmt.setString(4, String.valueOf(user.getIdtUsuario()));
            if(user.getFotoUsuario()!=null){
                pstmt.setBinaryStream(5, imageToBlob(user.getFotoUsuario()));
            }else{
                pstmt.setNull(5, Types.NULL);
            }
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param user
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(User user) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE Usuario SET nomeUsuario = ?, loginUsuario = ?, senhaUsuario = ?, idtUsuario = ?, userPhoto = ? WHERE idUsuario = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getNomeUsuario());
            pstmt.setString(2, user.getLoginUsuario());
            pstmt.setString(3, user.getSenhaUsuario());
            pstmt.setString(4, String.valueOf(user.getIdtUsuario()));
            if(user.getFotoUsuario()!=null){
                pstmt.setBinaryStream(5, imageToBlob(user.getFotoUsuario()));
            }else{
                pstmt.setNull(5, Types.NULL);
            }
            pstmt.setLong(6, user.getIdUsuario());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param userId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public User delete(Long userId) throws PersistenceException {
        try {
            User usuario= this.getUserById(userId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return usuario;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param userId
     * @return
     * @throws PersistenceException
     */
    @Override
    public User getUserById(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery(); 
            User usuario = new User();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setLoginUsuario(rs.getString("loginUsuario"));
                usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
                usuario.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                usuario.setFotoUsuario(image);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return usuario;
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
    public List<User> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); 
            ArrayList<User> lista = new ArrayList<>();
            while(rs.next()) {
                User usuario = new User();
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setLoginUsuario(rs.getString("loginUsuario"));
                usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
                usuario.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                usuario.setFotoUsuario(image);
                lista.add(usuario);
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
    public User getUserByLogin(String email, String senha) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario WHERE loginUsuario = ? AND senhaUsuario = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery(); 
            User usuario = null;
            if(rs.next()) {
                usuario = new User();
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setLoginUsuario(rs.getString("loginUsuario"));
                usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
                usuario.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhoto");
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                usuario.setFotoUsuario(image);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return usuario;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    @Override
    public List<User> getUserByIdt(char idt) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario WHERE idtUsuario=?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(idt));
            ResultSet rs = pstmt.executeQuery(); 
            List<User> usuarios = new ArrayList<>();
            while(rs.next()) {
                User usuario = new User();
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setLoginUsuario(rs.getString("loginUsuario"));
                usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
                usuario.setIdtUsuario(rs.getString("idtUsuario").charAt(0));
                InputStream blob = rs.getBinaryStream("userPhoto");  
                BufferedImage image=null;
                if(blob!=null)
                    image = ImageIO.read(blob);
                usuario.setFotoUsuario(image);
                usuarios.add(usuario);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return usuarios;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
}
