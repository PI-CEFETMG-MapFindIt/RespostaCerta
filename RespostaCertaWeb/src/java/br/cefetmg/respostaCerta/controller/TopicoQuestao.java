package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagement;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagementImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/*@author Pedro Almeida*/
class TopicoQuestao {
    public static String processa(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id")); 
        TopicManagement tpcMan = new TopicManagementImpl(new TopicDAOImpl());
        OpenQuestionManagement oqMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
        TopicAnswerManagement tpcAMan = new TopicAnswerManagementImpl(new TopicAnswerDAOImpl());
        try {
            List<TopicAnswer> r = tpcAMan.getAnswersTopic(id);
            request.setAttribute("respostas", r);
            Topic t = tpcMan.getTopicById(id);
            request.setAttribute("topico", t);
            Question q = oqMan.getQuestionById(id);
            request.setAttribute("questao", q);
            return "Topico.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
