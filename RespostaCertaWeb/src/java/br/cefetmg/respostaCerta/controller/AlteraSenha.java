package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Terror
 */
public class AlteraSenha {
    public static String processa(HttpServletRequest request){
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            MessageDigest m = MessageDigest.getInstance("MD5");
            
            String senhaAtual = request.getParameter("senhaAtualUsuario");
            m.update(senhaAtual.getBytes(), 0, senhaAtual.length());
            senhaAtual = new BigInteger(1,m.digest()).toString(16);
            
            String novaSenha = request.getParameter("novaSenhaUsuario");
            m.update(novaSenha.getBytes(), 0, novaSenha.length());
            novaSenha = new BigInteger(1,m.digest()).toString(16);

            if(!senhaAtual.equals(user.getSenhaUsuario().substring(0, 32))){
                request.setAttribute("mensagem", "Senha incorreta!");
            } else {
                request.setAttribute("mensagem", "Senha alterada com sucesso!");
                user.setSenhaUsuario(novaSenha);
                userMan.updateUser(user.getIdUsuario(), user);
            }
            
            request.setAttribute("usuario", user);
            return "Perfil.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        } catch (NoSuchAlgorithmException ex) {
            request.setAttribute("mensagem", "Erro de MD5, contate o administrador do sistema!");
            return "Perfil.jsp";
        }
    }
}
