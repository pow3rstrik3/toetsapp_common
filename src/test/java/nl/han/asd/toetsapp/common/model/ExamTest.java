package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExamTest {
    @Test
    public void getJSONExamInfoWithoutPlugins() throws Exception {
        final int id = 8;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.EXAM;
        final Exam baseExam = new Exam(id, version, title, examType, true);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam info converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam info converted to JSON and back should keep the same version.", version, jsonExam.getVersion());
        assertEquals("Exam info converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertTrue("Exam info converted to JSON and back should stay info-only.", jsonExam.isInfoOnly());
        assertEquals("Non-mock exam info converted to JSON and back should stay non-mock.", examType, jsonExam.getExamType());
        assertTrue("Exam info converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
    }

    @Test
    public void getJSONExamInfoWithPlugins() throws Exception {
        final int id = 8;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.EXAM;
        final PluginInfo plugin1 = new PluginInfo("First plugin", "1");
        final PluginInfo plugin2 = new PluginInfo("Second plugin", "2");
        final Exam baseExam = new Exam(id, version, title, examType, true);
        baseExam.addPlugin(plugin1);
        baseExam.addPlugin(plugin2);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam info converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam info converted to JSON and back should keep the same version.", version, jsonExam.getVersion());
        assertEquals("Exam info converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertTrue("Exam info converted to JSON and back should stay info-only.", jsonExam.isInfoOnly());
        assertEquals("Non-mock exam info converted to JSON and back should stay non-mock.", examType, jsonExam.getExamType());
        assertEquals("Exam converted to JSON and back with two plugins should have two plugins.", 2, jsonExam.getRequiredPlugins().size());
        assertTrue("First plugin of copied exam should remain the same.", plugin1.equals(jsonExam.getRequiredPlugins().get(0)));
        assertTrue("Second plugin of copied exam should remain the same.", plugin2.equals(jsonExam.getRequiredPlugins().get(1)));
    }

    @Test
    public void getJSONExamWithoutQuestions() throws Exception {
        final int id = 8;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.MOCKEXAM;
        final Exam baseExam = new Exam(id, version, title, examType, false);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam converted to JSON and back should keep the same version.", version, jsonExam.getVersion());
        assertEquals("Exam converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertFalse("Exam converted to JSON and back should not be info-only.", jsonExam.isInfoOnly());
        assertEquals("Non-mock exam converted to JSON and back should stay non-mock.", examType, jsonExam.getExamType());
        assertTrue("Exam converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
        assertTrue("Exam converted to JSON and back without questions should have no questions", jsonExam.getQuestions().isEmpty());
    }

    @Test
    public void getJSONExamWithQuestions() throws Exception {
        final int id = 8;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.MOCKEXAM;
        final Question question1 = new Question(1, "First question", "This is the first question", "Test questions", "Is this the first question?", "Test questions");
        final Question question2 = new Question(2, "Second question", "This is the second question", "Test questions", "Is this the second question?", "Test questions");
        final Exam baseExam = new Exam(id, version, title, examType, false);
        baseExam.addQuestion(question1);
        baseExam.addQuestion(question2);
        final Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam converted to JSON and back should keep the same version.", version, jsonExam.getVersion());
        assertEquals("Exam converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertFalse("Exam converted to JSON and back should not be info-only.", jsonExam.isInfoOnly());
        assertEquals("Non-mock exam converted to JSON and back should stay non-mock.", examType, jsonExam.getExamType());
        assertTrue("Exam converted to JSON and back without plugins should have no plugins", jsonExam.getRequiredPlugins().isEmpty());
        assertEquals("Exam with two questions converted to JSON and back should still have two questions.", 2, jsonExam.getQuestions().size());
        assertTrue("First question of copied Exam should remain the same.", question1.equals(jsonExam.getQuestions().get(0)));
        assertTrue("Second question of copied Exam should remain the same.", question2.equals(jsonExam.getQuestions().get(1)));
    }

}