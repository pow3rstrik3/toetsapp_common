package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class Answer implements JsonModel {
    //TODO: Rename
    private static final String PLUGIN_ANSWER = "pluginAnswer";
    private String pluginAnswer;

    public Answer(String pluginAnswer) {
        this.pluginAnswer = pluginAnswer;
    }

    public Answer(JSONObject jsonObject) {
        this(jsonObject.getString(PLUGIN_ANSWER));
    }

    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(PLUGIN_ANSWER, this.pluginAnswer);

        return jsonObject;
    }

    public String getPluginAnswer() {
        return pluginAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "pluginAnswer='" + pluginAnswer + '\'' +
                '}';
    }

}
