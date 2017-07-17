/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Terror
 */
class EnviarRespostaAberta {
    public static String processa(HttpServletRequest request) throws IOException{
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            
            Long idq = Long.parseLong(request.getParameter("id"));
            OpenQuestionManagement oqm = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            Question question = oqm.getQuestionById(idq);
            LocalDate localdate = LocalDate.now();
            OpenAnswerManagement oam = new OpenAnswerManagementImpl(new OpenAnswerDAOImpl());
            OpenAnswer answer = new OpenAnswer();
            answer.setAutor(user);
            answer.setQuestao(question);
            answer.setResposta(request.getParameter("respostaAberta"));
            answer.setIdtResposta('A');
            answer.setDataResposta(localdate);
            oam.registerQuestionAnswer(answer);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        
        return "index.jsp";
    }
}
