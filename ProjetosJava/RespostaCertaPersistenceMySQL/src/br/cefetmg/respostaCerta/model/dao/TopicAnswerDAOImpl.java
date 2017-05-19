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
public class TopicAnswerDAOImpl implements TopicAnswerDAO{

    /**
     *
     * @param respostaTopico
     * @throws PersistenceException
     */
    @Override
    public void insert(TopicAnswer respostaTopico) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param respostaTopico
     * @throws PersistenceException
     */
    @Override
    public void update(TopicAnswer respostaTopico) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param respostaTopicoId
     * @return
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer delete(Long respostaTopicoId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param respostaTopicoId
     * @return
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicAnswerById(Long respostaTopicoId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<TopicAnswer> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
