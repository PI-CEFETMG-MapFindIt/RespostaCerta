/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
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
public class PerformanceManagementImplTest {
    
    public PerformanceManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateErrors method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrors() throws Exception {
        System.out.println("calculateErrors");
        User user = null;
        PerformanceManagementImpl instance = null;
        Double expResult = null;
        Double result = instance.calculateErrors(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateErrorsByModule method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrorsByModule() throws Exception {
        System.out.println("calculateErrorsByModule");
        User user = null;
        Module modulo = null;
        PerformanceManagementImpl instance = null;
        Double expResult = null;
        Double result = instance.calculateErrorsByModule(user, modulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateErrosBySubject method, of class PerformanceManagementImpl.
     */
    @Test
    public void testCalculateErrosBySubject() throws Exception {
        System.out.println("calculateErrosBySubject");
        User user = null;
        Subject disciplina = null;
        PerformanceManagementImpl instance = null;
        Double expResult = null;
        Double result = instance.calculateErrosBySubject(user, disciplina);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
