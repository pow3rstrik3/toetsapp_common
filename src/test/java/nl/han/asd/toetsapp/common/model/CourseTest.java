package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void getJSONObjectWithoutExams() throws Exception {
        final String code = "SWA";
        final String fullName = "Software Architecture";
        final Course baseCourse = new Course(code, fullName);
        final Course jsonCourse = new Course(baseCourse.getJSONObject());
        assertEquals("Course converted to JSON and back should keep the same code.", code, jsonCourse.getCode());
        assertEquals("Course converted to JSON and back should keep the same full name.", fullName, jsonCourse.getFullName());
        assertTrue("Course without exam infos converted to JSON and back should have no exam information.", jsonCourse.getExams().isEmpty());
    }

    @Test
    public void getJSONObjectWithExams() throws Exception {
        final String code = "APP";
        final String fullName = "Software Architecture";
        final Exam examInfo1 = new Exam(1, "1.0", "APP Exam 1", ExamType.EXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        final Exam examInfo2 = new Exam(2, "1.1", "APP Exam 2", ExamType.MOCKEXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        final Course baseCourse = new Course(code, fullName);
        baseCourse.addExam(examInfo1);
        baseCourse.addExam(examInfo2);
        final Course jsonCourse = new Course(baseCourse.getJSONObject());
        assertEquals("Course converted to JSON and back should keep the same code.", code, jsonCourse.getCode());
        assertEquals("Course converted to JSON and back should keep the same full name.", fullName, jsonCourse.getFullName());
        assertEquals("Course with two exam infos converted to JSON and back should still have two exam infos.", 2, jsonCourse.getExams().size());
        assertTrue("First exam info of copied Course should remain the same.", examInfo1.equals(jsonCourse.getExams().get(0)));
        assertTrue("Second exam info of copied Course should remain the same.", examInfo2.equals(jsonCourse.getExams().get(1)));
    }

}