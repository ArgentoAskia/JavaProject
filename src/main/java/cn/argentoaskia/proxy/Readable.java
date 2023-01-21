package cn.argentoaskia.proxy;

import java.io.IOException;

public interface Readable {

    byte[] read(int bufferSize, int readCount) throws IOException;
    boolean canRead();
}
