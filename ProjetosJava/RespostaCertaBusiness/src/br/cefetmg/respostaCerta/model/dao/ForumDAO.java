/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ForumDAO {
    public void insert(Forum forum) throws PersistenceException;
    public void update(Forum forum) throws PersistenceException;
    public Forum delete(Long forumId) throws PersistenceException;
    public Forum  getForumById(Long forumId) throws PersistenceException;
    public List<Forum> listAll() throws PersistenceException;
}
