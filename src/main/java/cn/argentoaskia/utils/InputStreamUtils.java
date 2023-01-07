package cn.argentoaskia.utils;

import cn.argentoaskia.demo.InputStreamDemo;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入流工具类.
 *
 * @author Askia
 * @version 1.0.1
 * @since 1.0
 */
public class InputStreamUtils {

    /**
     * 字节读取buffer大小
     * 预定义读取：1kb = 1024 bytes
     * @since 1.0
     */
    public static final int BUFFER_1KB_SIZE = 1024;
    public static final int BUFFER_2KB_SIZE = BUFFER_1KB_SIZE * 2;
    public static final int BUFFER_4KB_SIZE = BUFFER_1KB_SIZE * 4;
    public static final int BUFFER_8KB_SIZE = BUFFER_1KB_SIZE * 8;
    public static final int BUFFER_16KB_SIZE = BUFFER_1KB_SIZE * 16;
    public static final int BUFFER_32KB_SIZE = BUFFER_1KB_SIZE * 32;
    public static final int BUFFER_64KB_SIZE = BUFFER_1KB_SIZE * 64;
    public static final int BUFFER_128KB_SIZE = BUFFER_1KB_SIZE * 128;
    public static final int BUFFER_256KB_SIZE = BUFFER_1KB_SIZE * 256;
    public static final int BUFFER_512KB_SIZE = BUFFER_1KB_SIZE * 512;
    public static final int BUFFER_1MB_SIZE = BUFFER_1KB_SIZE * 1024;
    public static final int BUFFER_2MB_SIZE = BUFFER_1MB_SIZE * 2;
    public static final int BUFFER_4MB_SIZE = BUFFER_1MB_SIZE * 4;
    public static final int BUFFER_8MB_SIZE = BUFFER_1MB_SIZE * 8;
    public static final int BUFFER_16MB_SIZE = BUFFER_1MB_SIZE * 16;
    public static final int BUFFER_32MB_SIZE = BUFFER_1MB_SIZE * 32;
    public static final int BUFFER_64MB_SIZE = BUFFER_1MB_SIZE * 64;
    public static final int BUFFER_128MB_SIZE = BUFFER_1MB_SIZE * 128;
    public static final int BUFFER_256MB_SIZE = BUFFER_1MB_SIZE * 256;
    public static final int BUFFER_512MB_SIZE = BUFFER_1MB_SIZE * 512;
    public static final int BUFFER_1GB_SIZE = BUFFER_1MB_SIZE * 1024;


    /**
     * 读取流中所有的字节，并关闭流
     * @param inputStream 要读取的目标流
     * @return 返回读取的所有字节
     * @since 1.0
     * @see InputStreamUtils#readAllBytes(InputStream, int)
     */
    public static byte[] readAllBytes(InputStream inputStream) {
        return readAllBytes(inputStream, BUFFER_1KB_SIZE);
    }

