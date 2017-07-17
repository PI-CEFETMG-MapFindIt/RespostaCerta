package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Terror
 */
public class Perfil {
    public static String processa(HttpServletRequest request) throws IOException{
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
                        
            request.setAttribute("usuario", user);
            request.setAttribute("mensagem", "");
            return "Perfil.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
