package com.marspie.demo;
/**
 * 方法二
 * 单例模式的实现：饱汉式,非线程安全
 *
 */
public class SingletonTest2 {

    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static SingletonTest2 instance;

    // 定义私有构造方法（防止通过 new SingletonTest()去实例化）
    private SingletonTest2() {

    }

    // 定义一个静态的方法（调用时再初始化SingletonTest，但是多线程访问时，可能造成重复初始化问题）
    public static SingletonTest2 getInstance() {
        if (instance == null)
            instance = new SingletonTest2();
        return instance;
    }
}