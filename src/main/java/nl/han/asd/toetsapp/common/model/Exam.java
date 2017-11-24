package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exam implements JsonModel {
    //TODO Misschien iets om requiredPlugins te vullen.
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String VERSION = "version";
    private static final String EXAM_TYPE = "examType";
    private static final String REQUIRED_PLUGINS = "requiredPlugins";
    private static final String QUESTIONS = "questions";
    private static final String INFO_ONLY = "infoOnly";
    private int id;
    private String version;
    private String title;
    private ExamType examType;
    //private long examDate;
    //private int duration;
    //String description
    //String comment
    private List<PluginInfo> requiredPlugins;
    private List<Question> questions;

    /**
     * Construct an Exam with the given fields.
     * @param id The ID of the exam.
     * @param version The version of the exam.
     * @param title The title of the exam.
     * @param examType The ExamType of the exam.
     * @param infoOnly Whether this exam is info-only. Info-only exams can not contain questions.
     */
    public Exam(int id, String version, String title, ExamType examType, boolean infoOnly) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.examType = examType;
        this.requiredPlugins = new ArrayList<>();
        if (!infoOnly)
            this.questions = new ArrayList<>();
    }

    /**
     * Construct an Exam from a JSONObject.
     * @param jsonObject The JSONObject to obtain the fields from.
     */
    public Exam(JSONObject jsonObject) {
        this(jsonObject.getInt(ID),
                jsonObject.getString(VERSION),
                jsonObject.getString(TITLE),
                ExamType.valueOf(jsonObject.getString(EXAM_TYPE)),
                jsonObject.getBoolean(INFO_ONLY));
        JSONArray pluginInfos = jsonObject.getJSONArray(REQUIRED_PLUGINS);
        for (int i = 0; i < pluginInfos.length(); i++) {
            requiredPlugins.add(new PluginInfo(pluginInfos.getJSONObject(i)));
        }
        if (!isInfoOnly()) {
            JSONArray jsonQuestions = jsonObject.getJSONArray(QUESTIONS);
            for (int i = 0; i < jsonQuestions.length(); i++) {
                questions.add(new Question(jsonQuestions.getJSONObject(i)));
            }
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
     * @return True if this exam is info-only and can't contain questions.
     */
    public boolean isInfoOnly() {
        return questions == null;
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
     * A getter for the questions added to the exam.
     * @return A list of questions. Returns null if this exam is info-only.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * A getter for the questions added to the exam, converted to an array.
     * @return An array of questions. Returns null if this exam is info-only.
     */
    public Question[] getQuestionsArray() {
        return questions != null ? questions.toArray(new Question[0]) : null;
    }

    /**
     * This method will add a question to the exam, assuming this is not an info-only exam.
     * @param question The question to add
     * @return True if the question was added, false if the exam is info-only.
     */
    public boolean addQuestion(Question question) {
        return !isInfoOnly() && this.questions.add(question);
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
        jsonObject.put(INFO_ONLY, isInfoOnly());

        JSONArray requiredPluginsJsonArray = new JSONArray();
        for (PluginInfo pluginInfo : requiredPlugins) {
            requiredPluginsJsonArray.put(pluginInfo.getJSONObject());
        }
        jsonObject.put(REQUIRED_PLUGINS, requiredPluginsJsonArray);

        if (!isInfoOnly()) {
            JSONArray questionsJsonArray = new JSONArray();
            for (Question question : questions) {
                questionsJsonArray.put(question.getJSONObject());
            }
            jsonObject.put(QUESTIONS, questionsJsonArray);
        }

        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (getId() != exam.getId()) return false;
        if (!getVersion().equals(exam.getVersion())) return false;
        if (!getTitle().equals(exam.getTitle())) return false;
        if (getExamType() != exam.getExamType()) return false;
        if (!getRequiredPlugins().equals(exam.getRequiredPlugins())) return false;
        if (getQuestions() == null)
            return exam.getQuestions() == null;
        else
            return getQuestions().equals(exam.getQuestions());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getVersion().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getExamType().hashCode();
        result = 31 * result + getRequiredPlugins().hashCode();
        if (getQuestions() != null)
            result = 31 * result + getQuestions().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", examType=" + examType +
                ", requiredPlugins=" + requiredPlugins +
                ", questions=" + questions +
                '}';
    }
}
