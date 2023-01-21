package cn.argentoaskia.proxy;

import java.util.List;

public interface Deletable {

    boolean canDelete();
    Object delete(List<?> list) throws RuntimeException;
}
