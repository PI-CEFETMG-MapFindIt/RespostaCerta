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
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class PagMinhasQuestoes {
    public static String processa(HttpServletRequest request){
        try {
            OpenQuestionManagement opMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ClosedQuestionManagement clMan = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            List<Question> lista = opMan.getQuestionsByUser((Long)request.getSession().getAttribute("usuario"));
            lista.addAll(clMan.getQuestionsByUser((Long)request.getSession().getAttribute("usuario")));
            request.setAttribute("questoes", lista);
            return "MinhasQuestoes.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
