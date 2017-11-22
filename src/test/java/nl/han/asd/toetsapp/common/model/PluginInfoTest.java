package nl.han.asd.toetsapp.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PluginInfoTest {
    @Test
    public void getJSONObject() throws Exception {
        final String name = "Test Plugin";
        final String version = "1.0-test";
        final PluginInfo basePluginInfo = new PluginInfo(name, version);
        final PluginInfo jsonPluginInfo = new PluginInfo(basePluginInfo.getJSONObject());
        assertEquals("PluginInfo converted to JSON and back should keep the same name.", name, jsonPluginInfo.getName());
        assertEquals("PluginInfo converted to JSON and back should keep the same version.", version, jsonPluginInfo.getVersion());
    }

}