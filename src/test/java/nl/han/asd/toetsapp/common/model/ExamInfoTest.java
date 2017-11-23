package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExamInfoTest {
    @Test
    public void getJSONObjectWithoutPlugins() throws Exception {
        final int id = 6;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.EXAM;
        final ExamInfo baseExamInfo = new ExamInfo(id, version, title, examType);
        final ExamInfo jsonExamInfo = new ExamInfo(baseExamInfo.getJSONObject());
        assertEquals("ExamInfo converted to JSON and back should keep the same name.", id, jsonExamInfo.getId());
        assertEquals("ExamInfo converted to JSON and back should keep the same version.", version, jsonExamInfo.getVersion());
        assertEquals("ExamInfo converted to JSON and back should keep the same title.", title, jsonExamInfo.getTitle());
        assertEquals("Non-mock ExamInfo converted to JSON and back should stay non-mock.", examType, jsonExamInfo.getExamType());
        assertTrue("ExamInfo converted to JSON and back without plugins should have no plugins", jsonExamInfo.getRequiredPlugins().isEmpty());
    }

    @Test
    public void getJSONObjectMockExam() throws Exception {
        final int id = 60;
        final String version = "1-test";
        final String title = "Test Exam 2";
        final ExamType examType = ExamType.MOCKEXAM;
        final ExamInfo baseExamInfo = new ExamInfo(id, version, title, examType);
        final ExamInfo jsonExamInfo = new ExamInfo(baseExamInfo.getJSONObject());
        assertEquals("ExamInfo converted to JSON and back should keep the same name.", id, jsonExamInfo.getId());
        assertEquals("ExamInfo converted to JSON and back should keep the same version.", version, jsonExamInfo.getVersion());
        assertEquals("ExamInfo converted to JSON and back should keep the same title.", title, jsonExamInfo.getTitle());
        assertEquals("Mock ExamInfo converted to JSON and back should stay mock.", examType, jsonExamInfo.getExamType());
        assertTrue("ExamInfo converted to JSON and back without plugins should have no plugins", jsonExamInfo.getRequiredPlugins().isEmpty());
    }

    @Test
    public void getJSONObjectWithPlugins() throws Exception {
        final int id = 7;
        final String version = "1-test";
        final String title = "Test Exam";
        final ExamType examType = ExamType.EXAM;
        final PluginInfo plugin1 = new PluginInfo("First plugin", "1");
        final PluginInfo plugin2 = new PluginInfo("Second plugin", "2");
        ExamInfo baseExamInfo = new ExamInfo(id, version, title, examType);
        baseExamInfo.addPlugin(plugin1);
        baseExamInfo.addPlugin(plugin2);
        ExamInfo jsonExamInfo = new ExamInfo(baseExamInfo.getJSONObject());
        assertEquals("ExamInfo converted to JSON and back should keep the same name.", id, jsonExamInfo.getId());
        assertEquals("ExamInfo converted to JSON and back should keep the same version.", version, jsonExamInfo.getVersion());
        assertEquals("ExamInfo converted to JSON and back should keep the same title.", title, jsonExamInfo.getTitle());
        assertEquals("Non-mock ExamInfo converted to JSON and back should stay non-mock.", examType, jsonExamInfo.getExamType());
        assertEquals("ExamInfo converted to JSON and back with two plugins should have two plugins.", 2, jsonExamInfo.getRequiredPlugins().size());
        assertTrue("First plugin of copied ExamInfo should remain the same.", plugin1.equals(jsonExamInfo.getRequiredPlugins().get(0)));
        assertTrue("Second plugin of copied ExamInfo should remain the same.", plugin2.equals(jsonExamInfo.getRequiredPlugins().get(1)));
    }

}