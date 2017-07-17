/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import javax.servlet.http.HttpServletRequest;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;


/**
 *
 * @author Adalbs
 */
public class PagQuestaoFechada {
    public static String processa(HttpServletRequest request){
        try{
            Question question = (Question) request.getAttribute("questao");
            ClosedQuestionManagement questionDAO = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            ClosedQuestion closedQuestion = questionDAO.getQuestionById(question.getIdQuestao());
            request.setAttribute("question", closedQuestion);
            return "ResponderQuestaoFechada.jsp";
        }catch (PersistenceException | BusinessException  ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }      
    }
}
