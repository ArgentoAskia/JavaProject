package cn.argentoaskia.observer.observable;

import cn.argentoaskia.observer.observers.BinaryObserver;
import cn.argentoaskia.observer.observers.HexObserver;
import cn.argentoaskia.observer.observers.OctalObserver;

import java.util.Observable;

/**
 * Observable: 被观察对象
 * 这个demo当用户设置10进制的数字的时候，会自动通知其他观察者计算二进制、八进制和十六进制并打印出来
 */
public class DecNumberObservable extends Observable {

    public DecNumberObservable(){
        addObserver(new BinaryObserver());
        addObserver(new HexObserver());
        addObserver(new OctalObserver());
    }
    private Integer number;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
        System.out.println("输入的数字是：" + number);
        // 值改变了
        setChanged();
        // 通知其他观察者计算属性，会调用观察者接口的update方法
        notifyObservers();
        // 其他观察者更新完成并输出之后，输出一行====代表分割
        System.out.println("=================================================");
    }
}
