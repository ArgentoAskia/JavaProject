package cn.argentoaskia.observer.observers;

import cn.argentoaskia.observer.observable.DecNumberObservable;

import java.util.Observable;
import java.util.Observer;

public class OctalObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String simpleName = o.getClass().getSimpleName();
        System.out.println("className:" + simpleName);
        DecNumberObservable decNumberObservable = (DecNumberObservable) o;
        Integer number = decNumberObservable.getNumber();
        String s = Integer.toOctalString(number);
        System.out.println(number + "的八进制表示是：" + s);
    }
}
