package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Question implements JsonModel{
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTEXT = "context";
    private static final String DEFINITION = "definition";
    private static final String PLUGIN = "plugin";
    private static final String SUBJECTS = "subjects";
    private int id;
    private String title;
    private String context;
    private String definition;
    private String plugin;
    private List<String> subjects;

    private Question(int id, String title, String context, String definition, String plugin) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.definition = definition;
        this.plugin = plugin;
        this.subjects = new ArrayList<>();
    }

    public Question(int id, String title, String context, String definition, String plugin, String subject) {
        this(id, title, context, definition, plugin);
        addSubject(subject);
    }

    //TODO: Exception if no subject
    public Question(JSONObject jsonObject) {
        this(jsonObject.getInt(ID),
            jsonObject.getString(TITLE),
            jsonObject.getString(CONTEXT),
            jsonObject.getString(DEFINITION),
            jsonObject.getString(PLUGIN));
        JSONArray subjectArray = jsonObject.getJSONArray(SUBJECTS);
        for (int i = 0; i < subjectArray.length(); i++) {
            subjects.add(subjectArray.getString(i));
        }
    }

    public String getContext() {
        return context;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPlugin() {
        return plugin;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void addSubject (String subject) {
        subjects.add(subject);
    }

    public void removeSubject (String subject) {
        subjects.remove(subject);
    }

    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID, this.id);
        jsonObject.put(TITLE, this.title);
        jsonObject.put(CONTEXT, this.context);
        jsonObject.put(DEFINITION, this.definition);
        jsonObject.put(PLUGIN, this.plugin);

        JSONArray subjectsArray = new JSONArray();
        for (String subject : subjects) {
            subjectsArray.put(subject);
        }
        jsonObject.put(SUBJECTS, subjectsArray);

        return jsonObject;
    }

    public boolean equals (Question other) {
        return getId() == other.getId() &&
                getTitle().equals(other.getTitle()) &&
                getContext().equals(other.getContext()) &&
                getDefinition().equals(other.getDefinition()) &&
                getPlugin().equals(other.getPlugin());
    }



}
