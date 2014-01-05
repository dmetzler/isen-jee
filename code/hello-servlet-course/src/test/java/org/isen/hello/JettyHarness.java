package org.isen.hello;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JettyHarness {

    private static final int BASE_PORT = 9090;
    private static final int MAX_PORT_DEVIATION = 100;
    private static final String CONTEXT_PATH = "app";


    private HttpClient httpClient;
    private static Server server;

    //Le port est choisi au hasard. Si plusieurs test s'exécutent
    //en même temps sur la machine, cela permet d'éviter des collisions
    //de port.
    private static int port = BASE_PORT
            + (int) (Math.random() * MAX_PORT_DEVIATION);


    protected static final String HELLO_SERVLET_PATH = "/hello";
    protected static final String HELLO_SERVLET_URI = getBaseUri()
            + HELLO_SERVLET_PATH;


    @BeforeClass
    public static void startServer() throws Exception {
        server = new Server(port);  // <> On créé un serveur
        server.setStopAtShutdown(true);

        WebAppContext context = new WebAppContext(); //<> On crée un nouvelle application que l'on va configurer

        String wardir = "src/main/webapp";
        context.setResourceBase(wardir); // <> On spécifie le répertoire qui correspond au WAR
        context.setDescriptor(wardir + "WEB-INF/web.xml"); //<> On spécifie le descripteur de déploiement

        context.setContextPath("/" + CONTEXT_PATH); //<> On spécifie à quel chemin va se trouver notre application

        context.setParentLoaderPriority(true);

        context.addServlet(new ServletHolder(new HelloServlet()),
                HELLO_SERVLET_PATH); // <> On ajoute une servlet au déploiement

        server.setHandler(context);
        server.start();  // <> On démarre le serveur

    }

    public static String getBaseUri() {
        return "http://localhost:" + port + "/" + CONTEXT_PATH;
    }

    @AfterClass
    public static void stopServer() throws Exception {
        server.stop();

    }

    @Before
    public void doBefore() throws Exception {
        httpClient = new HttpClient();

    }

    @After
    public void doAfter() throws Exception {
        httpClient.getHttpConnectionManager().closeIdleConnections(0);
    }

    public String get(String uri) throws IOException {
        HttpMethod method = new GetMethod(uri);
        try {
            httpClient.executeMethod(method);
            byte[] responseBody = method.getResponseBody();
            return new String(responseBody);
        } finally {
            method.releaseConnection();
        }
    }

}
