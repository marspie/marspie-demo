package com.marspie.demo;
/**
 * 方法四
 * 单例模式最优方案
 * 线程安全  并且效率高
 * 单例模式的最佳实现。内存占用地，效率高，线程安全，多线程操作原子性。
 */
public class SingletonTest4 {

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile SingletonTest4 instance;

    // 定义一个私有构造方法
    private SingletonTest4() {

    }

    //定义一个共有的静态方法，返回该类型实例
    public static SingletonTest4 getIstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (SingletonTest4.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new SingletonTest4();
                }
            }
        }
        return instance;
    }
}