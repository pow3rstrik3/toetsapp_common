package nl.han.asd.toetsapp.common.connection;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler {

    private static final Logger logger = Logger.getLogger(RequestHandler.class.getName());

    private String host;
    private int port;
    private boolean trustAllCertificates = false;


    /**
     * This is used to initialize the server to send requests to
     * @param host The host (For example nu.nl)
     * @param port The port (For 443)
     * @param trustAllCertificates Trust self-signed certificates (Set this for debugging)
     */
    public RequestHandler(String host, int port, boolean trustAllCertificates) {
        this.host = host;
        this.port = port;
        this.trustAllCertificates = trustAllCertificates;
    }


    /**
     * A GET request to a given path
     * @param path The path (For example /about)
     * @return A response from the server
     */
    public String get(String path) {
        return this.request(path, "GET", null);
    }


    /**
     * A POST request to a given path
     * @param path The path (For example /about)
     * @param data The data to send to the server
     * @return A response from the server
     */
    public String post(String path, String data) {
        return this.request(path, "POST", data);
    }


    /**
     * A PUT request to a given path
     * @param path The path (For example /about)
     * @param data The data to send to the server
     * @return A response from the server
     */
    public String put(String path, String data) {
        return this.request(path, "PUT", data);
    }


    /**
     * A DELETE request to a given path
     * @param path The path (For example /about)
     * @return A response from the server
     */
    public String delete(String path) {
        return this.request(path, "DELETE", null);
    }


    /**
     * A general method for handling requests to the server
     * @param path The path (For example /about)
     * @param method The method header to send (For example GET, POST, PUT, DELETE)
     * @param data The data to send to the server (nullable)
     * @return The response from the server
     */
    private String request(String path, String method, String data) {
        String response = "";
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(getUrl(path)).openConnection();

            if (trustAllCertificates && getSslUnsignedCertifcateManager() != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(getSslUnsignedCertifcateManager());
            }

            if (data != null) {
                connection.setDoOutput(true);
            }

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(method);
            connection.connect();

            if (data != null) {
                OutputStream out = connection.getOutputStream();
                out.write(data.getBytes("UTF-8"));
                out.flush();
                out.close();
            }

            InputStream input = new BufferedInputStream(connection.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
            StringBuilder builder = new StringBuilder();
            String temp = "";
            while ((temp = buffer.readLine()) != null) {
                builder.append(temp);
            }
            response = builder.toString();

            connection.disconnect();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Connection error for request: " + getUrl(path), e);
        }

        return response;
    }


    /**
     * This will setup the structure for a URL
     * @param path The path to add at the end of the url
     * @return A URL string
     */
    private String getUrl(String path) {
        String checkedPath = "";
        if (path != null) {
            checkedPath = path;
        }

        return "https://" + host + ":" + port + "/" + checkedPath;
    }


    /**
     * This will generate an sslContext so all certificates will be trusted
     * @return A SSLSocketFactory
     */
    private SSLSocketFactory getSslUnsignedCertifcateManager() {
        SSLContext sslContext = null;
        try {
            TrustManager[] trustManagers = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // default implementation ignored
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // default implementation ignored
                    }
                }
            };

            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARNING, "RequestHandler NoSuchAlgorithmException", e);
        } catch (KeyManagementException e) {
            logger.log(Level.WARNING, "RequestHandler KeyManagementException", e);
        }

        return sslContext != null ? sslContext.getSocketFactory() : null;
    }

}
