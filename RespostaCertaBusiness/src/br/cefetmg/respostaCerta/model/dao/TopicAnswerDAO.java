/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;


import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface TopicAnswerDAO {
    public void insert(TopicAnswer respostaTopico) throws PersistenceException;
    public void update(TopicAnswer respostaTopico) throws PersistenceException;
    public TopicAnswer delete(Long respostaTopicoId) throws PersistenceException;
    public TopicAnswer getTopicAnswerById(Long respostaTopicoId) throws PersistenceException;
    public List<TopicAnswer> listAll() throws PersistenceException;
}
