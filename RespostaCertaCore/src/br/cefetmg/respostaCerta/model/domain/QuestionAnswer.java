/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author umcan
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "idtResposta", length = 1, discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("R")
public class QuestionAnswer implements Serializable{
    @ManyToOne
    private User autor;
    @Temporal(TemporalType.DATE)
    private LocalDate dataResposta;
    @Column(insertable=false, updatable=false)
    private char idtResposta;
    @Id
    @GeneratedValue
    private Long idResposta;
    private boolean correta;

    public QuestionAnswer() {
    }

    public QuestionAnswer(User autor, LocalDate dataResposta, char idtResposta, boolean correta) {
        this.autor = autor;
        this.dataResposta = dataResposta;
        this.idtResposta = idtResposta;
        this.correta=correta;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public LocalDate getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDate dataResposta) {
        this.dataResposta = dataResposta;
    }

    public char getIdtResposta() {
        return idtResposta;
    }

    public void setIdtResposta(char idtResposta) {
        this.idtResposta = idtResposta;
    }
    
    public Long getIdResposta(){
        return this.idResposta;
    }
    
    public void setIdResposta(Long idResposta){
        this.idResposta=idResposta;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }  
}
