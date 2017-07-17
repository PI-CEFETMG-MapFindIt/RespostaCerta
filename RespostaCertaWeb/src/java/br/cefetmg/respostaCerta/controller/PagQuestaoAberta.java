/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Terror
 */
class PagQuestaoAberta {
    public static String processa(HttpServletRequest request) throws IOException{
        Long idq = Long.parseLong(request.getParameter("id"));
        OpenQuestionManagement oqm = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
        try {
            Question q = oqm.getQuestionById(idq);
            request.setAttribute("questao", q);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return "ResponderQuestaoAberta.jsp";
    }
}
