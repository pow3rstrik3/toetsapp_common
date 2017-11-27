package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void getJSONObjectWithoutExams() throws Exception {
        final Course baseCourse = new Course(1, "SWA", "Software Architecture");
        final Course jsonCourse = new Course(baseCourse.getJSONObject());
        assertTrue("Course without exam infos converted to JSON and back should have no exam information.", jsonCourse.getExams().isEmpty());
        assertTrue("Course converted to JSON and back should equal to its base.", jsonCourse.equals(baseCourse));
        assertEquals("Course converted to JSON and back should have the same hash code.", jsonCourse.hashCode(), baseCourse.hashCode());
        assertEquals("Course converted to JSON and back should have the same string representation.", jsonCourse.toString(), baseCourse.toString());
    }

    @Test
    public void getJSONObjectWithExams() throws Exception {
        final Exam examInfo1 = new Exam(1, "1.0", "APP Exam 1", ExamType.EXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        final Exam examInfo2 = new Exam(2, "1.1", "APP Exam 2", ExamType.MOCKEXAM, 1485000000, 5400, "Test exam", "You can test this.", false);
        final Course baseCourse = new Course(2, "APP", "Algorithms, Programming Languages and Paradigms");
        baseCourse.addExam(examInfo1);
        baseCourse.addExam(examInfo2);
        final Course jsonCourse = new Course(baseCourse.getJSONObject());
        assertEquals("Course with two exam infos converted to JSON and back should still have two exam infos.", 2, jsonCourse.getExams().size());
        assertTrue("First exam info of copied Course should remain the same.", examInfo1.equals(jsonCourse.getExams().get(0)));
        assertTrue("Second exam info of copied Course should remain the same.", examInfo2.equals(jsonCourse.getExams().get(1)));
        assertTrue("Course converted to JSON and back should equal to its base.", jsonCourse.equals(baseCourse));
        assertEquals("Course converted to JSON and back should have the same hash code.", jsonCourse.hashCode(), baseCourse.hashCode());
        assertEquals("Course converted to JSON and back should have the same string representation.", jsonCourse.toString(), baseCourse.toString());
    }

}