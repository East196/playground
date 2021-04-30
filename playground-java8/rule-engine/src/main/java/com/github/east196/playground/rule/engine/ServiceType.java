package com.github.east196.playground.rule.engine;

public enum ServiceType {

    TB_CORE, TB_RULE_ENGINE, TB_TRANSPORT, JS_EXECUTOR;

    public static ServiceType of(String serviceType) {
        return ServiceType.valueOf(serviceType.replace("-", "_").toUpperCase());
    }
}