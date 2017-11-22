package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class Answer implements JsonModel {

    private String pluginAnswer;

    public Answer(String pluginAnswer) {
        this.pluginAnswer = pluginAnswer;
    }


    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pluginAnswer", this.pluginAnswer);

        return jsonObject;
    }


    @Override
    public String toString() {
        return "Answer{" +
                "pluginAnswer='" + pluginAnswer + '\'' +
                '}';
    }

}
