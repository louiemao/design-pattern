package com.louie.study.dp.creational.abstractfactory.example1;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}