package nl.han.asd.toetsapp.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exam implements JsonModel {

    private int id;
	private ExamType examType;
    private String name;
    private String subject;
    private List<Question> questions;

    public Exam(int id, ExamType examType, String name, String subject) {
        this.id = id;
        this.examType = examType;
        this.name = name;
        this.subject = subject;
        this.questions = new ArrayList<>();
    }


    /**
     * A getter for the questions added to the exam
     * @return A list of questions
     */
    public Question[] getQuestions() {
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
        jsonObject.put("examType", examType.toString());
        jsonObject.put("name", this.name);
        jsonObject.put("subject", this.subject);

        JSONArray questionsJsonArray = new JSONArray();
        for (Question question : questions) {
            questionsJsonArray.put(question.getJSONObject());
        }
        jsonObject.put("questions", questionsJsonArray);

        return jsonObject;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", questions=" + questions +
                '}';
    }

}
