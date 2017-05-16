/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.time.LocalDate;

/**
 *
 * @author umcan
 */
public class Thread {
    private Question questao;
    private LocalDate dataCriacao;
    private boolean status;

    public Thread() {
    }

    public Thread(Question questao, LocalDate dataCriacao, boolean status) {
        this.questao = questao;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }
    
    public Question getQuestao() {
        return questao;
    }

    public void setQuestao(Question questao) {
        this.questao = questao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
