package Java08.String;

import java.util.UUID;

public class StringDemo {
    public static void main(String[] args) {
        String string = "abcdefhskfjsadahasd31313dff";

        System.out.println("--> 1.获取字符串中的某个字符——charAt(int index) <--");
        char c = string.charAt(2);
        System.out.println(string + "的第三个字符是" + c);
        System.out.println();

        System.out.println("--> 2.获取字符串中的某个字符（Unicode代码点整数形式）——codePointAt(int index) <--");
        int i = string.codePointAt(3);
        System.out.println(string + "的第三个字符对应Unicode：" + i);
        System.out.println();

        System.out.println("--> 3.比较两个字符串谁大谁小——compareTo()、compareToIgnoreCase()：不区分大小写<--");
        System.out.println("大于返回1，等于返回0，小于返回-1");
        int result1 = string.compareTo("abcdefhskfjsadahasd3131");
        int result2 = string.compareToIgnoreCase("ABCDEFhskfjsadahasd31313dfF");
        System.out.println("字符串" + string + "和abcdefhskfjsadahasd3131的比较结果是" + result1 + "说明：" + string + ">" + "abcdefhskfjsadahasd3131");
        System.out.println("字符串" + string + "和ABCDEFhskfjsadahasd31313dfF的比较结果是" + result2 + "说明：" + string + "=" + "ABCDEFhskfjsadahasd31313df");
        System.out.println();

        System.out.println("--> 4.字符串连接concat()、字符串包含contains()<--");
        String result3 = string.concat("呜呜呜呜呜");
        boolean containsResult = result3.contains("呜呜呜");
        System.out.println(result3);
        System.out.println(containsResult);
        System.out.println();

        System.out.println("--> 5.字符串判等 contentEquals()、equals()、<--");
        boolean contentEquals = string.contentEquals("呜呜呜呜呜");
        boolean equals = string.equals("abcdefhskfjsadahasd31313dff");
        boolean equalsIgnoreCase = string.equalsIgnoreCase("ABCDEFhskfjsadahasd31313dFf");
        System.out.println(contentEquals);
        System.out.println(equals);
        System.out.println(equalsIgnoreCase);
        System.out.println();

        System.out.println("--> 6.字符串定位 indexOf()、lastIndexOf() <--");
        int firstS = string.indexOf("s");
        int nextS = string.indexOf("s", firstS + 1);
        System.out.println(firstS);
        System.out.println(nextS);
        int lastS = string.lastIndexOf("s");
        System.out.println(lastS);

    }
}
