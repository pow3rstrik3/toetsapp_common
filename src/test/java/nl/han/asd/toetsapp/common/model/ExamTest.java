package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExamTest {
    @Test
    public void getJSONObjectWithoutQuestions() throws Exception {
        final int id = 8;
        final String title = "Test Exam";
        final String version = "1-test";
        final String subject = "APP";
        final boolean isMock = false;
        ExamInfo examInfo = new ExamInfo(id, version, title, subject, isMock);
        Exam baseExam = new Exam(id, title, examInfo);
        Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertTrue("Exam without questions converted to JSON and back should have no questions.", jsonExam.getQuestions().isEmpty());
        assertTrue("Exam converted to JSON and back should keep the same exam info.", jsonExam.getExamInfo().equals(jsonExam.getExamInfo()));
    }

    @Test
    public void getJSONObjectWithQuestions() throws Exception {
        final int id = 9;
        final String version = "1-test";
        final String title = "Test Exam 2";
        final String subject = "SWA";
        final boolean isMock = false;ExamInfo examInfo = new ExamInfo(id, version, title, subject, isMock);
        Exam baseExam = new Exam(id, title, examInfo);
        final Question question1 = new Question(1, "First question", "This is the first question", "Test questions", "Is this the first question?");
        final Question question2 = new Question(1, "Second question", "This is the second question", "Test questions", "Is this the second question?");
        baseExam.addQuestion(question1);
        baseExam.addQuestion(question2);
        Exam jsonExam = new Exam(baseExam.getJSONObject());
        assertEquals("Exam converted to JSON and back should keep the same id.", id, jsonExam.getId());
        assertEquals("Exam converted to JSON and back should keep the same title.", title, jsonExam.getTitle());
        assertTrue("Exam converted to JSON and back should keep the same exam info.", jsonExam.getExamInfo().equals(jsonExam.getExamInfo()));
        assertEquals("Exam with two questions converted to JSON and back should still have two questions.", 2, jsonExam.getQuestions().size());
        assertTrue("First question of copied Exam should remain the same.", question1.equals(jsonExam.getQuestions().get(0)));
        assertTrue("Second question of copied Exam should remain the same.", question2.equals(jsonExam.getQuestions().get(1)));
    }

}