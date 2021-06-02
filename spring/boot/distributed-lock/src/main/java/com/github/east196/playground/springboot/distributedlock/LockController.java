package com.github.east196.playground.springboot.distributedlock;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/lock")
public class LockController {
    /**
     * 参考： https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8
     * @return
     */
    @RequestMapping("/redisson")
    @SneakyThrows
    public String redissonLock() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("perredis://127.0.0.1:7181");

        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        // 加锁以后10秒钟自动解锁
// 无需调用unlock方法手动解锁
        lock.lock(10, TimeUnit.SECONDS);

// 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(100L, 10L, TimeUnit.SECONDS);
        if (res) {
            try {
                //TODO do something
            } finally {
                lock.unlock();
            }
        }
        return "";
    }

    @RequestMapping("/curator")
    @SneakyThrows
    public String curatorLock() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("perredis://127.0.0.1:7181");

        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        // 加锁以后10秒钟自动解锁
// 无需调用unlock方法手动解锁
        lock.lock(10, TimeUnit.SECONDS);

// 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(100L, 10L, TimeUnit.SECONDS);
        if (res) {
            try {
                //TODO do something
            } finally {
                lock.unlock();
            }
        }
        return "";
    }
}
