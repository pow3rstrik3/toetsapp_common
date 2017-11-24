package nl.han.asd.toetsapp.common.connection;

import org.junit.Test;

public class HttpsServerTest {
    @Test
    public void startServer() throws Exception {

        HttpsServer testServer = new HttpsServer();
        testServer.startServer(8000, "keystore.jks", "password");

        testServer.addProvider(new TestProvider());

        while (true){

        }
    }

    @Test
    public void handle() throws Exception {
    }

    @Test
    public void addProvider() throws Exception {
    }

    @Test
    public void removeProvider() throws Exception {
    }

}