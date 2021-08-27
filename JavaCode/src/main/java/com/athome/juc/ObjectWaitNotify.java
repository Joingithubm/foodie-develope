package com.athome.juc;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 *  线程的等待与唤醒
 * @Author Zengfc
 * @Date 2021/8/27 11:01
 * @Version 1.0
 */
public class ObjectWaitNotify {

    static Object object = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition  = lock.newCondition();


    /**
     *  LockSupport 原理： 向线程发放许可证，每次执行LockSupport.park消耗一张许可证，没有许可证线程被阻塞。
     *      同时LockSupport.unpart 向线程发放许可证，一次只能发放一张，多次发放不能累加。最多只有一张。
     *      解决： 1. 线程阻塞不需要在synchorized 关键字修饰的代码块，
     *            2. 线程在提前发放许可证，而不需要先阻塞后唤醒
     * @param args
     */
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t come in");
                LockSupport.park();
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "\t go on");
            }
        }, "t1");


        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t come in");

                LockSupport.unpark(thread);
                System.out.println(Thread.currentThread().getName()+"\t go on");
            }
        },"t2").start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t come in");
                LockSupport.unpark(thread);
                System.out.println(Thread.currentThread().getName()+"\t go on");
            }
        },"t3").start();



    }

    /**
     * Lock 中的await和signal
     *  局限： 只能在lock中使用
     *         只能先等待后唤醒
     */
    private static void extracted1() {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() +"\t ---- 启动了");
                    condition.await();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() +"\t ---- 休息结束");
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();
                try {
                    condition.signal();
                    System.out.println(Thread.currentThread().getName()+"\t  - 唤醒了");
                } finally {
                    lock.unlock();
                }
            }
        },"t2").start();
    }

    /**
     *  Object中的wait和notify
     *  必须与 synchronized 代码块使用,在同步方法中不行
     */
    private static void extracted() {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                synchronized (object){
                    System.out.println(Thread.currentThread().getName() +"\t ---- 启动了");
                    object.wait();
                }
                System.out.println(Thread.currentThread().getName() +"\t ---- 休息结束");
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    object.notify();
                    System.out.println(Thread.currentThread().getName()+"\t  - 唤醒了");
                }
            }
        },"t2").start();
    }



    private static synchronized void  method1(String name) throws InterruptedException {
        System.out.println(name +"\t method1 come in ");
        object.wait();
        System.out.println(name +"\t method1 go on ");
    }

    private static synchronized void  method2(String name) throws InterruptedException {

        object.notify();
        System.out.println(name + "\t method2  notify ");
    }
}
