package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course implements JsonModel {
    private static final String CODE = "code";
    private static final String FULL_NAME = "fullName";
    private static final String EXAM_INFOS = "examInfos";
    private String code;
    private String fullName;
    private List<ExamInfo> examInfos;

    public Course(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
        this.examInfos = new ArrayList<>();
    }

    public Course(JSONObject jsonObject) {
        this(jsonObject.getString(CODE),
                jsonObject.getString(FULL_NAME));
        JSONArray examInfosArray = jsonObject.getJSONArray(EXAM_INFOS);
        for (int i = 0; i < examInfosArray.length(); i++) {
            examInfos.add(new ExamInfo(examInfosArray.getJSONObject(i)));
        }
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, code);
        jsonObject.put(FULL_NAME, fullName);

        JSONArray examInfosArray = new JSONArray();
        for (ExamInfo examInfo : examInfos) {
            examInfosArray.put(examInfo.getJSONObject());
        }
        jsonObject.put(EXAM_INFOS, examInfosArray);

        return jsonObject;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public List<ExamInfo> getExamInfos() {
        return examInfos;
    }

    public void addExamInfo (ExamInfo examInfo) {
        examInfos.add(examInfo);
    }

    public void removeExam (ExamInfo examInfo) {
        examInfos.add(examInfo);
    }
}
