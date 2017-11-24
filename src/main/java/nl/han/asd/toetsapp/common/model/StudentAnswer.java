package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class StudentAnswer implements JsonModel {
    private static final String PLUGIN_ANSWER = "pluginAnswer";
    private String pluginAnswer;

    /**
     * Empty constructor, required by some frameworks.
     * Manual use of this constructor is not recommended.
     */
    public StudentAnswer() {
        this("");
    }

    public StudentAnswer(String pluginAnswer) {
        this.pluginAnswer = pluginAnswer;
    }

    /**
     * Construct an AnswerKey from JSON.
     * @param jsonObject A JSONObject
     */
    public StudentAnswer(JSONObject jsonObject) {
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

    public void setPluginAnswer(String pluginAnswer) {
        this.pluginAnswer = pluginAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAnswer that = (StudentAnswer) o;

        return getPluginAnswer().equals(that.getPluginAnswer());
    }

    @Override
    public int hashCode() {
        return getPluginAnswer().hashCode();
    }

    @Override
    public String toString() {
        return "AnswerKey{" +
                "pluginAnswer='" + pluginAnswer + '\'' +
                '}';
    }

}
