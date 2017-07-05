/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class ExcluirQuestao {
    public static String processa(HttpServletRequest request){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            OpenQuestionManagement op = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ClosedQuestionManagement cl = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            op.removeQuestion(id);
            cl.removeQuestion(id);
            return PagMinhasQuestoes.processa(request);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
