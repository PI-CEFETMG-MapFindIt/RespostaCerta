package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
public class ModulosDisciplina {
    public static String processa(HttpServletRequest request){
        try {    
            Long id = Long.parseLong(request.getParameter("id"));
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
            Subject sub = subMan.getSubjectById(id);
            request.setAttribute("disciplina", sub);
            request.setAttribute("modulos", modMan.getModulesSubject(id));
            return "ModulosDisciplina.jsp";
        }catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
