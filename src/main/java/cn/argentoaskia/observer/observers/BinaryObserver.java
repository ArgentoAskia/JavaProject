package cn.argentoaskia.observer.observers;

import cn.argentoaskia.observer.observable.DecNumberObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * 二进制转换器，观察者类，观察DecNumberObservable
 */
public class BinaryObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // 注意由于notifyObservers()方法是先清楚hasChanged标记，再通知Observers进行更新
        // 所以进入到Observers的时候，hasChanged标记已经被清除，这就导致在Observer实现类里面调用
        // hasChanged方法永远都是false
        // 这可能更多的是在面对多线程环境下做的妥协！
        String simpleName = o.getClass().getSimpleName();
        System.out.println("className:" + simpleName);
        DecNumberObservable decNumberObservable = (DecNumberObservable) o;
        Integer number = decNumberObservable.getNumber();
        String s = Integer.toBinaryString(number);
        System.out.println(number + "的二进制表示是：" + s);
    }
}
