/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class Logout {
    public static String processa(HttpServletRequest request){
        request.getSession().removeAttribute("usuario");
        return "index.jsp";
    }
}
