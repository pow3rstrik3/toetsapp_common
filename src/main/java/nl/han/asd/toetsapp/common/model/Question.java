package nl.han.asd.toetsapp.common.model;

import org.json.JSONObject;

public class Question implements JsonModel{
	private int id;
	private String title;
	private String description;
	private String questionType;
	private String questionText;
	
	public Question(JSONObject jsonObject) {
		this.id = jsonObject.getInt("id");
		this.title = jsonObject.getString("title");
		this.description = jsonObject.getString("description");
		this.questionType = jsonObject.getString("questionType");
		this.questionText = jsonObject.getString("questionText");
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
        jsonObject.put("id", this.id);
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("questionType", this.questionType);
        jsonObject.put("questionText", this.questionText);
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", questionType=" + questionType + ", questionText="
				+ questionText + ", questionDescription=" + description + "]";
	}
	
}
