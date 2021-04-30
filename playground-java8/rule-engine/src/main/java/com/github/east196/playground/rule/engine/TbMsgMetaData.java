package com.github.east196.playground.rule.engine;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
public final class TbMsgMetaData implements Serializable {

    private final Map<String, String> data = new ConcurrentHashMap<>();

    public TbMsgMetaData(Map<String, String> data) {
        data.forEach((key, val) -> putValue(key, val));
    }

    public String getValue(String key) {
        return data.get(key);
    }

    public void putValue(String key, String value) {
        if (key != null && value != null) {
            data.put(key, value);
        }
    }

    public Map<String, String> values() {
        return new HashMap<>(data);
    }

    public TbMsgMetaData copy() {
        return new TbMsgMetaData(new ConcurrentHashMap<>(data));
    }
}