package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course implements JsonModel {
    private static final String ID = "id";
    private static final String CODE = "code";
    private static final String FULL_NAME = "fullName";
    private static final String EXAM_INFOS = "exams";
    private int id;
    private String code;
    private String fullName;
    private List<Exam> exams;

    public Course () {

    }

    public Course(int id, String code, String fullName) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.exams = new ArrayList<>();
    }

    /**
     * Construct a Course from JSON.
     * @param jsonObject A JSONObject
     */
    public Course(JSONObject jsonObject) {
        this(jsonObject.getInt(ID),
                jsonObject.getString(CODE),
                jsonObject.getString(FULL_NAME));
        JSONArray examsArray = jsonObject.getJSONArray(EXAM_INFOS);
        for (int i = 0; i < examsArray.length(); i++) {
            exams.add(new Exam(examsArray.getJSONObject(i)));
        }
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID, id);
        jsonObject.put(CODE, code);
        jsonObject.put(FULL_NAME, fullName);

        JSONArray examArray = new JSONArray();
        for (Exam examInfo : exams) {
            examArray.put(examInfo.getJSONObject());
        }
        jsonObject.put(EXAM_INFOS, examArray);

        return jsonObject;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void addExam (Exam examInfo) {
        exams.add(examInfo);
    }

    public void removeExam (Exam examInfo) {
        exams.add(examInfo);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (!getCode().equals(course.getCode())) return false;
        if (!getFullName().equals(course.getFullName())) return false;
        return getExams().equals(course.getExams());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getCode().hashCode();
        result = 31 * result + getFullName().hashCode();
        result = 31 * result + getExams().hashCode();
        return result;
    }
}
