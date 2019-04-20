package com.logan;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 *
 * CGLibProxy demo
 *
 * 为了弥补JDK动态代理的不足，cglib代理在底层字节码的基础上创建类，因为需要动态的去创建类，所以创建类所花费的时间要大于JDK动态代理
 * 若一次创建，多次服务，那么效果还是比较好的。
 *
 *
 * @author logan
 */
public class CGLibProxy implements MethodInterceptor {

    private Object target;
    private Enhancer enhancer = new Enhancer();

    public CGLibProxy(Object target){
        this.target = target;
    }

    /**
     * get proxy object
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }


    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("proxy before .....");
        long startTime = System.currentTimeMillis();
        Object object = methodProxy.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("proxy after .....");
        System.out.println("spend time is " + (endTime - startTime) );
        return object;
    }
}
