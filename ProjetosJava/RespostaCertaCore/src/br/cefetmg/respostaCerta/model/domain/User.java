/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

/**
 *
 * @author umcan
 */
public class User {
    private Long idUsuario;
    private String nomeUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private char idtUsuario;

    public User() {
    }

    public User(String nomeUsuario, String loginUsuario, String senhaUsuario, char idtUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.idtUsuario = idtUsuario;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public char getIdtUsuario() {
        return idtUsuario;
    }

    public void setIdtUsuario(char idtUsuario) {
        this.idtUsuario = idtUsuario;
    }
    
}
