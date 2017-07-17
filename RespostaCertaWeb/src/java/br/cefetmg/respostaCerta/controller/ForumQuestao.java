package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagementImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Pedro Almeida
 */
public class ForumQuestao {
    public static String processa(HttpServletRequest request){
        
        Long id = Long.parseLong(request.getParameter("id"));
        TopicManagement tpcMan = new TopicManagementImpl(new TopicDAOImpl());
        OpenQuestionManagement oqMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
        try {
            List<Topic> topico = tpcMan.getTopicsForum(id);
            request.setAttribute("topico", topico);
            Question q = oqMan.getQuestionById(id);
            request.setAttribute("questao", q);
            return "Forum.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }    
}
