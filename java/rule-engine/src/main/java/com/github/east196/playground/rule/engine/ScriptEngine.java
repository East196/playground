package com.github.east196.playground.rule.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.ListenableFuture;

import javax.script.ScriptException;
import java.util.Set;

public interface ScriptEngine {

    TbMsg executeUpdate(TbMsg msg) throws ScriptException;

    ListenableFuture<TbMsg> executeUpdateAsync(TbMsg msg);

    TbMsg executeGenerate(TbMsg prevMsg) throws ScriptException;

    boolean executeFilter(TbMsg msg) throws ScriptException;

    ListenableFuture<Boolean> executeFilterAsync(TbMsg msg);

    Set<String> executeSwitch(TbMsg msg) throws ScriptException;

    JsonNode executeJson(TbMsg msg) throws ScriptException;

    ListenableFuture<JsonNode> executeJsonAsync(TbMsg msg) throws ScriptException;

    String executeToString(TbMsg msg) throws ScriptException;

    void destroy();

}
