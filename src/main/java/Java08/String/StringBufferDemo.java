package Java08.String;

import java.util.UUID;

/**
 * StringBuilder同理
 */
public class StringBufferDemo {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        // 流式API,拼接元素
        stringBuffer.append("Hello").append(" ").append("W").append("o").append("rld");
        // 转为字符串
        String s = stringBuffer.toString();
        // 字符串反转,注意返回的reverse和stringBuffer是同一个引用。
        StringBuffer reverse = stringBuffer.reverse();
        System.out.println("原始字符串：" + s);
        System.out.println("反转之后的字符串：" + reverse.toString());
        System.out.println("=================================================");

        // stringBuffer内置容量，stringBuffer为了提高效率会预分配一个空间用来存放字符串
        // 这个空间一般会比length大或者等于，当这个空间满了之后就会扩容，类似于Vector结构
        int capacity = stringBuffer.capacity();
        // stringBuffer内置字符的数量
        int length = stringBuffer.length();
        System.out.println("StringBuffer容量：" + capacity);
        System.out.println("StringBuffer内字符串的长度：" + length);
        // 压缩容量,这行代码会删除stringBuffer中预先分配的没有使用的空位，这会导致capacity()和length()的结果一致
        stringBuffer.trimToSize();
        System.out.println("压缩空间之后...");
        int capacity1 = stringBuffer.capacity();
        int length1 = stringBuffer.length();
        System.out.println("StringBuffer容量：" + capacity1);
        System.out.println("StringBuffer内字符串的长度：" + length1);
        System.out.println("=================================================");

        // 字符串定位，indexOf:某个字符第一次出现的地方（下标），lastIndexOf:某个字符最后一次出现的地方（下标）
        System.out.println("现在字符串是：" + stringBuffer);
        int lIndex = stringBuffer.indexOf("l");
        System.out.println("l字符第一次出现的地方（index）：" + lIndex);
        int lLastIndex = stringBuffer.lastIndexOf("l");
        System.out.println("l字符最后一次出现的地方（index）：" + lLastIndex);
        System.out.println("=================================================");

        // 在某处（index）插入，
        // 删除从开始处（下标，包括）到结束处（下标，不包括）、
        // 替换从开始处（下标，包括）到结束处（下标，不包括）字符串，替换的字符串会被放在结束处的字符之前
        System.out.println("现在字符串是：" + stringBuffer);
        stringBuffer.insert(0, "abcdefg");
        System.out.println("在开头插入abcdefg：" + stringBuffer);
        stringBuffer.delete(0, stringBuffer.indexOf("g"));
        System.out.println("删除字符a-f：" + stringBuffer);
        stringBuffer.replace(0, 2, "abc");
        System.out.println("替换gd成abc：" + stringBuffer);
        System.out.println("=================================================");

        // 获取从开始处（下标，包括）到结束处（下标，不包括）的字符串
        System.out.println("输出下标是2-3子串");
        String substring = stringBuffer.substring(2, 4);
        System.out.println(substring);
    }
}
