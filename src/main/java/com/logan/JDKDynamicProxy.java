package com.logan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理：被代理的对象需要实现一个接口，代理类对被代理类的方法进行拦截，在实际执行被代理类的方法前后，加上日志，事务，权限控制等。。
 *
 * 1.事务，日志，权限控制是和业务逻辑不相干的，它们是垂直正交的关系。则可看做AOP中的"切面"。
 * 2.切入的方法，比如在Dao层所有的add*，update*,delete*之前的方法加上事务，成功了就提交事务，失败了就回滚。
 *   可称之为切点 CutPoint
 *
 *
 * JDK 动态代理 ： spring 中默认使用JDK动态代理，若想使用CGLib，则需要配置 <aop:conﬁg proxy-target-class="true"> 默认为false
 * 随着JDK版本的不断升级，最开始jdk动态代理的效率逐渐在提高。
 *
 * 优点：
 * 1.对比静态代理来说，动态代理可以代码复用。
 * 2.使用静态代理时，如果代理类和被代理类同时实现了一个接口，当接口方法有变动时，代理类也必须同时修改。
 *
 * 缺点：
 * 1.只能代理实现了接口的类，否则将不能使用JDK 动态代理。
 *
 *
 * @author logan
 *
 * @Date 2019年04月20日15:12:42
 *
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target){
        this.target = target;
    }

    public <T> T getProxy(){
        return  (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces(), this);
    }



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk dynamic proxy before .....");
        long startTime = System.currentTimeMillis();
        Object object = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("jdk dynamic proxy after .....");
        System.out.println("jdk dynamic proxy spend time is " + (endTime - startTime) );
        return object;
    }

}
