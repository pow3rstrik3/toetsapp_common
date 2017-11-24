package nl.han.asd.toetsapp.common.connection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpsServer implements HttpHandler {

    private List<Provider> providers;
    private static final Logger logger = Logger.getLogger(HttpsServer.class.getName());

    /**
     * This class is used to serve
     * Providers on a HTTPS server
     */
    public HttpsServer() {
        providers = new ArrayList<>();
    }


    /**
     * Call this method to start serving web content
     * @param port The port to serve on (For example 8000)
     * @param keyStoreFile The path to the keystore file starting in the working directory
     * @param keyStorePassword The password for the given key store
     */
    public void startServer(int port, String keyStoreFile, String keyStorePassword) {
        com.sun.net.httpserver.HttpsServer server = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            initialiseKeyStore(keyStoreFile, keyStorePassword, sslContext);

            server = com.sun.net.httpserver.HttpsServer.create(new InetSocketAddress(port), 0);
            server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
            server.createContext("/", this);
            server.setExecutor(null);
            server.start();

            logger.log(Level.INFO, "HTTPS Server Started");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Start server IOException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Start server NoSuchAlgorithmException", e);
        }
    }


    /**
     * This method is to initialize the given keystore
     * @param keystoreFile The path to the keystore file starting in the working directory
     * @param password The password for the given key store
     * @param sslContext The SSL context to add the keystore to
     */
    private void initialiseKeyStore(String keystoreFile, String password, SSLContext sslContext) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream fileInputStream = new FileInputStream(keystoreFile);
            keyStore.load(fileInputStream, password.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, password.toCharArray());
            tmf.init(keyStore);

            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        } catch (KeyStoreException e) {
            logger.log(Level.SEVERE, "KeyStoreException", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException", e);
        } catch (CertificateException e) {
            logger.log(Level.SEVERE, "CertificateException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "NoSuchAlgorithmException", e);
        } catch (UnrecoverableKeyException e) {
            logger.log(Level.SEVERE, "UnrecoverableKeyException", e);
        } catch (KeyManagementException e) {
            logger.log(Level.SEVERE, "KeyManagementException", e);
        }
    }


    /**
     * This method is called by the HttpServer to handle the requests
     * @param httpExchange The exchange
     * @throws IOException Exception for IO
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        byte[] response = onRequest(httpExchange).getBytes();

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response);
        outputStream.close();
    }


    /**
     * This will prepare the response for the HttpServer
     * @param httpExchange The HttpExchange from the HttpServer
     * @return A response string (the content for the web page)
     */
    private String onRequest(HttpExchange httpExchange) throws IOException {
        // Remove the first slash if there is one
        String path = httpExchange.getRequestURI().getPath();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        // Split the url for parameters used by the Providers
        List<String> splittedUrl = new ArrayList<>();
        Collections.addAll(splittedUrl, path.split("/"));

        return getResponseForProvider(
                httpExchange,
                splittedUrl
        );
    }


    /**
     * This will get the the content from the correct provider
     * @param httpExchange The HttpExchange from the HttpServer
     * @param splittedUrl The splitted parts from the url
     * @return A response string (the content for the web page)
     * @throws IOException
     */
    private String getResponseForProvider(HttpExchange httpExchange, List<String> splittedUrl) throws IOException {
        // Loop trough all the provider names to
        // get a matching class for the URL
        for (Provider p : providers) {
            if (p.isUrlSchemeMatching(splittedUrl.get(0))) {

                // Remove the first part of the url
                // because it is not a parameter
                splittedUrl.remove(0);

                try {
                    // Return a successful (code 200) request if
                    // there is a response from the provider
                    String successResponse = p.request(
                            httpExchange.getRequestMethod(),
                            splittedUrl.toArray(new String[0])
                    );

                    if (successResponse != null) {
                        httpExchange.sendResponseHeaders(200, successResponse.length());
                        return successResponse;
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Unhandled server exception", e);

                    httpExchange.sendResponseHeaders(500, serverError().length());
                    return serverError();
                }
            }
        }

        // When there isn't a provider available
        httpExchange.sendResponseHeaders(404, notFoundError().length());
        return notFoundError();
    }


    /**
     * This will add a provider to the known providers
     * @param provider The provider to add
     * @return Success
     */
    public boolean addProvider(Provider provider) {
        return providers.add(provider);
    }


    /**
     * This will remove a provider from the known providers
     * @param provider The provider to remove
     * @return Success
     */
    public boolean removeProvider(Provider provider) {
        return providers.remove(provider);
    }


    /**
     * This content will be shown when a provider is not found
     * @return A response string (the content for the web page)
     */
    private String notFoundError() {
        return "<div>\n" +
                "   <h1>Page Not Found (404)</h1>\n" +
                "</div>";
    }


    /**
     * This content will be shown when a provider has an internal error
     * @return A response string (the content for the web page)
     */
    private String serverError() {
        return "<div>\n" +
                "   <h1>Internal Server Error (500)</h1>\n" +
                "</div>";
    }

}