package Java08.String;

import java.util.StringJoiner;

public class StringJoinerDemo {
    public static void main(String[] args) {
        // JDK 8
        // 字符串格式化拼接
        // new StringJoiner(String delimiter, String prefix, String suffix)
        // delimiter:分隔符
        // prefix:左连接前缀
        // suffix:右连接后缀
        StringJoiner stringJoiner = new StringJoiner(",","[", "]");
        // add添加拼接的元素
        stringJoiner.add("123").add("456").add("789");
        // 转为字符串
        String s = stringJoiner.toString();
        // 结果：[123,456,789]
        System.out.println(s);
    }
}
