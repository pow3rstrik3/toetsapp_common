package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {
    @Test
    public void getJSONObjectWithoutSubjects() throws Exception {
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
        assertTrue("Questions without subjects converted to JSON and back should have no subjects.", jsonQuestion.getSubjects().isEmpty());
    }

    @Test
    public void getJSONObjectWithSubjects() throws Exception {
        final int id = 5;
        final String title = "Test question";
        final String description = "This is a test.";
        final String questionType = "type";
        final String questionText = "What is a test?";
        final Question baseQuestion = new Question(id, title, description, questionType, questionText);
        String subject1 = "Graphs";
        String subject2 = "Dijkstra";
        baseQuestion.addSubject(subject1);
        baseQuestion.addSubject(subject2);
        final Question jsonQuestion = new Question(baseQuestion.getJSONObject());
        assertEquals("Questions converted to JSON and back should keep the same id.", id, jsonQuestion.getId());
        assertEquals("Questions converted to JSON and back should keep the same title.", title, jsonQuestion.getTitle());
        assertEquals("Questions converted to JSON and back should keep the same description.", description, jsonQuestion.getContext());
        assertEquals("Questions with two subjects converted to JSON and back should still have two subjects.", 2, jsonQuestion.getSubjects().size());
        assertEquals("First subject of a question should remain the same after converting to JSON and back.", subject1, jsonQuestion.getSubjects().get(0));
        assertEquals("Second subject of a question should remain the same after converting to JSON and back.", subject2, jsonQuestion.getSubjects().get(1));
    }
}