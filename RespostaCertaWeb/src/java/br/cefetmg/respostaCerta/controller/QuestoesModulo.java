/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class QuestoesModulo {
    public static String processa(HttpServletRequest request){
        try {    
            Long id = Long.parseLong(request.getParameter("id"));
            ClosedQuestionManagement cq = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            OpenQuestionManagement co = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            request.setAttribute("modulo", modMan.getModuleById(id));
            List<Question> questoes = cq.getClosedQuestionByModule(id);
            questoes.addAll(co.getOpenQuestionByModule(id));
            request.setAttribute("questoes", questoes);
            return "QuestoesModulo.jsp";
        }catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
