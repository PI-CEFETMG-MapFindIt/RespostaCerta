/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ForumDAO {

    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    public void insert(Forum forum) throws PersistenceException;

    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    public void update(Forum forum) throws PersistenceException;

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    public Forum delete(Long forumId) throws PersistenceException;

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    public Forum  getForumById(Long forumId) throws PersistenceException;

    /**
     *
     * @param questãoID
     * @return
     * @throws PersistenceException
     * retorna todas as mensagens e sub mensagens recebendo como parametro o id da questão
     */
    public List<TopicAnswer>getForumAnswer(Long questãoID) throws PersistenceException;

    /**
     *
     * @return
     * @throws PersistenceException
     */
    public List<Forum> listAll() throws PersistenceException;
}
