package com.github.east196.playground;

import cn.hutool.core.date.DateUtil;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Boon {

    public static int nThreads() {
        int numberOfCore = Runtime.getRuntime().availableProcessors();
        double blockingCoefficient = 0.9;
        int poolSize = (int) (numberOfCore / (1 - blockingCoefficient));
        return poolSize;
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String uuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String areaCode(String prefix) {
        return prefix + UUID.randomUUID().toString().replace("-", "");
    }


    public static Date praseDate(String dateOrDateTime) {
        return DateUtil.parse(dateOrDateTime);
    }

    public static String shortDate(Date date) {
        return DateUtil.formatDate(date);
    }

    public static String shortDate(long millis) {
        return DateUtil.formatDate(new Date(millis));
    }

    public static String longDate(Date date) {
        return DateUtil.formatDateTime(date);
    }

    public static String longDate(long millis) {
        return DateUtil.formatDateTime(new Date(millis));
    }

    public static boolean notBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean notEmpty(Object obj) {
        return length(obj) != 0;
    }

    public static boolean isEmpty(Object obj) {
        return length(obj) == 0;
    }

    public static boolean isNullOrEmpty(Object obj) {
        return length(obj) == 0;
    }

    public static int length(Object obj) {
        if (obj == null) {
            return 0;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).size();
        } else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        } else {
            return 1;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<T> list, Class<T> type) {
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    public static Duration timeDuration(Integer seconds) {
        if (seconds == null || seconds == 0L) {
            return Duration.ZERO;
        }
        return Duration.ofSeconds(seconds.longValue());
    }

    public static Duration timeDuration(Long mills) {
        if (mills == null || mills == 0L) {
            return Duration.ZERO;
        }
        return Duration.ofMillis(mills);
    }

    public static Duration timeDuration(Date startTime, Date endTime) {
        if (startTime == null || endTime == null || startTime.after(endTime)) {
            return Duration.ZERO;
        }
        return Duration.ofMillis(endTime.getTime() - startTime.getTime());
    }

    public static Long timeDurationToMin(Date startTime, Date endTime) {
        if (startTime == null || endTime == null || startTime.after(endTime)) {
            return Duration.ZERO.toMinutes();
        }
        return Duration.ofMillis(endTime.getTime() - startTime.getTime()).toMinutes();
    }

    public static byte[] toByteArray(int value) {
        return new byte[]{(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value};
    }


}
