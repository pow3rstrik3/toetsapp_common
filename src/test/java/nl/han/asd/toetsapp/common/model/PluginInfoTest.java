package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PluginInfoTest {
    @Test
    public void getJSONObject() throws Exception {
        final PluginInfo basePluginInfo = new PluginInfo("Test Plugin", "1.0-test");
        final PluginInfo jsonPluginInfo = new PluginInfo(basePluginInfo.getJSONObject());
        assertTrue("PluginInfo converted to JSON and back should equal to its base.", jsonPluginInfo.equals(basePluginInfo));
        assertEquals("PluginInfo converted to JSON and back should have the same hash code.", jsonPluginInfo.hashCode(), basePluginInfo.hashCode());
        assertEquals("PluginInfo converted to JSON and back should have the same string representation.", jsonPluginInfo.toString(), basePluginInfo.toString());
    }

}