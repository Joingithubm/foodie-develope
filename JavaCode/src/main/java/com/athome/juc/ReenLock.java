package com.athome.juc;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:    可重入锁
 *      1. 概念： 在线程已经获取了锁对象，在线程内部可以多次获取该锁对象，而不会阻塞线程。（重点：同一个锁对象）
 *      2. 种类：  1> synchronized
 *                2> ReentrantLock
 * @Author Zengfc
 * @Date 2021/8/27 14:52
 * @Version 1.0
 */
public class ReenLock {

    static Object object = new Object();
    static Object objectTwo = new Object();
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(object){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t 最外层拿到锁了");
                    synchronized(object){
                        System.out.println(Thread.currentThread().getName()+"\t 中层拿到锁了");
                        synchronized(object){
                            System.out.println(Thread.currentThread().getName()+"\t 内层拿到锁了");
                        }
                    }
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(objectTwo){
                    System.out.println(Thread.currentThread().getName()+"\t 最外层拿到锁了");
                    synchronized(object){
                        System.out.println(Thread.currentThread().getName()+"\t 中层拿到锁了");
                        synchronized(object){
                            System.out.println(Thread.currentThread().getName()+"\t 内层拿到锁了");
                        }
                    }
                }
            }
        },"t2").start();
    }
}
