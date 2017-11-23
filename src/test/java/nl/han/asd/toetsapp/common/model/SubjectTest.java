package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubjectTest {

    @Test
    public void getJSONObjectWithoutExams() throws Exception {
        final String code = "SWA";
        final String fullName = "Software Architecture";
        final Subject baseSubject = new Subject(code, fullName);
        final Subject jsonSubject = new Subject(baseSubject.getJSONObject());
        assertEquals("Subject converted to JSON and back should keep the same code.", code, jsonSubject.getCode());
        assertEquals("Subject converted to JSON and back should keep the same full name.", fullName, jsonSubject.getFullName());
        assertTrue("Subject without exam infos converted to JSON and back should have no exam information.", jsonSubject.getExamInfos().isEmpty());
    }

    @Test
    public void getJSONObjectWithExams() throws Exception {
        final String code = "APP";
        final String fullName = "Software Architecture";
        final ExamInfo examInfo1 = new ExamInfo(1, "1.0", "APP Exam 1", false);
        final ExamInfo examInfo2 = new ExamInfo(2, "1.1", "APP Exam 2", true);
        final Subject baseSubject = new Subject(code, fullName);
        baseSubject.addExamInfo(examInfo1);
        baseSubject.addExamInfo(examInfo2);
        final Subject jsonSubject = new Subject(baseSubject.getJSONObject());
        assertEquals("Subject converted to JSON and back should keep the same code.", code, jsonSubject.getCode());
        assertEquals("Subject converted to JSON and back should keep the same full name.", fullName, jsonSubject.getFullName());
        assertEquals("Subject with two exam infos converted to JSON and back should still have two exam infos.", 2, jsonSubject.getExamInfos().size());
        assertTrue("First exam info of copied Subject should remain the same.", examInfo1.equals(jsonSubject.getExamInfos().get(0)));
        assertTrue("Second exam info of copied Subject should remain the same.", examInfo2.equals(jsonSubject.getExamInfos().get(1)));
    }

}