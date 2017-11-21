package nl.han.asd.toetsapp.common.connection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for creating connections to servers.
 */
public class ConnectionManager {
    private static ConnectionManager instance;
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    private ConnectionManager() {

    }

    public static ConnectionManager getInstance () {
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    /**
     * Create a HTTPS connection to a server, using the specified host, port and postfix.
     * @param host The host to connect to.
     * @param port The port to connect to.
     * @param postfix The postfix to add to the end of the URL.
     * @param trustAllCertificates Whether all certificates should be trusted. Only use this for testing.
     * @return The connection that was created.
     * @throws IOException If an IOException occurred while setting up the connection.
     */
    public URLConnection createServerConnection (String host, int port, String postfix, boolean trustAllCertificates) throws IOException {
        return createServerConnection("https://" + host + ":" + port + "/" + postfix, trustAllCertificates);
    }

    /**
     * Create a HTTPS connection to a server, using the specified URL.
     * @param url The URL to connect to.
     * @param trustAllCertificates Whether all certificates should be trusted. Only use this for testing.
     * @return The connection that was created.
     * @throws IOException If an IOException occurred while setting up the connection.
     */
    public URLConnection createServerConnection (String url, boolean trustAllCertificates) throws IOException {
        if (trustAllCertificates) {
            TrustManager[] trustAll = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAll, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (GeneralSecurityException e) {
                logger.log(Level.WARNING, "GeneralSecurityException when trying to initialize SSL context for trusting all certificates.", e);
            }
        }

        return new URL(url).openConnection();
    }
}
