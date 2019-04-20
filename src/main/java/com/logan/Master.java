package com.logan;

public class Master implements IMaster {

    public void sayHello(String name){
        System.out.println("Hello, " + name);
    }

    public String select(String name){
        return "name = " + name;
    }

}
