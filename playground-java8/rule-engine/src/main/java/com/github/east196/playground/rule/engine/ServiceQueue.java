package com.github.east196.playground.rule.engine;

import lombok.ToString;

import java.util.Objects;

@ToString
public class ServiceQueue {

    public static final String MAIN = "Main";

    private final ServiceType type;
    private final String queue;

    public ServiceQueue(ServiceType type) {
        this.type = type;
        this.queue = MAIN;
    }

    public ServiceQueue(ServiceType type, String queue) {
        this.type = type;
        this.queue = queue != null ? queue : MAIN;
    }

    public ServiceType getType() {
        return type;
    }

    public String getQueue() {
        return queue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceQueue that = (ServiceQueue) o;
        return type == that.type &&
                queue.equals(that.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, queue);
    }

}