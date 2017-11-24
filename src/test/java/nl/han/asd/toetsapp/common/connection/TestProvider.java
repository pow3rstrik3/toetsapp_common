package nl.han.asd.toetsapp.common.connection;

public class TestProvider implements Provider {

    /**
     * This is an example
     * @param requestType The type of request (for example GET, POST, PUT, DELETE)
     * @param parameters The parameters from the url
     * @param data
     * @return Example
     */
    @Override
    public String request(String requestType, String[] parameters, String data) {
        return "This is a " + requestType + " request on the '"  +
                this.getClass().getSimpleName() + "' provider with data: '" + data + "'";
    }

}
