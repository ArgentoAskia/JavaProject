package cn.argentoaskia.observer;

import cn.argentoaskia.observer.observable.DecNumberObservable;

public class ObserverTest {
    public static void main(String[] args) {
        DecNumberObservable decNumberObservable = new DecNumberObservable();
        decNumberObservable.setNumber(50);
        decNumberObservable.setNumber(100);
        decNumberObservable.setNumber(200);
    }
}
