/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class ExcluirModulo {
    public static String processa(HttpServletRequest request){
        ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
        try {
            modMan.removeModule(Long.parseLong(request.getParameter("id")));
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return PagGerenciarCadastro.processa(request);
    }
}
