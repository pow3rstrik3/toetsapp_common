package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class PluginInfo implements JsonModel {
    private static final String NAME = "name";
    private static final String VERSION = "version";
    private String name;
    private String version;

    /**
     * Empty constructor, required by some frameworks.
     * Manual use of this constructor is not recommended.
     */
    public PluginInfo() {
        this("untitled", "1.0");
    }

    public PluginInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    /**
     * Construct a PluginInfo from JSON.
     * @param jsonObject A JSONObject
     */
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

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean equals (PluginInfo other) {
        return getName().equals(other.getName()) && getVersion().equals(other.getVersion());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginInfo that = (PluginInfo) o;

        if (!getName().equals(that.getName())) return false;
        return getVersion().equals(that.getVersion());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getVersion().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
