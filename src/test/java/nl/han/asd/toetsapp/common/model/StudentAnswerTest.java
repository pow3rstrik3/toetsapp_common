package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentAnswerTest {
    @Test
    public void getJSONObject() throws Exception {
        final StudentAnswer baseStudentAnswer = new StudentAnswer("This is an answer.");
        final StudentAnswer jsonStudentAnswer = new StudentAnswer(baseStudentAnswer.getJSONObject());
        assertTrue("Student answer converted to JSON and back should equal to its base.", jsonStudentAnswer.equals(baseStudentAnswer));
        assertEquals("Student answer converted to JSON and back should have the same hash code.", jsonStudentAnswer.hashCode(), baseStudentAnswer.hashCode());
        assertEquals("Student answer converted to JSON and back should have the same string representation.", jsonStudentAnswer.toString(), baseStudentAnswer.toString());
    }

}