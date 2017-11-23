package nl.han.asd.toetsapp.common.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exam implements JsonModel {
    private static final String EXAM_INFO = "examInfo";
    private static final String QUESTIONS = "questions";
    private ExamInfo examInfo;
    private List<Question> questions;

    public Exam(ExamInfo examInfo) {
        this.examInfo = examInfo;
        this.questions = new ArrayList<>();
    }

    public Exam(JSONObject jsonObject) {
        this(new ExamInfo(jsonObject.getJSONObject(EXAM_INFO)));
        JSONArray jsonQuestions = jsonObject.getJSONArray(QUESTIONS);
        for (int i = 0; i < jsonQuestions.length(); i++) {
            questions.add(new Question(jsonQuestions.getJSONObject(i)));
        }
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    /**
     * A getter for the questions added to the exam
     * @return A list of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * A getter for the questions added to the exam, converted to an array.
     * @return An array of questions
     */
    public Question[] getQuestionsArray() {
        return questions.toArray(new Question[0]);
    }


    /**
     * This method will add a question to the exam
     * @param question The question to add
     * @return Success
     */
    public boolean addQuestion(Question question) {
        return this.questions.add(question);
    }


    /**
     * This will create a JSONObject which represents the model
     * @return A JSON Object
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(EXAM_INFO, examInfo.getJSONObject());

        JSONArray questionsJsonArray = new JSONArray();
        for (Question question : questions) {
            questionsJsonArray.put(question.getJSONObject());
        }
        jsonObject.put(QUESTIONS, questionsJsonArray);

        return jsonObject;
    }


    @Override
    public String toString() {
        return "Exam{" +
                ", examInfo=" + examInfo +
                ", questions=" + questions +
                '}';
    }
}
