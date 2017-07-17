package br.cefetmg.respostaCerta.controller;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class Logout {
    public static String processa(HttpServletRequest request){
        request.getSession().removeAttribute("usuario");
        return "index.jsp";
    }
}
