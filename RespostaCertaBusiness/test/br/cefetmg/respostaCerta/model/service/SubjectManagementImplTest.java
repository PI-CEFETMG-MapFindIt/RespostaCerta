/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.SubjectDAO;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAO;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author umcan
 */
public class SubjectManagementImplTest {
    
    private static SubjectDAO subjectDAO;
    private static SubjectManagementImpl impl;
    
    public SubjectManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        subjectDAO = SubjectDAOImpl.getInstance();
        impl = new SubjectManagementImpl(subjectDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Subject> su;
        try {
            su = subjectDAO.listAll();
            for(Subject s : su){
                subjectDAO.delete(s.getIdDominio());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerSubject method, of class SubjectManagementImpl.
     */
    @Test
    public void testRegisterSubject1() throws Exception {
        System.out.println("registerSubject1");
        Subject subject = null;
        try{
            impl.registerSubject(subject);
        }catch(BusinessException ex){
            assertTrue("Subject não pode ser nulo".equals(ex.getMessage()));
            return;
        }
        fail("Aceitou usuario nulo");
    }
    
    @Test
    public void testRegisterSubject3() throws Exception {
       System.out.println("registerSubject3");
        Subject subject = new Subject();
        subject.setNomeDominio(null);
        try{
            impl.registerSubject(subject);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do dominio não pode ser nulo"));
            return;
        }
        fail("Aceitou dominio com nome nulo");
    }

    /**
     * Test of registerSubject method, of class SubjectManagementImpl.
     */
    @Test
    public void testUpdateSubject1() throws Exception {
        System.out.println("updateSubject1");
        Subject subject = null;
        try{
            impl.updateSubject(new Long(0), subject);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Subject não pode ser nulo"));
            return;
        }
        fail("Aceitou dominio nulo");
    }
    
    @Test
    public void testUpdateSubject3() throws Exception {
       System.out.println("updateSubject3");
        Subject subject = new Subject();
        subject.setNomeDominio(null);
        try{
            impl.updateSubject(new Long(0), subject);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do dominio não pode ser nulo"));
            return;
        }
        fail("Aceitou dominio com nome nulo");
    }
    
    /**
     * Test of updateSubject method, of class SubjectManagementImpl.
     */
    @Test
    public void testUpdateSubject4() throws Exception {
        System.out.println("updateSubject4");
        Subject subject = new Subject("Dominio");
        try{
            impl.updateSubject(null, subject);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou ID nulo");
    }

    /**
     * Test of removeSubject method, of class SubjectManagementImpl.
     */
    @Test
    public void testRemoveSubject1() throws Exception {
        System.out.println("removeSubject1");
        Long id = null;
        try{
           impl.removeSubject(id); 
        }catch(BusinessException ex){
           assertEquals(ex.getMessage(), "ID não pode ser nulo");
           return;
        }
        fail("Aceitou id nulo");
    }
    
    
    
    /**
     * Test of removeSubject method, of class SubjectManagementImpl.
     */
    @Test
    public void testGetSubjectById1() throws Exception {
        System.out.println("getSubjectById1");
        Long id = null;
        try{
           impl.getSubjectById(id);
        }catch(BusinessException ex){
           assertEquals(ex.getMessage(), "ID não pode ser nulo");
           return;
        }
        
        fail("Aceitou id nulo");
    }
    
}
