package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class PagEditarQuestao {
    public static String processa(HttpServletRequest request){
        try {
            Long idQuestao = Long.parseLong(request.getParameter("id"));
            OpenQuestionManagement openMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            Question q = openMan.getQuestionById(idQuestao);
            if(q==null || !q.isIdtQuestao()){
                ClosedQuestionManagement closedMan = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
                ClosedQuestion qCl= closedMan.getQuestionById(q.getIdQuestao());
                request.setAttribute("questao", qCl);
            }else{
                request.setAttribute("questao", q);
            }
            //Set modulos e dominios
            PagCadastrarQuestao.processa(request);
            return "EditarQuestao.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
