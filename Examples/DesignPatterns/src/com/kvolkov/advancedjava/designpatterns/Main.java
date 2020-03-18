package com.kvolkov.advancedjava.designpatterns;

import com.kvolkov.advancedjava.designpatterns.builder.BaseProduct;
import com.kvolkov.advancedjava.designpatterns.builder.SingletonExample;
import com.kvolkov.advancedjava.designpatterns.listener.ObservableExample;

import java.awt.desktop.SystemEventListener;

public class Main {

    public static void main(String[] args) {
//        builderExample();

//        singletonExample();

        listenerExample();
    }

    private static void listenerExample() {
        ObservableExample.start();
    }

    private static void singletonExample() {
        SingletonExample.getInstance().printSelf();

        if (SingletonExample.getInstance() == SingletonExample.getInstance()) {
            System.out.println("Все отлично!");
        } else {
            System.out.println("ФСЁ ПРОПАЛО!");
        }
    }

    private static void builderExample() {
        System.out.println(
                new BaseProduct.Builder()
                        .setName("Мопед вялый")
                        .setDescription("100 л.с.")
                        .setPrice(10000.f)
                        .build().toString());

        System.out.println(
                new BaseProduct.Builder()
                        .setName("Мопед средний")
                        //.setDescription("200 л.с.")
                        .setPrice(20000.f)
                        .build().toString());

        System.out.println(
                new BaseProduct.Builder()
                        .setName("Мопед огонь")
                        .setDescription("1000 л.с.")
                        .setPrice(20000.f)
                        .build().toString());
    }
}
