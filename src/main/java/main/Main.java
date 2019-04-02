package main;

import accounts.AccountService;
import accounts.UserProfile;
import db.SchemaControl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import servlets.*;


public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Creating the server on port 8080
        Server server = new Server(8080);

        // 2. Creating the WebAppContext for the created content
        WebAppContext ctx = new WebAppContext();
        ctx.setContextPath("/");
        ctx.setWelcomeFiles(new String[] {"index.html"});
        ctx.setResourceBase("src/main/webapp/html");

        SchemaControl sc = new SchemaControl();
        ctx.addServlet(new ServletHolder(new PharmacyServlet(sc)),"/medicine");
        ctx.addServlet(new ServletHolder(new FeedbackServlet(sc)),"/feedback");
        //3. Including the JSTL jars for the webapp.
        ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");

        //4. Enabling the Annotation based configuration
        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

        server.setHandler(ctx);
        server.start();
        server.join();

    }
}
