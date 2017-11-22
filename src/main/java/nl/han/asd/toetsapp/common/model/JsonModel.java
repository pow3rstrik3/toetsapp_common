package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public interface JsonModel {

    /**
     * This will require a class to publish a JSONObject
     * @return A JSON object which represents the model
     */
    JSONObject getJSONObject();

}
