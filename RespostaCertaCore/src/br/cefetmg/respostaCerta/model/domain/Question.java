/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.awt.Image;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author umcan
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "idtQuestao", length = 1, discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class Question implements Serializable{
    @ManyToOne
    private Module modulo;
    @ManyToOne
    private User criador;
    @Id
    @GeneratedValue
    private Long idQuestao;
    private String enunciadoQuestao;
    @Column(insertable=false, updatable=false)
    private boolean idtQuestao;
    @Temporal(TemporalType.DATE)
    private LocalDate dataCriacao;
    private String tituloQuestao;
    @Lob
    private Image questPhoto;
    private char idtDificuldade;

    public Question() {
    }

    /**
     *
     * @param modulo
     * @param criador
     * @param enunciadoQuestao
     * @param idtQuestao
     * @param dataCriacao
     * @param tituloQuestao
     * @param questPhoto
     * @param idtDificuldade
     */
    public Question(Module modulo, User criador, String enunciadoQuestao, boolean idtQuestao, LocalDate dataCriacao, String tituloQuestao, Image questPhoto, char idtDificuldade) {
        this.modulo = modulo;
        this.criador = criador;
        this.enunciadoQuestao = enunciadoQuestao;
        this.idtQuestao = idtQuestao;
        this.dataCriacao = dataCriacao;
        this.tituloQuestao = tituloQuestao;
        this.questPhoto = questPhoto;
        this.idtDificuldade=idtDificuldade;
    }

    /**
     *
     * @return
     */
    public Module getModulo() {
        return modulo;
    }

    /**
     *
     * @param modulo
     */
    public void setModulo(Module modulo) {
        this.modulo = modulo;
    }

    /**
     *
     * @return
     */
    public User getCriador() {
        return criador;
    }

    /**
     *
     * @param criador
     */
    public void setCriador(User criador) {
        this.criador = criador;
    }

    /**
     *
     * @return
     */
    public Long getIdQuestao() {
        return idQuestao;
    }

    /**
     *
     * @param idQuestao
     */
    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    /**
     *
     * @return
     */
    public String getEnunciadoQuestao() {
        return enunciadoQuestao;
    }

    /**
     *
     * @param enunciadoQuestao
     */
    public void setEnunciadoQuestao(String enunciadoQuestao) {
        this.enunciadoQuestao = enunciadoQuestao;
    }

    /**
     *
     * @return
     */
    public boolean isIdtQuestao() {
        return idtQuestao;
    }

    /**
     *
     * @param idtQuestao
     */
    public void setIdtQuestao(boolean idtQuestao) {
        this.idtQuestao = idtQuestao;
    }

    /**
     *
     * @return
     */
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    /**
     *
     * @param dataCriacao
     */
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     *
     * @return
     */
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

    public char getIdtDificuldade() {
        return idtDificuldade;
    }

    public void setIdtDificuldade(char idtDificuldade) {
        this.idtDificuldade = idtDificuldade;
    }
    
}
