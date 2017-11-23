package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {
    @Test
    public void getJSONObject() throws Exception {
        final int id = 5;
        final String title = "Test question";
        final String description = "This is a test.";
        final String questionType = "type";
        final String questionText = "What is a test?";
        final Question baseQuestion = new Question(id, title, description, questionType, questionText);
        final Question jsonQuestion = new Question(baseQuestion.getJSONObject());
        assertEquals("Questions converted to JSON and back should keep the same id.", id, jsonQuestion.getId());
        assertEquals("Questions converted to JSON and back should keep the same title.", title, jsonQuestion.getTitle());
        assertEquals("Questions converted to JSON and back should keep the same description.", description, jsonQuestion.getContext());
    }

}