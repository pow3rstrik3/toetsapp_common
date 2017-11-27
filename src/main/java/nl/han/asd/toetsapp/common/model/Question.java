package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Question implements JsonModel {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTEXT = "context";
    private static final String ASKED_QUESTION = "askedQuestion";
    private static final String PLUGIN = "plugin";
    private static final String SUBJECTS = "subjects";
    private int id;
    private String title;
    private String context;
    private String askedQuestion;
    private PluginInfo plugin;
    private List<String> subjects;

    /**
     * Empty constructor, required by some frameworks.
     * Manual use of this constructor is not recommended.
     */
    public Question() {
        this(0, "untitled", "", "none", new PluginInfo("no-plugin", "1.0"));
    }

    private Question(int id, String title, String context, String askedQuestion, PluginInfo plugin) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.askedQuestion = askedQuestion;
        this.plugin = plugin;
        this.subjects = new ArrayList<>();
    }

    public Question(int id, String title, String context, String askedQuestion, PluginInfo plugin, String subject) {
        this(id, title, context, askedQuestion, plugin);
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
            jsonObject.getString(ASKED_QUESTION),
            new PluginInfo(jsonObject.getJSONObject(PLUGIN)));
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

    public String getAskedQuestion() {
        return askedQuestion;
    }

    public PluginInfo getPlugin() {
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

    public void setAskedQuestion(String askedQuestion) {
        this.askedQuestion = askedQuestion;
    }

    public void setPlugin(PluginInfo plugin) {
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
        jsonObject.put(ASKED_QUESTION, this.askedQuestion);
        jsonObject.put(PLUGIN, this.plugin.getJSONObject());

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
        if (!getAskedQuestion().equals(question.getAskedQuestion())) return false;
        if (!getPlugin().equals(question.getPlugin())) return false;
        return getSubjects().equals(question.getSubjects());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContext().hashCode();
        result = 31 * result + getAskedQuestion().hashCode();
        result = 31 * result + getPlugin().hashCode();
        result = 31 * result + getSubjects().hashCode();
        return result;
    }
}
