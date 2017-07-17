package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
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
 * @author Pedro Otávio & Vitor
 */
public class VisualizarQuestao {

    public static String processa(HttpServletRequest request) {

        Long idq = Long.parseLong(request.getParameter("id"));
        OpenQuestionManagement oqm = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
        ClosedQuestionManagement cqm = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
        try {
            Question q = oqm.getQuestionById(idq);
            if(q.getIdQuestao()==null){
                q = cqm.getQuestionById(idq);
            }
            request.setAttribute("questao", q);
            if (q.isIdtQuestao()) {
                request.setAttribute("idtQ", "Aberta");
            } else {
                request.setAttribute("idtQ", "Fechada");
            }
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return "Questao.jsp";
    }
}
