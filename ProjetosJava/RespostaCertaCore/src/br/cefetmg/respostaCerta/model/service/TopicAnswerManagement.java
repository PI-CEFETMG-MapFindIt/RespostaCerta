/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface TopicAnswerManagement {
    public void registerTopicAnswer(TopicAnswer topicAnswer) throws BusinessException, PersistenceException;
    public void updateTopicAnswer(Long id, TopicAnswer  topicAnswer) throws BusinessException, PersistenceException;
    public void removeTopicAnswer(Long id) throws BusinessException, PersistenceException;
    public TopicAnswer getTopicAnswerById(Long id) throws BusinessException, PersistenceException;
    public List<TopicAnswer> getAnswersTopic(Long id) throws BusinessException, PersistenceException;
}
