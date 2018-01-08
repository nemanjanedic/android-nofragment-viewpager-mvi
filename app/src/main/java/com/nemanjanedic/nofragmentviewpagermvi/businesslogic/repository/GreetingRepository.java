package com.nemanjanedic.nofragmentviewpagermvi.businesslogic.repository;

import java.util.Random;

import io.reactivex.Observable;

/**
 * Dummy repository that will return random greeting with a thread sleep delay.
 */
public class GreetingRepository {

    private static final String GREETING_1 = "Hello there!";
    private static final String GREETING_2 = "Howdy!";
    private static final String GREETING_3 = "Good day!";

    public Observable<String> getGreeting() {
        return Observable.fromCallable(this::getRandomGreetingWithDelay);
    }

    private String getRandomGreetingWithDelay() {
        try {
            Thread.sleep(1500);

            int greetingNumber = new Random().nextInt(3);
            switch (greetingNumber) {
                case 0:
                    return GREETING_1;
                case 1:
                    return GREETING_2;
                case 2:
                    return GREETING_3;
            }
        } catch (InterruptedException e) {
            // probably not the best way to handle the exception
            e.printStackTrace();
        }
        return "";
    }

}
