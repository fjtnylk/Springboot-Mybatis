package com.muy.common.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class ScriptEngineUtils {
    private static ScriptEngineManager manager;
    private static ScriptEngine engine;

    public static ScriptEngine getEngineInstance(){
        if (manager == null) {
            manager = new ScriptEngineManager();
            engine = manager.getEngineByName("Groovy");
        }
        if (engine == null) {
            engine = manager.getEngineByName("Groovy");
        }
        return engine;
    }
}
