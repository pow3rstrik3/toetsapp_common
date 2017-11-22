package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ExamInfo implements JsonModel {

    private String id;
    private String version;
    private String title;
    private String subject;
    private boolean isMock;
    private List<PluginInfo> requiredPlugins;

    public ExamInfo(String id, String version, String title, String subject, boolean isMock) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.subject = subject;
        this.isMock = isMock;
    }


    /**
     * A getter for the list of required plugins
     * @return A list of PluginInfo objects
     */
    public List<PluginInfo> getRequiredPlugins() {
        return requiredPlugins;
    }


    /**
     * This method will add a required plugin to the examInfo
     * @param requiredPlugin The plugin to add to the list of required plugins
     */
    public boolean addPlugin(PluginInfo requiredPlugin) {
        return this.requiredPlugins.add(requiredPlugin);
    }


    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("title", this.title);
        jsonObject.put("version", this.version);
        jsonObject.put("subject", this.subject);
        jsonObject.put("isMock", this.isMock);

        JSONArray requiredPluginsJsonArray = new JSONArray();
        for (PluginInfo pluginInfo : requiredPlugins) {
            requiredPluginsJsonArray.put(pluginInfo.getJSONObject());
        }
        jsonObject.put("requiredPlugins", requiredPluginsJsonArray);

        return jsonObject;
    }


    @Override
    public String toString() {
        return "ExamInfo{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", isMock=" + isMock +
                ", requiredPlugins=" + requiredPlugins +
                '}';
    }

}
