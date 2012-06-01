/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.services.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.dspace.kernel.mixins.InitializedService;
import org.dspace.services.ConfigurationService;
import org.dspace.services.EmailService;
import org.dspace.utils.DSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides mail sending services through JavaMail.  If a {@link javax.mail.Session}
 * instance is provided through JNDI, it will be used.  If not, then a session
 * will be created from DSpace configuration data ({@code mail.server} etc.)
 *
 * @author mwood
 */
public class EmailServiceImpl
        extends Authenticator
        implements InitializedService, EmailService
{
    private Session session = null;

    private static final ConfigurationService cfg = new DSpace().getConfigurationService();

    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);

    /* Provide a reference to the JavaMail session. */
    @Override
    public Session getSession() { return session; }

    @Override
    public void init()
    {
        // First, see if there is a Session in our environment
        try
        {
            Context ctx = new InitialContext(null);
            session = (Session) ctx.lookup("java:comp/env/mail/Session"); // TODO configurable?
        } catch (NamingException ex)
        {
            logger.error("Couldn't get an email session:  ", ex);
            return;
        }

        if (null != session)
            logger.info("Email session retrieved from environment.");
        else
        { // No Session provided, so create one
            logger.info("Initializing an email session from configuration.");
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.host", cfg.getProperty("mail.server"));
            String port = cfg.getProperty("mail.server.port");
            if (null != port)
                props.put("mail.smtp.port", port);

            if (null == cfg.getProperty("mail.server.username"))
                session = Session.getInstance(props);
            else
                session = Session.getInstance(null, this);

            // Set extra configuration properties
            String extras = cfg.getProperty("mail.extraproperties");
            if ((extras != null) && (!"".equals(extras.trim())))
            {
                String arguments[] = extras.split(",");
                String key, value;
                for (String argument : arguments)
                {
                    key = argument.substring(0, argument.indexOf('=')).trim();
                    value = argument.substring(argument.indexOf('=') + 1).trim();
                    props.put(key, value);
                }
            }
        }
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(
                cfg.getProperty("mail.server.username"),
                cfg.getProperty("mail.server.password"));
    }
}
