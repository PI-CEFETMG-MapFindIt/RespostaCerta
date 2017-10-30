/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author umcan
 */
public class User implements Serializable{
    private Long idUsuario;
    private String nomeUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private char idtUsuario;
    private Image fotoUsuario;

    public User() {
    }
    
    public User(Long idUsuario, String nomeUsuario, String loginUsuario, String senhaUsuario, char idtUsuario, Image fotoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.idtUsuario = idtUsuario;
        this.fotoUsuario=fotoUsuario;
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

    public Image getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(Image fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    
}
