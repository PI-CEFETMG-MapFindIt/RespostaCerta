/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adalbs
 */
public class BuscarQuestao {
    public static String processa(HttpServletRequest request){
        try{
            ClosedQuestionManagement cq = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            OpenQuestionManagement co = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            List<Question> questoes  = new ArrayList();
            questoes.addAll(cq.searchClosedQuestion(request.getParameter("query")));
            questoes.addAll(co.searchQuestion(request.getParameter("query")));
            request.setAttribute("questoes",questoes);
            request.setAttribute("query", request.getAttribute("query"));
            return "BuscarQuestao.jsp";
        }catch (PersistenceException | BusinessException  e) {
            request.setAttribute("erro", e.getMessage());
            return "Erro.jsp";
        } 
    }
}
