package cn.argentoaskia.proxy;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Creatable {

    <T> T create(Class<T> tClass) throws FileNotFoundException, EOFException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
