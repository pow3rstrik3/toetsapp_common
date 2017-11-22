package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class PluginInfo implements JsonModel {

    private String name;
    private String version;

    public PluginInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }


    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("version", this.version);

        return jsonObject;
    }


    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
