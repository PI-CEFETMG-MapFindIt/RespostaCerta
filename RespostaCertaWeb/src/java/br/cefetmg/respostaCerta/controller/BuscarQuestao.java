/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adalbs
 */
public class BuscarQuestao {
    public static String processa(HttpServletRequest request){
        try{
            ClosedQuestionDAO cq = new ClosedQuestionDAOImpl();
            OpenQuestionDAO co = new OpenQuestionDAOImpl();
            List<Question> questoes  = new ArrayList();
            questoes.addAll(cq.searchClosedQuestion((String) request.getAttribute("query")));
            questoes.addAll(co.searchQuestion((String) request.getAttribute("query")));
            request.setAttribute("questoes",questoes);
            request.setAttribute("query", request.getAttribute("query"));
            return "BuscarQuestao.jsp";
        }catch (PersistenceException e) {
            request.setAttribute("erro", e.getMessage());
            return "Erro.jsp";
        }
    }
}
