package nl.han.asd.toetsapp.common.connection;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Simple HTTPS server that runs a server for a given HttpHandler.
 */
public class SimpleHttpsServer {
    private static final String KEYSTORE_TYPE = "JKS";
    private static final String KEYSTORE_ALGORITHM = "SunX509";
    private static final String SSL_PROTOCOL = "TLS";
    private final int port;
    private final HttpHandler httpHandler;

    public SimpleHttpsServer(int port, HttpHandler handler) {
        this.port = port;
        this.httpHandler = handler;
    }

    /**
     * Start the server.
     * @param keyStoreFile The file that contains the key store to be used for this connection.
     * @param keyStorePassword The password for the given key store.
     * @throws IOException If an IOException occurred while starting the server.
     * @throws GeneralSecurityException If a GeneralSecurityException occurred while initialising the key store.
     */
    public void startServer(String keyStoreFile, String keyStorePassword) throws IOException, GeneralSecurityException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        HttpsServer httpsServer = HttpsServer.create(socketAddress, 0);
        SSLContext sslContext = SSLContext.getInstance(SSL_PROTOCOL);
        initialiseKeyStore(keyStoreFile, keyStorePassword, sslContext);
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        httpsServer.createContext("/", httpHandler);
        httpsServer.setExecutor(null);
        httpsServer.start();
    }

    private void initialiseKeyStore(String keystoreFile, String password, SSLContext sslContext) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
        FileInputStream fileInputStream = new FileInputStream(keystoreFile);
        char[] passwordChars = password.toCharArray();
        keyStore.load(fileInputStream, passwordChars);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KEYSTORE_ALGORITHM);
        kmf.init(keyStore, passwordChars);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(KEYSTORE_ALGORITHM);
        tmf.init(keyStore);

        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
    }

}