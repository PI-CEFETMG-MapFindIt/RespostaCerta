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
public class ClosedAnswer extends QuestionAnswer{
    private int resposta;
    private boolean correta;

    public ClosedAnswer() {
    }

    public ClosedAnswer(int resposta, boolean correta, User autor, Question questao, LocalDate dataResposta, char idtResposta) {
        super(autor, questao, dataResposta, idtResposta);
        this.resposta = resposta;
        this.correta = correta;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
    
    
}
