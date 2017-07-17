package br.cefetmg.respostaCerta.controller;

import javax.servlet.http.HttpServletRequest;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.time.LocalDate;

/**
 *
 * @author Adalbs & Vitor
 */
public class ResponderQuestaoFechada {

    public static String processa(HttpServletRequest request) {
        try {
            LocalDate localdate = LocalDate.now();
            ClosedAnswerManagement mcq = new ClosedAnswerManagementImpl(new ClosedAnswerDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long) request.getSession().getAttribute("usuario"));
            ClosedQuestionManagement manQuest = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            ClosedAnswer answer = new ClosedAnswer();
            answer.setAutor(user);
            answer.setDataResposta(localdate);
            answer.setQuestao(manQuest.getQuestionById(Long.parseLong(request.getParameter("id"))));
            answer.setResposta(Integer.parseInt(request.getParameter("option")));
            if (answer.getResposta() == answer.getQuestao().getCorreta()) {
                answer.setCorreta(true);
            } else {
                answer.setCorreta(false);
            }
            answer.setIdtResposta('F');
            request.setAttribute("escolha", Integer.valueOf(answer.getResposta()));
            request.setAttribute("question", answer.getQuestao());
            request.setAttribute("respondida", Boolean.valueOf(true));
            mcq.registerQuestionAnswer(answer);
            return "ResponderQuestaoFechada.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
