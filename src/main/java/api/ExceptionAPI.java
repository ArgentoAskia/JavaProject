package api;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.rmi.AccessException;
import java.util.Random;

/**
 * 该类显示异常信息的各个部分意思，及其对应的API。
 */

public class ExceptionAPI {
    // IOException
    // RuntimeException
    // throws
    // throw
    // 异常包装
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                ExceptionAPI.method();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExceptionAPI exceptionAPI = new ExceptionAPI();
        String s = exceptionAPI.get();
        System.out.println(s);
        try {
            exceptionAPI.throwIOException();
        } catch (IOException e) {
            // 1.如果不知道需要干啥的，可以直接e.printStackTrace();
            // 2.输出异常信息
            String message = e.getMessage();
            String localizedMessage = e.getLocalizedMessage();
            System.out.println("message:" + message);
            System.out.println("localizedMessage:" + localizedMessage);
            throw new RuntimeException();
        } finally {
            System.out.println(123131313);
        }
    }

    public String get() {
        try {
            throwIOException();
        } catch (IOException e) {
            e.printStackTrace();
//            return "12313";
            System.exit(0);
        }finally {
            System.out.println("123123123123");
        }
        return "456456";
    }
    public static <T extends IOException> void method() throws T{
        T exception;
        Random random = new Random();
        boolean b = random.nextBoolean();
        if (b){
            exception = (T) new EOFException("EOF");
        }else{
            exception = (T) new AccessException("access");
        }
        throw exception;
    }
    public void throwIOException() throws IOException {
        IOException exception = new IOException("我是IOException高层异常，我带了三个其他异常，请收好~~~");
        try{
            throwEOFException();
        }catch (EOFException eofException){
          exception.initCause(eofException);
          try{
              throwBindException();
          }catch (BindException bindException){
              exception.addSuppressed(bindException);
              try{
                  throwConnectException();
              }catch (ConnectException connectException){
                  exception.addSuppressed(connectException);
              }
          }
        }
        throw exception;
    }
    private void throwEOFException() throws EOFException {
        throw new EOFException("已经到达文件尾啦~");
    }
    private void throwBindException() throws BindException {
        throw new BindException("bindException作为伴随异常（Suppressed）存在IOException异常里面了");
    }
    private void throwConnectException() throws ConnectException {
        throw new ConnectException("connectException作为伴随异常（Suppressed）存在IOException异常里面了");
    }
}
