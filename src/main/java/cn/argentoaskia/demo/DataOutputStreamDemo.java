package cn.argentoaskia.demo;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataOutputStream是一个可以写出Java数据类型的流，如写出一个int、char、String、byte、long等
 */
public class DataOutputStreamDemo {
//    生成数据
    public static void main(String[] args) throws IOException {
        // 先描述一下路劲问题，比方说我们当前的大项目是JavaProject（D:\OpenSourceProject\JavaProject），
        // 在JavaProject里面有很多个模块如Java-Exception（D:\OpenSourceProject\JavaProject\Java-Exception）、Java-IOStream（D:\OpenSourceProject\JavaProject\Java-IOStream）等等
        // 则直接new File("DataFile.txt")，这个文件会被放在项目根目录，变成（D:\OpenSourceProject\JavaProject\DataFile.txt）
        // 如果是这样写new File("/DataFile.txt")，则是要求放在根目录的意思，会变成：D:\DataFile.txt
        // 因此需要手动target等等
//        File file = new File("Java-IOStream/src/main/resources/DataFile.txt");
//        String path = file.getAbsolutePath();
//        System.out.println(path);
        FileOutputStream fileOutputStream = new FileOutputStream("Java-IOStream/src/main/resources/DataFile.txt");
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.write("Hello World!".getBytes());
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeByte(125);
        dataOutputStream.writeBytes("Hello Java!");
        dataOutputStream.writeChars("Hello String!");
        dataOutputStream.writeDouble(5.2);
        dataOutputStream.writeInt(2);
        dataOutputStream.writeLong(52102014545554545L);
        dataOutputStream.writeUTF("我的世界！！");
        dataOutputStream.close();
        fileOutputStream.close();
    }
}
