package cn.argentoaskia.observer.observers;

import cn.argentoaskia.observer.observable.DecNumberObservable;

import java.util.Observable;
import java.util.Observer;

public class HexObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        String simpleName = o.getClass().getSimpleName();
        System.out.println("className:" + simpleName);
        DecNumberObservable decNumberObservable = (DecNumberObservable) o;
        Integer number = decNumberObservable.getNumber();
        String s = Integer.toHexString(number);
        System.out.println(number + "的十六进制表示是：" + s);
    }
}
