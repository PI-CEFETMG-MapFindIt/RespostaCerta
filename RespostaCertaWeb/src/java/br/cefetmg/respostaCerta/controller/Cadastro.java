package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.LoginManagement;
import br.cefetmg.respostaCerta.model.service.LoginManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Vitor
 */
public class Cadastro {
    public static String processa(HttpServletRequest request){
        User usuario = new User();
        usuario.setIdtUsuario(request.getParameter("tipo").charAt(0));//A = Aluno e E=Professor em espera
        String email=request.getParameter("email");
        usuario.setLoginUsuario(email);
        usuario.setNomeUsuario(request.getParameter("primNome") + " " + request.getParameter("ultimoNome"));
        try {
            if(!"".equals(request.getParameter("blob"))){
                usuario.setFotoUsuario(decodeToImage(request.getParameter("blob").substring(22)));
            }
        } catch (BusinessException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        String senha = request.getParameter("password");
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        m.update(senha.getBytes(), 0, senha.length());
        String senhaMd5 = new BigInteger(1,m.digest()).toString(16);
        usuario.setSenhaUsuario(senhaMd5);
        UserManagement user = new UserManagementImpl(new UserDAOImpl());
        try {
            user.registerUser(usuario);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        LoginManagement login = new LoginManagementImpl(new UserDAOImpl());
        try {
            usuario=login.loginUser(email, senhaMd5);
            request.getSession().setAttribute("usuario", usuario.getIdUsuario());
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return "index.jsp";
    }
    
    private static BufferedImage decodeToImage(String imageString) throws BusinessException {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            throw new BusinessException("Erro na imagem");
        }
        image = redimensionar(image, 300, 300);
        return image;
    }
    
    private static BufferedImage redimensionar(Image originalImage, int scaledWidth, int scaledHeight){
        int imageType = BufferedImage.TYPE_INT_RGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose();
        return scaledBI;
    }
}
