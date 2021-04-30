package com.github.east196.playground.rule.engine;

import com.github.east196.playground.util.AbstractListeningExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsExecutorService extends AbstractListeningExecutor {

    @Value("${actors.rule.js_thread_pool_size}")
    private int jsExecutorThreadPoolSize;

    @Override
    protected int getThreadPollSize() {
        return jsExecutorThreadPoolSize;
    }

}