package com.whxiaoyu.devops.generator.proxy;

/**
 * @author jinxiaoyu
 */
public class App {

    public static void main(String[] args) {
        //代理的对象
        Subject subject = new SubjectImpl();
        System.out.println("本身执行的方法：--------");
        subject.something();

        //静态代理
        StaticProxy staticProxy = new StaticProxy(subject);
        System.out.println("静态代理：--------");
        staticProxy.something();

        //jdk动态代理
        Subject jdkProxy = new SubjectJdkProxyFactory<>(subject).getProxyInstance();
        System.out.println("jdk动态代理：--------");
        jdkProxy.something();

        //cglib动态代理
        Subject cglibProxy = new SubjectCglibProxyFactory<>(subject).getProxyInstance();
        System.out.println("cglib动态代理：--------");
        cglibProxy.something();
    }
}
