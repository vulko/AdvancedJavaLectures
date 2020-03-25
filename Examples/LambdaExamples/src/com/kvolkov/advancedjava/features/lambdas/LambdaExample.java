package com.kvolkov.advancedjava.features.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample {

    List<IMethodRefExample> mIFaceList = new ArrayList<>();

    public interface ILambdaExample {
        boolean lamdaExampleCheck(int a, String b, boolean c);
    }

    public interface IMethodRefExample {
        boolean methodRefExample();
    }

    public void run() {

        Function<Integer, String> stringLambda = (Integer value) -> {
            return checkValue(value);
        };

        Function<String, Void> printLambda = (String value) -> {
            System.out.println(value);
            return null;
        };

        Function<Integer, Void> stringAndPrint = stringLambda.andThen(printLambda);

        stringAndPrint.apply(-10);
        stringAndPrint.apply(0);
        stringAndPrint.apply(10);

        ILambdaExample lambdaExampleShort = (a, b, c) -> true;
        ILambdaExample lambdaExampleFull = (a, b, c) -> {
            return true;
        };

        ILambdaExample lambdaExampleOldSchool = new ILambdaExample() {
            @Override
            public boolean lamdaExampleCheck(int a, String b, boolean c) {
                return false;
            }
        };

        mIFaceList.forEach(IMethodRefExample::methodRefExample);
    }

    private String checkValue(int val) {
        if (val >= 0) {
            return val + " is positive";
        } else {
            return val + " is negative";
        }
    }

    private void runLambdaExample(Predicate<Boolean> predicate) {
//        if (predicate.test())
    }

}
