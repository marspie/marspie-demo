package com.marspie.demo;
/**
 * 方法一
 * 单例模式的实现：饿汉式,线程安全 但效率比较低
 */
public class SingletonTest1 {

    // 将自身的实例对象设置为一个属性,并加上Static和final修饰符
    private static final SingletonTest1 instance = new SingletonTest1();

    // 定义一个私有的构造方法
    private SingletonTest1() {

    }

    // 静态方法返回该类的实例
    public static SingletonTest1 getInstancei() {
        return instance;
    }

}