package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exam implements JsonModel {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String VERSION = "version";
    private static final String EXAM_TYPE = "examType";
    private static final String REQUIRED_PLUGINS = "requiredPlugins";
    private static final String QUESTIONS = "questions";
    private static final String EXAM_DATE = "examDate";
    private static final String DURATION = "duration";
    private static final String DESCRIPTION = "description";
    private static final String COMMENT = "comment";
    private static final String INFO_ONLY = "infoOnly";
    private int id;
    private String version;
    private String title;
    private ExamType examType;
    private long examDate;
    private int duration;
    private String description;
    private String comment;
    private List<PluginInfo> requiredPlugins;
    private List<Question> questions;

    /**
     * Empty constructor, required by some frameworks.
     * Manual use of this constructor is not recommended.
     */
    public Exam() {
        this(0, "1.0", "untitled", ExamType.EXAM, 0, 0, "", "", false);
    }

    /**
     * Construct an Exam with the given fields.
     * @param id The ID of the exam.
     * @param version The version of the exam.
     * @param title The title of the exam.
     * @param examType The ExamType of the exam.
     * @param infoOnly Whether this exam is info-only. Info-only exams can not contain questions.
     * @param examDate The timestamp of the start of the exam.
     * @param duration The duration of the exam in seconds.
     * @param description The description of the exam.
     * @param comment The teacher's comment on the exam.
     */
    public Exam(int id, String version, String title, ExamType examType, long examDate, int duration, String description, String comment, boolean infoOnly) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.examType = examType;
        this.examDate = examDate;
        this.duration = duration;
        this.description = description;
        this.comment = comment;
        this.requiredPlugins = new ArrayList<>();
        setInfoOnly(infoOnly);
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
                jsonObject.getLong(EXAM_DATE),
                jsonObject.getInt(DURATION),
                jsonObject.getString(DESCRIPTION),
                jsonObject.getString(COMMENT),
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

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public void setExamDate(long examDate) {
        this.examDate = examDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRequiredPlugins(List<PluginInfo> requiredPlugins) {
        this.requiredPlugins = requiredPlugins;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Set whether this exam is info-only.
     * If the exam is set to info-only, any questions will be deleted.
     * @param infoOnly Whether this exam should be info-only.
     */
    public void setInfoOnly(boolean infoOnly) {
        if (infoOnly)
            questions = null;
        else if (questions == null)
            questions = new ArrayList<>();
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

    public long getExamDate() {
        return examDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
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
        jsonObject.put(ID, id);
        jsonObject.put(TITLE, title);
        jsonObject.put(VERSION, version);
        jsonObject.put(EXAM_TYPE, examType.toString());
        jsonObject.put(EXAM_DATE, examDate);
        jsonObject.put(DURATION, duration);
        jsonObject.put(DESCRIPTION, description);
        jsonObject.put(COMMENT, comment);
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
        if (getExamDate() != exam.getExamDate()) return false;
        if (getDuration() != exam.getDuration()) return false;
        if (!getVersion().equals(exam.getVersion())) return false;
        if (!getTitle().equals(exam.getTitle())) return false;
        if (getExamType() != exam.getExamType()) return false;
        if (!getDescription().equals(exam.getDescription())) return false;
        if (!getComment().equals(exam.getComment())) return false;
        if (!getRequiredPlugins().equals(exam.getRequiredPlugins())) return false;
        return getQuestions() != null ? getQuestions().equals(exam.getQuestions()) : exam.getQuestions() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getVersion().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getExamType().hashCode();
        result = 31 * result + (int) (getExamDate() ^ (getExamDate() >>> 32));
        result = 31 * result + getDuration();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getComment().hashCode();
        result = 31 * result + getRequiredPlugins().hashCode();
        result = 31 * result + (getQuestions() != null ? getQuestions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", examType=" + examType +
                ", examDate=" + examDate +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", requiredPlugins=" + requiredPlugins +
                ", questions=" + questions +
                '}';
    }
}
