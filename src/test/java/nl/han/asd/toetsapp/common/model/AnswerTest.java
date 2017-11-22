package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    @Test
    public void getJSONObject() throws Exception {
        final String pluginAnswer = "This is an answer.";
        final Answer baseAnswer = new Answer(pluginAnswer);
        final Answer jsonAnswer = new Answer(baseAnswer.getJSONObject());
        assertEquals("Answer converted to JSON and back should keep the same pluginAnswer.", pluginAnswer, jsonAnswer.getPluginAnswer());
    }

}