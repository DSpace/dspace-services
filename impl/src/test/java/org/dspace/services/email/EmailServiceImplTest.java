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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.dspace.services.ConfigurationService;
import org.dspace.test.DSpaceAbstractKernelTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author mwood
 */
public class EmailServiceImplTest
        extends DSpaceAbstractKernelTest
{
    private static final String USERNAME = "auser";
    private static final String PASSWORD = "apassword";
    
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
            throws MessagingException
    {
        System.out.println("getSession");
        Session session;
        EmailServiceImpl instance = new EmailServiceImpl();

        // Try to get a Session
        session = instance.getSession();
        assertNotNull(" getSession returned null", session);
    }

    private static final String CFG_USERNAME = "mail.server.username";
    private static final String CFG_PASSWORD = "mail.server.password";

    /**
     * Test of getPasswordAuthentication method, of class EmailServiceImpl.
     */
    @Test
    public void testGetPasswordAuthentication()
    {
        System.out.println("getPasswordAuthentication");
        ConfigurationService cfg = getKernel().getConfigurationService();

        // Save existing values.
        String oldUsername = cfg.getProperty(CFG_USERNAME);
        String oldPassword = cfg.getProperty(CFG_PASSWORD);

        // Set known values.
        cfg.setProperty(CFG_USERNAME, USERNAME);
        cfg.setProperty(CFG_PASSWORD, PASSWORD);

        EmailServiceImpl instance = new EmailServiceImpl();

        PasswordAuthentication result = instance.getPasswordAuthentication();
        assertNotNull(" null returned", result);
        assertEquals(" username does not match configuration", result.getUserName(), USERNAME);
        assertEquals(" password does not match configuration", result.getPassword(), PASSWORD);

        // Restore old values, if any.
        cfg.setProperty(CFG_USERNAME, oldUsername);
        cfg.setProperty(CFG_PASSWORD, oldPassword);
    }
}
