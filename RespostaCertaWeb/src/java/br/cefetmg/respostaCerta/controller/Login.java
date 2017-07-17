package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.LoginManagement;
import br.cefetmg.respostaCerta.model.service.LoginManagementImpl;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class Login {
    public static String processa(HttpServletRequest request){
        String resposta = "";
        String email = request.getParameter("emailUsuario");
        String senha = request.getParameter("senhaUsuario");
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            resposta="Erro.jsp";
            request.setAttribute("erro", ex.getMessage());
            return resposta;
        }
        m.update(senha.getBytes(), 0, senha.length());
        senha = new BigInteger(1,m.digest()).toString(16);
        LoginManagement login = new LoginManagementImpl(new UserDAOImpl());
        User usuario;
        try {
            usuario = login.loginUser(email, senha);
        } catch (BusinessException | PersistenceException ex) {
            resposta="Erro.jsp";
            request.setAttribute("erro", ex.getMessage());
            return resposta;
        }
        if(usuario==null){
            request.setAttribute("falha", true);
            return "index.jsp";
        }else{
            request.getSession().setAttribute("usuario", usuario.getIdUsuario());
            
            resposta="index.jsp";
        }
        return resposta;
    }
}
