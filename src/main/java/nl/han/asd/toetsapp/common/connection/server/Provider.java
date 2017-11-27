package nl.han.asd.toetsapp.common.connection.server;

public interface Provider {

    /**
     * This method is used to check if a
     * Provider matches the URL scheme
     * @param url The scheme to match
     * @return A successful- or mis- match
     */
    default boolean isUrlSchemeMatching(String url) {
        return url.equalsIgnoreCase(this.getClass().getSimpleName());
    }


    /**
     * This needs to be implemented in a Provider to deliver a response message
     * @param requestType The type of request (for example GET, POST, PUT, DELETE)
     * @param parameters The parameters from the url
     * @param data The input from the POST or the PUT method
     * @return A response string (the content for the web page)
     */
    String request(String requestType, String[] parameters, String data);

}