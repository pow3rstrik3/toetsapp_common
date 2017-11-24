package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentAnswerTest {
    @Test
    public void getJSONObject() throws Exception {
        final String pluginStudentAnswer = "This is an answer.";
        final StudentAnswer baseStudentAnswer = new StudentAnswer(pluginStudentAnswer);
        final StudentAnswer jsonStudentAnswer = new StudentAnswer(baseStudentAnswer.getJSONObject());
        assertEquals("StudentAnswer converted to JSON and back should keep the same pluginStudentAnswer.", pluginStudentAnswer, jsonStudentAnswer.getPluginAnswer());
    }

}