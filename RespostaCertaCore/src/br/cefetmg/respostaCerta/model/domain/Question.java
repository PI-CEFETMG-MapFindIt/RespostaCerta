/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.awt.Image;
import java.time.LocalDate;

/**
 *
 * @author umcan
 */
public class Question {
    private Module modulo;
    private User criador;
    private Long idQuestao;
    private String enunciadoQuestao;
    private boolean idtQuestao;
    private LocalDate dataCriacao;
    private String tituloQuestao;
    private Image questPhoto;

    public Question() {
    }

    public Question(Module modulo, User criador, String enunciadoQuestao, boolean idtQuestao, LocalDate dataCriacao, String tituloQuestao, Image questPhoto) {
        this.modulo = modulo;
        this.criador = criador;
        this.enunciadoQuestao = enunciadoQuestao;
        this.idtQuestao = idtQuestao;
        this.dataCriacao = dataCriacao;
        this.tituloQuestao = tituloQuestao;
        this.questPhoto = questPhoto;
    }

    public Module getModulo() {
        return modulo;
    }

    public void setModulo(Module modulo) {
        this.modulo = modulo;
    }

    public User getCriador() {
        return criador;
    }

    public void setCriador(User criador) {
        this.criador = criador;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getEnunciadoQuestao() {
        return enunciadoQuestao;
    }

    public void setEnunciadoQuestao(String enunciadoQuestao) {
        this.enunciadoQuestao = enunciadoQuestao;
    }

    public boolean isIdtQuestao() {
        return idtQuestao;
    }

    public void setIdtQuestao(boolean idtQuestao) {
        this.idtQuestao = idtQuestao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTituloQuestao() {
        return tituloQuestao;
    }

    public void setTituloQuestao(String tituloQuestao) {
        this.tituloQuestao = tituloQuestao;
    }

    public Image getQuestPhoto() {
        return questPhoto;
    }

    public void setQuestPhoto(Image questPhoto) {
        this.questPhoto = questPhoto;
    }
}
