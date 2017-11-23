package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExamInfo implements JsonModel {

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String VERSION = "version";
    private static final String EXAM_TYPE = "examType";
    private static final String REQUIRED_PLUGINS = "requiredPlugins";
    private int id;
    private String version;
    private String title;
    private ExamType examType;
    private List<PluginInfo> requiredPlugins;

    public ExamInfo(int id, String version, String title, ExamType examType) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.examType = examType;
        this.requiredPlugins = new ArrayList<>();
    }

    public ExamInfo(JSONObject jsonObject) {
        this(jsonObject.getInt(ID),
            jsonObject.getString(VERSION),
            jsonObject.getString(TITLE),
            ExamType.valueOf(jsonObject.getString(EXAM_TYPE)));
        JSONArray pluginInfos = jsonObject.getJSONArray(REQUIRED_PLUGINS);
        for (int i = 0; i < pluginInfos.length(); i++) {
            requiredPlugins.add(new PluginInfo(pluginInfos.getJSONObject(i)));
        }
    }

    public int getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public ExamType getExamType() {
        return examType;
    }

    public boolean isMock() {
        return examType.isMock();
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
        jsonObject.put(ID, this.id);
        jsonObject.put(TITLE, this.title);
        jsonObject.put(VERSION, this.version);
        jsonObject.put(EXAM_TYPE, this.examType.toString());

        JSONArray requiredPluginsJsonArray = new JSONArray();
        for (PluginInfo pluginInfo : requiredPlugins) {
            requiredPluginsJsonArray.put(pluginInfo.getJSONObject());
        }
        jsonObject.put(REQUIRED_PLUGINS, requiredPluginsJsonArray);

        return jsonObject;
    }

    public boolean equals (ExamInfo other) {
        if (!(getId() == other.getId() &&
                getTitle().equals(other.getTitle()) &&
                getVersion().equals(other.getVersion()) &&
                isMock() == other.isMock() &&
                getRequiredPlugins().size() == other.getRequiredPlugins().size()))
            return false;
        for (int i = 0; i < other.getRequiredPlugins().size(); i++) {
            if (!other.getRequiredPlugins().get(i).equals(other.getRequiredPlugins().get(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", examType=" + examType +
                ", requiredPlugins=" + requiredPlugins +
                '}';
    }

}
