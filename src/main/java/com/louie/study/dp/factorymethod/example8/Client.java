package com.louie.study.dp.factorymethod.example8;

/**
 * @author maoliang
 * @date 2019/6/24
 */
public class Client {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new DellMouseFactory();
        Mouse mouse = mouseFactory.createMouse();
        mouse.sayHi();
    }
}