    /**
     * 读取流中所有的字节，并关闭流。
     * @param inputStream 要读取的目标流
     * @param bufferSize 一次获取的buffer大小
     * @return 返回读取的所有字节
     * @since 1.0
     * @see InputStreamUtils#peekBytes(InputStream, int, byte[])
     */
    public static byte[] readAllBytes(InputStream inputStream, int bufferSize){
        List<byte[]> bytesRead = new ArrayList<>();
        byte[] bytes = new byte[bufferSize];
        int reading = 0;
        int totalBytes = 0;
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)){
            while ((reading = bufferedInputStream.read(bytes)) > 0){
                byte[] readingBytes = Arrays.copyOf(bytes, reading);
                totalBytes += reading;
                bytesRead.add(readingBytes);
                Arrays.fill(bytes, (byte)0);
            }
            byte[] data = new byte[totalBytes];
            int cursor = 0;
            for (byte[] partOfBytesRead :
                    bytesRead) {
                System.arraycopy(partOfBytesRead, 0, data, cursor, partOfBytesRead.length);
                cursor += partOfBytesRead.length;
            }
            inputStream.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 预先探索流里面的字节并返回，这些字节被读取之后会被放回流。
     * <p>
     * 会返回一个新的流来重新包装被peek的流，因此调用方式可以参考：
     * <code><pre>
     * InputStream input = ...;
     * byte[] peekData = ...;
     * input = InputStreamUtils.peekBytes(input, bufferSize, peekData);
     * </pre></code>
     * <strong>
     *     该方法会关闭作为第一个参数的输入流，因此原本的输入流将变得不可用，可以将返回值赋予给原先的输入流变量。
     * </strong>
     *
     * @param inputStream 待读取数据的输入流
     * @param bufferSize 每次读取数据所使用的buffer的大小（每次能从流里面读取多少个字节），可以使用{@link InputStreamUtils}的{@code BUFFER_X_SIZE}常量
     * @param data peek的数据，用户需要peek多少数据则创建多大的数组即可
     * @return 一个新的输入流，是第一个参数的输入流的拷贝
     * @since 1.0
     * @see InputStreamUtils#peekBytes(InputStream, byte[])
     */
    public static InputStream peekBytes(InputStream inputStream, int bufferSize, byte[] data) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            LinkedList<byte[]> list = new LinkedList<>();
            byte[] buffer = new byte[bufferSize];
            int totalBytesCount = 0;
            int reading = 0;
            while ((reading = bufferedInputStream.read(buffer)) > 0) {
                byte[] partOfBytes = Arrays.copyOf(buffer, reading);
                list.add(partOfBytes);
                totalBytesCount += reading;
                Arrays.fill(buffer, (byte) 0);
            }
            // 复制字节到数组并返回
            byte[] totalBytes = new byte[totalBytesCount];
            int cursor = 0;
            for (byte[] bytes : list) {
                System.arraycopy(bytes, 0, totalBytes, cursor, bytes.length);
                cursor += bytes.length;
            }
            System.arraycopy(totalBytes, 0, data, 0, data.length);
            inputStream.close();
            return new ByteArrayInputStream(totalBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return inputStream;
        }
    }

    /**
     * 指定采用{@code 1KB}大小的{@code Buffer}来预先探索流里面的字节并返回，这些字节被读取之后会被放回流。
     *
     * @param inputStream 待读取数据的输入流
     * @param data peek的数据，用户需要peek多少数据则创建多大的数组即可
     * @return 一个新的输入流，是第一个参数的输入流的拷贝
     * @since 1.0
     * @see InputStreamUtils#peekBytes(InputStream, int, byte[])
     */
    public static InputStream peekBytes(InputStream inputStream, byte[] data){
        return peekBytes(inputStream, BUFFER_1KB_SIZE, data);
    }

    private static boolean isParameterTypeMatch(Class<?>[] classes, Class<?>[] parameterTypesClasses){
        return false;
    }
    private static Class<?>[] composeClassesParameters(int inputStreamArgIndex, Object... args){
        Class<?>[] classes = new Class[args.length + 1];
        int j = 0;
        for (int i = 0; i < args.length + 1; i++) {
            if (inputStreamArgIndex == i){
                classes[i] = InputStream.class;
                continue;
            }else if (inputStreamArgIndex < i){
                j = i - 1;
            }else{
                j = i;
            }
            Class<?> aClass = args[j].getClass();
            classes[i] = aClass;
        }
        return classes;
    }
    private static Object[] composeArgs(InputStream target, int targetInputStreamArgIndex, Object... args){
        return null;
    }

    /**
     *
     * @param inputStreamClass
     * @param target
     * @param targetInputStreamArgIndex
     * @param args
     * @param <T>
     * @return
     * @since 1.0.1
     */
    @SuppressWarnings("unchecked")
    public static <T extends InputStream> T inputStreamWrapper(Class<T> inputStreamClass, InputStream target, int targetInputStreamArgIndex, Object... args){
        Class<?>[] classes = composeClassesParameters(targetInputStreamArgIndex, args);
        Constructor<?>[] constructors = inputStreamClass.getConstructors();
        Constructor<?> callerConstructor = null;
        for (Constructor<?> c :
                constructors){
            int parameterCount = c.getParameterCount();
            if (classes.length == parameterCount){
                Class<?>[] parameterTypes = c.getParameterTypes();
                if(isParameterTypeMatch(classes, parameterTypes)){
                    // 获取到可调用的constructor
                    callerConstructor = c;
                }
            }
        }
        if (callerConstructor != null){
            Object[] objects = composeArgs(target, targetInputStreamArgIndex, args);
            try {
                T o = (T)callerConstructor.newInstance(objects);
                return o;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return (T) target;
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = InputStreamDemo.class.getResourceAsStream("/data.txt");
        byte[] bytes1 = new byte[resourceAsStream.available()];
        InputStream bytes = peekBytes(resourceAsStream, 1024, bytes1);
        int read = bytes.read();
        System.out.println(read);
        System.out.println(Arrays.toString(bytes1));
    }
}
