package com.github.east196.playground.rule.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@ConditionalOnProperty(prefix = "js", value = "evaluator", havingValue = "local", matchIfMissing = true)
@Service
public class NashornJsInvokeService extends AbstractNashornJsInvokeService {

    @Value("${js.local.use_js_sandbox}")
    private boolean useJsSandbox;

    @Value("${js.local.monitor_thread_pool_size}")
    private int monitorThreadPoolSize;

    @Value("${js.local.max_cpu_time}")
    private long maxCpuTime;

    @Value("${js.local.max_errors}")
    private int maxErrors;

    @Value("${js.local.max_black_list_duration_sec:60}")
    private int maxBlackListDurationSec;

    @Override
    protected boolean useJsSandbox() {
        return useJsSandbox;
    }

    @Override
    protected int getMonitorThreadPoolSize() {
        return monitorThreadPoolSize;
    }

    @Override
    protected long getMaxCpuTime() {
        return maxCpuTime;
    }

    @Override
    protected int getMaxErrors() {
        return maxErrors;
    }

    @Override
    protected long getMaxBlacklistDuration() {
        return TimeUnit.SECONDS.toMillis(maxBlackListDurationSec);
    }
}