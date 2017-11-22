package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class Question implements JsonModel{
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String QUESTION_TYPE = "questionType";
    private static final String QUESTION_TEXT = "questionText";
    private int id;
    private String title;
    private String description;
    private String questionType;
    private String questionText;

    public Question(int id, String title, String description, String questionType, String questionText) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questionType = questionType;
        this.questionText = questionText;
    }

    public Question(JSONObject jsonObject) {
        this(jsonObject.getInt(ID),
            jsonObject.getString(TITLE),
            jsonObject.getString(DESCRIPTION),
            jsonObject.getString(QUESTION_TYPE),
            jsonObject.getString(QUESTION_TEXT));
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID, this.id);
        jsonObject.put(TITLE, this.title);
        jsonObject.put(DESCRIPTION, this.description);
        jsonObject.put(QUESTION_TYPE, this.questionType);
        jsonObject.put(QUESTION_TEXT, this.questionText);
        return jsonObject;
    }

    public boolean equals (Question other) {
        return getId() == other.getId() &&
                getTitle().equals(other.getTitle()) &&
                getDescription().equals(other.getDescription()) &&
                getQuestionType().equals(other.getQuestionType()) &&
                getQuestionText().equals(other.getQuestionText());
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", questionType=" + questionType + ", questionText="
                + questionText + ", questionDescription=" + description + "]";
    }

}
