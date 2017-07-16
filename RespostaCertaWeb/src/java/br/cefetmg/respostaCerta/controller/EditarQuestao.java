/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
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
 * @author Aluno
 */
public class EditarQuestao {
    public static String processa(HttpServletRequest request){
        try{
            int idtNovo = Integer.parseInt(request.getParameter("idtNovo"));
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            OpenQuestionManagement openQuestMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ClosedQuestionManagement closedQuestMan = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            Module mod;
            if(idtNovo==1){
                Long idDisc = Long.parseLong(request.getParameter("idDisciplina"));
                mod = new Module();
                mod.setDominio(subMan.getSubjectById(idDisc));
                mod.setNomeModulo(request.getParameter("modulo"));
                modMan.registerModule(mod);
                
            }else{
                if(idtNovo==2){
                    Subject sub = new Subject(request.getParameter("disciplina"));
                    subMan.registerSubject(sub);
                    mod = new Module();
                    mod.setDominio(sub);
                    mod.setNomeModulo(request.getParameter("modulo"));
                    modMan.registerModule(mod);
                }else{
                    mod = modMan.getModuleById(Long.parseLong(request.getParameter("modulo")));
                }
            }
            if("Discursiva".equals(request.getParameter("tipoQuestao"))){
                Question questao = new Question();
                questao.setCriador(userMan.getUserById((Long) request.getSession().getAttribute("usuario")));
                questao.setDataCriacao(LocalDate.now());
                questao.setEnunciadoQuestao(request.getParameter("enunciado"));
                switch(request.getParameter("difQuestao")){
                    case "Facil": questao.setIdtDificuldade('F'); break;
                    case "Moderada": questao.setIdtDificuldade('M'); break;
                    case "Dificil": questao.setIdtDificuldade('D'); break;
                    case "Desafio": questao.setIdtDificuldade('X'); break;
                }
                questao.setIdtQuestao(true);
                if(!"".equals(request.getParameter("blob")))
                    questao.setQuestPhoto(decodeToImage(request.getParameter("blob").substring(22)));
                questao.setTituloQuestao(request.getParameter("titulo"));
                questao.setIdQuestao(Long.parseLong(request.getParameter("idQuestao")));
                questao.setModulo(mod);
                openQuestMan.updateQuestion(questao.getIdQuestao(), questao);
            }else{
                ClosedQuestion questao = new ClosedQuestion();
                questao.setCriador(userMan.getUserById((Long) request.getSession().getAttribute("usuario")));
                questao.setDataCriacao(LocalDate.now());
                questao.setEnunciadoQuestao(request.getParameter("enunciado"));
                switch(request.getParameter("difQuestao")){
                    case "Facil": questao.setIdtDificuldade('F'); break;
                    case "Moderada": questao.setIdtDificuldade('M'); break;
                    case "Dificil": questao.setIdtDificuldade('D'); break;
                    case "Desafio": questao.setIdtDificuldade('X'); break;
                }
                questao.setIdtQuestao(false);
                if(!"".equals(request.getParameter("blob")))
                    questao.setQuestPhoto(decodeToImage(request.getParameter("blob").substring(22)));
                questao.setTituloQuestao(request.getParameter("titulo"));
                questao.setAlt1(request.getParameter("alternativa1"));
                questao.setAlt2(request.getParameter("alternativa2"));
                questao.setAlt3(request.getParameter("alternativa3"));
                questao.setAlt4(request.getParameter("alternativa4"));
                questao.setAlt5(request.getParameter("alternativa5"));
                questao.setCorreta(Integer.parseInt(request.getParameter("altQuestao")));
                questao.setModulo(mod);
                questao.setIdQuestao(Long.parseLong(request.getParameter("idQuestao")));
                closedQuestMan.updateQuestion(questao.getIdQuestao(), questao);
            } 
            return PagMinhasQuestoes.processa(request);
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
