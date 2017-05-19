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
        return forumDAO.getForumById(forum.getQuestao().getIdQuestao()).getQuestao().getIdQuestao();
    }

    /**
     *
     * @param id // id da questão que terá o forum atualizado
     * @param forum
     * @throws BusinessException
     * @throws PersistenceException
     * Atualiza um forum já existente
     */
    @Override
    public void updateForum(Long id, Forum forum) throws BusinessException, PersistenceException {
        forum.getQuestao().setIdQuestao(id);
        forumDAO.update(forum);
    }

    /**
     *
     * @param id // id do forum que será deletado
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeForum(Long id) throws BusinessException, PersistenceException {
        forumDAO.delete(id);
    }
    /**
     *
     * @param id
     * @return // retorna um objecto do tipo forum, que representa o forum da questão
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Forum getForumById(Long id) throws BusinessException, PersistenceException {
       return forumDAO.getForumById(id);
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
