package com.kvolkov.advancedjava.designpatterns.builder;

public class SingletonExample {

    private static SingletonExample sInstance = null;

    private SingletonExample() {}

    private BaseProduct.Builder product = new BaseProduct.Builder();

    public static SingletonExample getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonExample();
        }
        return sInstance;
    }

    public BaseProduct.Builder getProduct() {
        return product;
    }

    public void printSelf() {
        System.out.println("Привет бобер! Я синглтон!");
    }

}
