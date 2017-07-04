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
public class Module {
    private Subject dominio;
    private Long idModulo;
    private String nomeModulo;

    public Module() {
    }

    public Module(Subject dominio, String nomeModulo) {
        this.dominio = dominio;
        this.nomeModulo = nomeModulo;
    }

    public Subject getDominio() {
        return dominio;
    }

    public void setDominio(Subject dominio) {
        this.dominio = dominio;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }
}
