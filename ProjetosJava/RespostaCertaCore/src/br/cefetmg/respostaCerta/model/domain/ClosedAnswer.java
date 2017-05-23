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

    public ClosedAnswer() {
    }

    public ClosedAnswer(int resposta, User autor, Question questao, LocalDate dataResposta, char idtResposta, boolean correta) {
        super(autor, questao, dataResposta, idtResposta, correta);
        this.resposta = resposta;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }
}
