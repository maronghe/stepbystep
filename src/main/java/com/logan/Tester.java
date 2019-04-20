package com.logan;



public class Tester {

    public static void main(String[] args) {

        IMaster master = new CGLibProxy(new Master()).getProxy();
        master.sayHello("Logan");
        System.out.println(master.select("Nancy"));
        System.out.println("=============");

        IMaster proxyMaster = new JDKDynamicProxy(new Master()).getProxy();
        proxyMaster.sayHello("xiaoma");
        System.out.println(proxyMaster.select("123"));

    }

}
