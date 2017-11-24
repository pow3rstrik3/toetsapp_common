package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course implements JsonModel {
    //TODO: Add ID
    private static final String CODE = "code";
    private static final String FULL_NAME = "fullName";
    private static final String EXAM_INFOS = "exams";
    private String code;
    private String fullName;
    private List<Exam> exams;

    public Course(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
        this.exams = new ArrayList<>();
    }

    public Course(JSONObject jsonObject) {
        this(jsonObject.getString(CODE),
                jsonObject.getString(FULL_NAME));
        JSONArray examsArray = jsonObject.getJSONArray(EXAM_INFOS);
        for (int i = 0; i < examsArray.length(); i++) {
            exams.add(new Exam(examsArray.getJSONObject(i)));
        }
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, code);
        jsonObject.put(FULL_NAME, fullName);

        JSONArray examArray = new JSONArray();
        for (Exam examInfo : exams) {
            examArray.put(examInfo.getJSONObject());
        }
        jsonObject.put(EXAM_INFOS, examArray);

        return jsonObject;
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
}
