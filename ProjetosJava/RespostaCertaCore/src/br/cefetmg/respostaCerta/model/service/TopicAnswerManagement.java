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
    public Long registerTopic(TopicAnswer topicAnswer) throws BusinessException, PersistenceException;
    public void updateTopic(Long id, TopicAnswer  topicAnswer) throws BusinessException, PersistenceException;
    public void removeTopic(Long id) throws BusinessException, PersistenceException;
    public TopicAnswer getTopicById(Long id) throws BusinessException, PersistenceException;
    public List<TopicAnswer> getTopicAnswers(Long id) throws BusinessException, PersistenceException;
}
