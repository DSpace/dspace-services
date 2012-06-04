/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dspace.services.email;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.dspace.test.DSpaceAbstractKernelTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author mwood
 */
public class EmailServiceImplTest
        extends DSpaceAbstractKernelTest
{
    
    public EmailServiceImplTest()
    {
    }
    /*
     @BeforeClass
    public static void setUpClass()
            throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass()
            throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    */

    /**
     * Test of getSession method, of class EmailServiceImpl.
     */
    @Test
    public void testGetSession()
    { // TODO set up JNDI provider
        System.out.println("getSession");
        EmailServiceImpl instance = new EmailServiceImpl();
        Session result = instance.getSession();
        assertNotNull(" getSession returned null", result);
        // TODO figure out some more tests we could do
    }

    /**
     * Test of getPasswordAuthentication method, of class EmailServiceImpl.
     */
    @Test
    public void testGetPasswordAuthentication()
    { // TODO set up test credentials
        System.out.println("getPasswordAuthentication");
        EmailServiceImpl instance = new EmailServiceImpl();
        PasswordAuthentication result = instance.getPasswordAuthentication();
        assertNotNull(" null returned", result);
        assertNotNull(" null username", result.getUserName());
        assertFalse(" empty username", result.getUserName().isEmpty());
        assertNotNull(" null password", result.getPassword());
        assertFalse(" empty password", result.getPassword().isEmpty());
        System.out.printf(" Returned username '%s'", result.getUserName());
        System.out.printf(" Returned password '%s'", result.getPassword());
    }
}
