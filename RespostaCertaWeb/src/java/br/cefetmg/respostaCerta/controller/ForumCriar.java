package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ForumDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ForumManagement;
import br.cefetmg.respostaCerta.model.service.ForumManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;

/**
 * 
 * @author Pedro Almeida
 */
public class ForumCriar {
    public static String processa(HttpServletRequest request){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            
            OpenQuestionManagement oqMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            TopicManagement tpcMan = new TopicManagementImpl(new TopicDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            ForumManagement forMan = new ForumManagementImpl(new ForumDAOImpl(), new TopicDAOImpl());
            
            User autor = userMan.getUserById((Long) request.getSession().getAttribute("usuario"));
            String mensagem = request.getParameter("mensagem");
            LocalDate data = LocalDate.now();
            Forum forum = forMan.getForumById(id);
            
            Topic t = new Topic();
            t.setAutor(autor);
            t.setDataPostagem(data);
            t.setForum(forum);
            t.setTxtMensagem(mensagem);
            if(!"".equals(request.getParameter("blob")))
                t.setMsgPhoto(decodeToImage(request.getParameter("blob").substring(22)));
            tpcMan.registerTopic(t);
            
            Question q = oqMan.getQuestionById(id);
            request.setAttribute("questao", q);
            
            return ForumQuestao.processa(request);
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
