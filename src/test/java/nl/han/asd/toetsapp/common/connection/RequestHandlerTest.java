package nl.han.asd.toetsapp.common.connection;

import org.junit.Test;

public class RequestHandlerTest {
    @Test
    public void get() throws Exception {
        RequestHandler requestHandler = new RequestHandler("httpbin.org", 443, true);
        System.out.println(requestHandler.get("get"));
    }

    @Test
    public void post() throws Exception {
        RequestHandler requestHandler = new RequestHandler("httpbin.org", 443, true);
        System.out.println(requestHandler.post("post", "abc"));
    }

    @Test
    public void put() throws Exception {
        RequestHandler requestHandler = new RequestHandler("httpbin.org", 443, true);
        System.out.println(requestHandler.put("put", "abc"));
    }

    @Test
    public void delete() throws Exception {
        RequestHandler requestHandler = new RequestHandler("httpbin.org", 443, true);
        System.out.println(requestHandler.delete("delete"));
    }

}