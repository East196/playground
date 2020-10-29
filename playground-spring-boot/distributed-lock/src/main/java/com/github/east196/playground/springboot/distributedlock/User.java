package com.github.east196.playground.springboot.distributedlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class User {

    public static void main(String[] args) {
        //重试策略, 参数1：等待时间, 参数2：重试次数
        RetryPolicy policy = new ExponentialBackoffRetry(2000, 3);

        //创建zookeeper客户端连接
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.1.30:2181").retryPolicy(policy).build();
        client.start();

        //获取锁对象
        final InterProcessMutex mutex = new InterProcessMutex(client, "/locks");

        //创建10个线程，相当于10个用户去抢购5部手机
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //请求锁
                    mutex.acquire();

                    //执行抢购业务
                    buy();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        //释放锁
                        mutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 抢购
     */
    public static void buy() {
        System.out.println("【" + Thread.currentThread().getName() + "】开始抢购");
        //获取剩余手机数量
        int currentNumber = 10000;

        if (currentNumber == 0) {
            System.out.println("抢购已结束，下次再来吧");
        } else {
            System.out.println("剩余手机数量：" + currentNumber);

            //睡眠3秒，模拟业务逻辑处理耗时时间
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //购买后数量减1
            currentNumber--;
        }
        System.out.println("【" + Thread.currentThread().getName() + "】  购买结束");
        System.out.println("-----------------------------------------");
    }
}