/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface TopicDAO {
    public void insert(Topic topico) throws PersistenceException;
    public void update(Topic topico) throws PersistenceException;
    public Topic delete(Long topicoId) throws PersistenceException;
    public Topic  getTopicById(Long topicoId) throws PersistenceException;
    public List<Topic> listAll() throws PersistenceException;
}
