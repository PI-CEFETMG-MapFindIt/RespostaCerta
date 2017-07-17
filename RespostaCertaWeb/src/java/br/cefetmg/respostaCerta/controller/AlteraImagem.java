package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Terror & Vitor
 */
class AlteraImagem {
    public static String processa(HttpServletRequest request) throws IOException{
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            if(!"".equals(request.getParameter("blob"))){
                user.setFotoUsuario(decodeToImage(request.getParameter("blob").substring(22)));
            }
            userMan.updateUser(user.getIdUsuario(), user);
            request.setAttribute("usuario", user);
            request.setAttribute("mensagem", "");
            return "Perfil.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
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
