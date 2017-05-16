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
public class Subject {
    private Long idDominio;
    private String nomeDominio;
    private String descDominio;

    public Subject() {
    }

    public Subject(Long idDominio, String nomeDominio, String descDominio) {
        this.idDominio = idDominio;
        this.nomeDominio = nomeDominio;
        this.descDominio = descDominio;
    }

    public Long getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Long idDominio) {
        this.idDominio = idDominio;
    }

    public String getNomeDominio() {
        return nomeDominio;
    }

    public void setNomeDominio(String nomeDominio) {
        this.nomeDominio = nomeDominio;
    }

    public String getDescDominio() {
        return descDominio;
    }

    public void setDescDominio(String descDominio) {
        this.descDominio = descDominio;
    }
    
    
}
