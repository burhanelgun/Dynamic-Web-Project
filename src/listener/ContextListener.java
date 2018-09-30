package listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import dao.ServiceFacade;

public class ContextListener implements ServletContextListener {
	static Properties prop = new Properties();

	final static Logger logger = Logger.getLogger(ContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties prop = new Properties();
		InputStream input = null;
		ServletContext context = arg0.getServletContext();
		try {
			input = new FileInputStream(context.getRealPath("/WEB-INF/classes/database.properties"));
			prop.load(input);

			if (logger.isDebugEnabled()) {
				logger.debug("This is debug : ");
			}

			if (logger.isInfoEnabled()) {
				logger.info("This is info : ");
			}

			logger.warn("This is warn : ");
			logger.error("This is error : ");
			logger.fatal("This is fatal : ");

			ServiceFacade.getInstance().start(prop);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ServletContextListener started");
	}
}
