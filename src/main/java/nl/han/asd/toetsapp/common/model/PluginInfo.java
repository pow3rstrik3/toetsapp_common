package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class PluginInfo implements JsonModel {
    private static final String NAME = "name";
    private static final String VERSION = "version";
    private String name;
    private String version;

    public PluginInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public PluginInfo(JSONObject jsonObject) {
        this(jsonObject.getString(NAME),
            jsonObject.getString(VERSION));
    }

    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NAME, this.name);
        jsonObject.put(VERSION, this.version);

        return jsonObject;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public boolean equals (PluginInfo other) {
        return getName().equals(other.getName()) && getVersion().equals(other.getVersion());
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
