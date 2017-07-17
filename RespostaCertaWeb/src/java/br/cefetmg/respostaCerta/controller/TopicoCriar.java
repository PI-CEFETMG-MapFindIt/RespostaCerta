package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagement;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/*@author Pedro Almeida*/
public class TopicoCriar {
    public static String processa(HttpServletRequest request){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            
            TopicManagement tpcMan = new TopicManagementImpl(new TopicDAOImpl());
            TopicAnswerManagement tpcAMan = new TopicAnswerManagementImpl(new TopicAnswerDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            
            User autor = userMan.getUserById((Long) request.getSession().getAttribute("usuario"));
            String mensagem = request.getParameter("mensagem");
            LocalDate data = LocalDate.now();
            Topic topico = tpcMan.getTopicById(id);
            
            TopicAnswer tA = new TopicAnswer();
            
            tA.setAutor(autor);
            tA.setDataResposta(data);
            tA.setMensagem(topico);
            tA.setTxtMensagem(mensagem);
            tpcAMan.registerTopicAnswer(tA);
            
            request.setAttribute("topico", topico);
            return TopicoQuestao.processa(request);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }   
}
