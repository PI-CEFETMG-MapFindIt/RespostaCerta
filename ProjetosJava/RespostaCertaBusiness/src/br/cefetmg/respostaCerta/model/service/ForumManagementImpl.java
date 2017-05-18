/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ForumDAO;
import java.util.List;

/**
 *
 * @author umcan
 */
public class ForumManagementImpl implements ForumManagement{
    private final ForumDAO forumDAO;
    public ForumManagementImpl (ForumDAO forumDAO){
        this.forumDAO= forumDAO;
    }

    /**
     *
     * @param forum
     * @return 
     * @throws BusinessException
     * @throws PersistenceException
     * 
     */
    @Override
    public Long registerForum(Forum forum) throws BusinessException, PersistenceException {
        forumDAO.insert(forum);
        return forum.getQuestao().getIdQuestao();
    }

    /**
     *
     * @param id
     * @param forum
     * @throws BusinessException
     * @throws PersistenceException
     * Atualiza um forum já existente
     */
    @Override
    public void updateForum(Long id, Forum forum) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeForum(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Forum getForumById(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public List<Topic> getTopicsForum(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
