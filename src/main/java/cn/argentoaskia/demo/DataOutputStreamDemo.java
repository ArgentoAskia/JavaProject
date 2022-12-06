package cn.argentoaskia.demo;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataOutputStream是一个可以写出Java数据类型的流，如写出一个int、char、String、byte、long等
 */
public class DataOutputStreamDemo {
//    生成数据
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\OpenSourceProject\\JavaProject\\Java-IOStream\\src\\main\\resources\\DataFile.txt");
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
