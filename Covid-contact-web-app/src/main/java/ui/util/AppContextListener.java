package ui.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
<<<<<<< HEAD

=======
>>>>>>> origin/main
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String dbUrl = servletContextEvent.getServletContext().getInitParameter("dburl");
        String schema = servletContextEvent.getServletContext().getInitParameter("schema");
        DbConnectionService.connect(dbUrl, schema);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DbConnectionService.disconnect();
    }
}
