/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagement;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class ExcluirRespostaTopico {
    public static String processa(HttpServletRequest request){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            TopicAnswerManagement tpcAMan = new TopicAnswerManagementImpl(new TopicAnswerDAOImpl());
            Topic top = tpcAMan.getTopicAnswerById(id).getMensagem();
            tpcAMan.removeTopicAnswer(id);
            return TopicoQuestao.processa(request, top.getTopicoId());
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }   
}
