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

    /**
     * Empty constructor, required by some frameworks.
     * Manual use of this constructor is not recommended.
     */
    public Question() {
        this(0, "untitled", "", "none", "");
    }

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

    /**
     * Construct a Question from JSON.
     * @param jsonObject A JSONObject
     */
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (getId() != question.getId()) return false;
        if (!getTitle().equals(question.getTitle())) return false;
        if (!getContext().equals(question.getContext())) return false;
        if (!getDefinition().equals(question.getDefinition())) return false;
        if (!getPlugin().equals(question.getPlugin())) return false;
        return getSubjects().equals(question.getSubjects());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContext().hashCode();
        result = 31 * result + getDefinition().hashCode();
        result = 31 * result + getPlugin().hashCode();
        result = 31 * result + getSubjects().hashCode();
        return result;
    }
}
