package com.marspie.demo;
/**
 * 方法三
 * 单例模式的实现：饱汉式,线程安全简单实现
 * 优点是：使用synchronized关键字避免多线程访问时，出现多个SingletonTest实例。
 * 缺点是：同步方法频繁调用时，效率略低。
 */
public class SingletonTest3 {

    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static SingletonTest3 instance;

    // 定义私有构造方法（防止通过 new SingletonTest()去实例化）
    private SingletonTest3() {
    }

    // 定义一个静态的方法（调用时再初始化SingletonTest，使用synchronized 避免多线程访问时，可能造成重的复初始化问题）
    public static synchronized  SingletonTest3 getInstance() {
        if (instance == null)
            instance = new SingletonTest3();
        return instance;
    }
}