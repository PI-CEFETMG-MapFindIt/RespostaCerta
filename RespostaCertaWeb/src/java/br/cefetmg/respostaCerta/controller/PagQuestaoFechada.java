package br.cefetmg.respostaCerta.controller;

import javax.servlet.http.HttpServletRequest;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;


/**
 *
 * @author Adalberto & Vitor
 */
public class PagQuestaoFechada {
    public static String processa(HttpServletRequest request){
        try{
            ClosedQuestionManagement questionDAO = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            ClosedQuestion closedQuestion = questionDAO.getQuestionById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("question", closedQuestion);
            request.setAttribute("respondida", Boolean.valueOf(false));
            return "ResponderQuestaoFechada.jsp";
        }catch (PersistenceException | BusinessException  ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }      
    }
}
