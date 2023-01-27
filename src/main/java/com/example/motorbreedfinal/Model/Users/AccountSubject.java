package com.example.motorbreedfinal.Model.Users;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountSubject {
    private static List<AccountObserver> observers = new ArrayList<>();

    private static List<BuyerObserver> orderObservers = new ArrayList<>();

    public static void attach(AccountObserver observer){
        observers.add(observer);

    }
    public static void attach(BuyerObserver observer){
        orderObservers.add(observer);
    }
    public void notifyObservers(){
        for(AccountObserver observer : observers){
            observer.update();
        }
    }



}
