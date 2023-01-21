package cn.argentoaskia.proxy;

import Java08.homework.RandomUtil;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DefaultImpl implements Creatable, Deletable, Readable, Writeable{
    private Random random = new Random();

    @Override
    public <T> T create(Class<T> tClass) throws FileNotFoundException, EOFException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T t = tClass.getConstructor().newInstance();
        return t;
    }

    @Override
    public boolean canDelete() {
        return random.nextBoolean();
    }

    @Override
    public Object delete(List<?> list) throws RuntimeException {
        int randomIndex = random.nextInt(list.size());
        return list.remove(randomIndex);
    }

    @Override
    public byte[] read(int bufferSize, int readCount) throws IOException {
        int totalRead = bufferSize * readCount;
        return RandomUtil.randomByteArray(totalRead);
    }

    @Override
    public boolean canRead() {
        return random.nextBoolean();
    }

    @Override
    public boolean canWrite() {
        return random.nextBoolean();
    }

    @Override
    public int write(byte[] bytes) {
        System.out.println(Arrays.toString(bytes));
        return bytes.length;
    }
}
