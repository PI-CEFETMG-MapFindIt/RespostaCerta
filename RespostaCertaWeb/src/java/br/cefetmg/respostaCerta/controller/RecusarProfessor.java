package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class RecusarProfessor {
    public static String processa(HttpServletRequest request){
        try {    
            UserManagement usMan = new UserManagementImpl(new UserDAOImpl());
            usMan.removeUser(Long.parseLong(request.getParameter("id")));
            return PagGerenciarCadastro.processa(request);
        }catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
