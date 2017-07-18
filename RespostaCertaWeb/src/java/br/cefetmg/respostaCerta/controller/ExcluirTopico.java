/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class ExcluirTopico {
    public static String processa(HttpServletRequest request){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            TopicManagement tpcMan = new TopicManagementImpl(new TopicDAOImpl());
            Long idForum = tpcMan.getTopicById(id).getForum().getIdForum();
            tpcMan.removeTopic(id);
            return ForumQuestao.processa(request, idForum);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }   
}
