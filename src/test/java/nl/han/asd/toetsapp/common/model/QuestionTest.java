package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void getJSONObjectWithSubjects() throws Exception {
        final String subject1 = "Graphs";
        final String subject2 = "Dijkstra";
        PluginInfo plugin = new PluginInfo("test-plugin", "1.0");
        final Question baseQuestion = new Question(5, "Test question", "This is a test", "What is a test?", plugin, subject1);
        baseQuestion.addSubject(subject2);
        final Question jsonQuestion = new Question(baseQuestion.getJSONObject());
        assertEquals("Questions with two subjects converted to JSON and back should still have two subjects.", 2, jsonQuestion.getSubjects().size());
        assertEquals("First subject of a question should remain the same after converting to JSON and back.", subject1, jsonQuestion.getSubjects().get(0));
        assertEquals("Second subject of a question should remain the same after converting to JSON and back.", subject2, jsonQuestion.getSubjects().get(1));
        assertTrue("Question converted to JSON and back should equal to its base.", jsonQuestion.equals(baseQuestion));
        assertEquals("Question converted to JSON and back should have the same hash code.", jsonQuestion.hashCode(), baseQuestion.hashCode());
        assertEquals("Question converted to JSON and back should have the same string representation.", jsonQuestion.toString(), baseQuestion.toString());
    }
}