/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adalbs
 */
public class QuestaoFechada {
    public static String processa(HttpServletRequest request){
        try{
            Question question = (Question) request.getAttribute("questao");
            ClosedQuestionDAO questionDAO = new ClosedQuestionDAOImpl();
            ClosedQuestion closedQuestion = questionDAO.getClosedQuestionById(question.getIdQuestao());
            request.setAttribute("question", closedQuestion);
            return "ResponderQuestaoFechada.jsp";
        }catch (PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }      
    }
}
