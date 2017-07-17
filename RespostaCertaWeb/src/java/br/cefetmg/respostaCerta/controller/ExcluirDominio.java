package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class ExcluirDominio {
    public static String processa(HttpServletRequest request){
        SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
        try {
            subMan.removeSubject(Long.parseLong(request.getParameter("id")));
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return PagGerenciarCadastro.processa(request);
    }
}
