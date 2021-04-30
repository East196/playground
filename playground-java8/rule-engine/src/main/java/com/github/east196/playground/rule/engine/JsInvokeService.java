package com.github.east196.playground.rule.engine;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.UUID;


/**
 * js调用服务
 */
public interface JsInvokeService {

    /**
     * 解析
     * @param scriptType
     * @param scriptBody
     * @param argNames
     * @return
     */
    ListenableFuture<UUID> eval(JsScriptType scriptType, String scriptBody, String... argNames);

    /**
     * 调用
     * @param scriptId
     * @param args
     * @return
     */
    ListenableFuture<Object> invokeFunction(UUID scriptId, Object... args);

    /**
     * 释放
     * @param scriptId
     * @return
     */
    ListenableFuture<Void> release(UUID scriptId);

}