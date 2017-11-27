package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExamTest {
    @Test
    public void getJSONExamInfoWithoutPlugins() throws Exception {
        final Exam baseExam = new Exam(8, "1-test", "Test Exam", ExamType.EXAM, 1485000000, 5400, "Test exam", "You can test this.", true);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertTrue("Exam info converted to JSON and back should stay info-only.", jsonExam.isInfoOnly());
        assertFalse("Non-mock exam info converted to JSON and back should stay non-mock.", jsonExam.isMock());
        assertTrue("Exam info converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
        assertTrue("Exam info converted to JSON and back should equal to its base.", jsonExam.equals(baseExam));
        assertEquals("Exam info converted to JSON and back should have the same hash code.", jsonExam.hashCode(), baseExam.hashCode());
        assertEquals("Exam info converted to JSON and back should have the same string representation.", jsonExam.toString(), baseExam.toString());
    }

    @Test
    public void getJSONExamInfoWithPlugins() throws Exception {
        final PluginInfo plugin1 = new PluginInfo("First plugin", "1");
        final PluginInfo plugin2 = new PluginInfo("Second plugin", "2");
        final Exam baseExam = new Exam(8, "1-test", "Test Exam", ExamType.EXAM, 1485000000, 5400, "Test exam", "You can test this.", true);
        baseExam.addPlugin(plugin1);
        baseExam.addPlugin(plugin2);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertTrue("Exam info converted to JSON and back should stay info-only.", jsonExam.isInfoOnly());
        assertFalse("Non-mock exam info converted to JSON and back should stay non-mock.", jsonExam.isMock());
        assertEquals("Exam converted to JSON and back with two plugins should have two plugins.", 2, jsonExam.getRequiredPlugins().size());
        assertTrue("First plugin of copied exam should remain the same.", plugin1.equals(jsonExam.getRequiredPlugins().get(0)));
        assertTrue("Second plugin of copied exam should remain the same.", plugin2.equals(jsonExam.getRequiredPlugins().get(1)));
        assertTrue("Exam info converted to JSON and back should equal to its base.", jsonExam.equals(baseExam));
        assertEquals("Exam info converted to JSON and back should have the same hash code.", jsonExam.hashCode(), baseExam.hashCode());
        assertEquals("Exam info converted to JSON and back should have the same string representation.", jsonExam.toString(), baseExam.toString());
    }

    @Test
    public void getJSONExamWithoutQuestions() throws Exception {
        final Exam baseExam = new Exam(8, "1-test", "Test Exam", ExamType.MOCKEXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertFalse("Exam converted to JSON and back should not be info-only.", jsonExam.isInfoOnly());
        assertTrue("Mock exam converted to JSON and back should stay mock.", jsonExam.isMock());
        assertTrue("Exam converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
        assertTrue("Exam converted to JSON and back without questions should have no questions", jsonExam.getQuestions().isEmpty());
        assertTrue("Exam converted to JSON and back should equal to its base.", jsonExam.equals(baseExam));
        assertEquals("Exam converted to JSON and back should have the same hash code.", jsonExam.hashCode(), baseExam.hashCode());
        assertEquals("Exam converted to JSON and back should have the same string representation.", jsonExam.toString(), baseExam.toString());
    }

    @Test
    public void getJSONExamWithQuestions() throws Exception {
        PluginInfo plugin = new PluginInfo("test-plug", "1.0");
        final Question question1 = new Question(1, "First question", "This is the first question", "Is this the first question?", plugin, "Test questions");
        final Question question2 = new Question(2, "Second question", "This is the second question", "Is this the second question?", plugin, "Test questions");
        final Exam baseExam = new Exam(8, "1-test", "Test Exam", ExamType.EXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        baseExam.addQuestion(question1);
        baseExam.addQuestion(question2);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertFalse("Exam converted to JSON and back should not be info-only.", jsonExam.isInfoOnly());
        assertFalse("Non-mock exam converted to JSON and back should stay non-mock.", jsonExam.isMock());
        assertTrue("Exam converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
        assertEquals("Exam with two questions converted to JSON and back should still have two questions.", 2, jsonExam.getQuestions().size());
        assertTrue("First question of copied Exam should remain the same.", question1.equals(jsonExam.getQuestions().get(0)));
        assertTrue("Second question of copied Exam should remain the same.", question2.equals(jsonExam.getQuestions().get(1)));
        assertTrue("Exam converted to JSON and back should equal to its base.", jsonExam.equals(baseExam));
        assertEquals("Exam converted to JSON and back should have the same hash code.", jsonExam.hashCode(), baseExam.hashCode());
        assertEquals("Exam info converted to JSON and back should have the same string representation.", jsonExam.toString(), baseExam.toString());
    }

}