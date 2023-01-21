package cn.argentoaskia.proxy;

public interface Writeable {

    boolean canWrite();
    int write(byte[] bytes);
}
