package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class PagCadastrarQuestao {
    public static String processa(HttpServletRequest request){
        ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
        SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
        try {
            request.setAttribute("modulos", modMan.getAllModules());
            request.setAttribute("dominios", subMan.getAllSubjects());
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return "CadastroQuestao.jsp";
    }
}
