/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ModuleDAO;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
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
 * @author pedro
 */
public class ModuleManagementImplTest {
    private static ModuleDAO moduleDAO;
    private static ModuleManagementImpl impl;
    
    public ModuleManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        moduleDAO = ModuleDAOImpl.getInstance();
        impl = new ModuleManagementImpl(moduleDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Module> us;
        try {
            us = moduleDAO.listAll();
            for(Module a : us){
                moduleDAO.delete(a.getIdModulo());
            }
        } catch (PersistenceException ex) {
            System.out.println("Erro!");
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerModule method, of class ModuleManagementImpl.
     */
    @Test
    public void testRegisterModule1() throws Exception {
        System.out.println("RegisterModule1");
        try{
            impl.registerModule(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou módulo nulo");
    }

    @Test
    public void testRegisterModule2() throws Exception {
        System.out.println("RegisterModule2");
        Module modulo = new Module();
        modulo.setNomeModulo("");
        modulo.setDescModulo("");
        
        try{
            impl.registerModule(modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Dominio não pode ser nulo"));
            return;
        }
        fail("Aceitou dominio nulo");
    }
    
    @Test
    public void testRegisterModule3() throws Exception {
        System.out.println("RegisterModule3");
        Module modulo = new Module();
        modulo.setDominio(new Subject());
        modulo.setDescModulo("");
        
        try{
            impl.registerModule(modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou nome nulo");
    }
    
    @Test
    public void testRegisterModule4() throws Exception {
        System.out.println("RegisterModule4");
        Module modulo = new Module();
        modulo.setDominio(new Subject());
        modulo.setNomeModulo("");
        
        try{
            impl.registerModule(modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Descrição do modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou descrição nula");
    }

    /**
     * Test of updateModule method, of class ModuleManagementImpl.
     */
    @Test
    public void testUpdateModule1() throws Exception {
        System.out.println("UpdateModule1");
        try{
            impl.updateModule(new Long(1),null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou módulo nulo");
    }

    @Test
    public void testUpdateModule2() throws Exception {
        System.out.println("UpdateModule2");
        Module modulo = new Module();
        modulo.setNomeModulo("");
        modulo.setDescModulo("");
        
        try{
            impl.updateModule(new Long(1),modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Dominio não pode ser nulo"));
            return;
        }
        fail("Aceitou dominio nulo");
    }
    
    @Test
    public void testUpdateModule3() throws Exception {
        System.out.println("UpdateModule3");
        Module modulo = new Module();
        modulo.setDominio(new Subject());
        modulo.setDescModulo("");
        
        try{
            impl.updateModule(new Long(1),modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Nome do modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou nome nulo");
    }
    
    @Test
    public void testUpdateModule4() throws Exception {
        System.out.println("UpdateModule4");
        Module modulo = new Module();
        modulo.setDominio(new Subject());
        modulo.setNomeModulo("");
        
        try{
            impl.updateModule(new Long(1),modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("Descrição do modulo não pode ser nulo"));
            return;
        }
        fail("Aceitou descrição nula");
    }
    
    @Test
    public void testUpdateModule5() throws Exception {
        System.out.println("UpdateModule5");
        Module modulo = new Module();
        modulo.setDominio(new Subject());
        modulo.setNomeModulo("");
        modulo.setDescModulo("");
        try{
            impl.updateModule(null,modulo);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of removeModule method, of class ModuleManagementImpl.
     */
    @Test
    public void testRemoveModule() throws Exception {
        System.out.println("RemoveModule");
        
        try{
            impl.removeModule(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of getModuleById method, of class ModuleManagementImpl.
     */
    @Test
    public void testGetModuleById() throws Exception {
        System.out.println("GetModuleById");
        
        try{
            impl.getModuleById(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }

    /**
     * Test of getModulesSubject method, of class ModuleManagementImpl.
     */
    @Test
    public void testGetModulesSubject() throws Exception {
        System.out.println("GetModulesSubject");
        
        try{
            impl.getModulesSubject(null);
        }catch(BusinessException ex){
            assertTrue(ex.getMessage().equals("ID não pode ser nulo"));
            return;
        }
        fail("Aceitou Id nulo");
    }
    
}
