package Java08.Date;

import java.time.Instant;
import java.util.Date;

/**
 * Date方法大部分都已经被废弃。
 */
public class DateDemo {
    public static void main(String[] args) {
        // 1.获取当前日期
        Date date = new Date();
        System.out.println("当前日期：" + date);

        Date date1 = new Date(0);
        System.out.println("date最早能表示的时间" + date1);

        // 2.可用的方法
        boolean after = date.after(date1);
        boolean before = date.before(date1);
        // 获取当前时间距离1970年1月1日0时0分0秒之间的毫秒数
        long time = date.getTime();
        date1.setTime(300);
        //  转成字符串
        String s = date.toString();
        //  转成时间戳
        Instant instant = date.toInstant();
    }
}
