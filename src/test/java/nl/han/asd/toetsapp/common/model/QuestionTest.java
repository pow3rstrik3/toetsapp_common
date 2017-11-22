package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {
    @Test
    public void getJSONObject() throws Exception {
        int id = 5;
        String title = "Test question";
        String description = "This is a test.";
        String questionType = "type";
        String questionText = "What is a text?";
        Question baseQuestion = new Question(id, title, description, questionType, questionText);
        Question jsonQuestion = new Question(baseQuestion.getJSONObject());
        assertEquals("Questions converted to JSON and back should keep the same id.", id, jsonQuestion.getId());
        assertEquals("Questions converted to JSON and back should keep the same title.", title, jsonQuestion.getTitle());
        assertEquals("Questions converted to JSON and back should keep the same description.", description, jsonQuestion.getDescription());
        assertEquals("Questions converted to JSON and back should keep the same question type.", questionType, jsonQuestion.getQuestionType());
        assertEquals("Questions converted to JSON and back should keep the same question text.", questionText, jsonQuestion.getQuestionText());
    }

}