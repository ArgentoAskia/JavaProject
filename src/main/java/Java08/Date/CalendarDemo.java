package Java08.Date;

import sun.util.BuddhistCalendar;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 1.Calendar类Demo
 */
public class CalendarDemo {
    public static void main(String[] args) {
        // TODO: 1.如何创建一个Calendar对象,首先要明白Calendar类是一个抽象类，为了兼容世界上的多种日历计算方式，采用了工厂模式的设计。
        /*
             可以使用Calendar.getInstance();获取一个Calendar实例。
             在JDK中，可以找到三个Calendar的实现类：
             - BuddhistCalendar：佛历，https://baike.baidu.com/item/%E4%BD%9B%E5%8E%86?fromModule=lemma_search-box
             - GregorianCalendar：公历，https://baike.baidu.com/item/%E5%85%AC%E5%85%83/17855?fromtitle=%E5%85%AC%E5%8E%86&fromid=449762&fromModule=lemma_search-box
             - JapaneseImperialCalendar：日本帝国历，https://baike.baidu.com/item/%E6%97%A5%E6%9C%AC%E5%8E%86?fromModule=lemma_search-box
         */
        Calendar instance = Calendar.getInstance();
        /*
            理论上可以直接new Calendar的实现类来实现日历的创建。
            但是需要注意，日本帝国历的实现类并不是public的，因此没法new，这个设计就很奇怪！
         */
        // Calendar buddhistCalendar = new BuddhistCalendar();
        // Calendar gregorianCalendar = new GregorianCalendar();

        // TODO: 2.
        // 获取日历的类型
        boolean weekDateSupported = instance.isWeekDateSupported();
        System.out.println("是否支持WeekDate：" + weekDateSupported);
        String calendarType = instance.getCalendarType();
        System.out.println("calendarType：" + calendarType);
        // 获取一个星期的第一天
        int firstDayOfWeek = instance.getFirstDayOfWeek();
        System.out.println("星期的第一天是" + firstDayOfWeek);
        // Calendar转Date对象
        Date time = instance.getTime();
        System.out.println("Calendar转Date对象：" + time);
        // 获取从1970.1.1到现在的毫秒数
        long timeInMillis = instance.getTimeInMillis();
        System.out.println("获取从1970.1.1到现在的毫秒数：" + timeInMillis);
        // 获取时区
        TimeZone timeZone = instance.getTimeZone();
        System.out.println("时区：" + timeZone);
        // 获取时间戳
        Instant instant = instance.toInstant();
        System.out.println("时间戳：" + instant);
        // 获取字符串
        String s = instance.toString();
        System.out.println("toString：" + s);

        // TODO: 3. WeekYear相关,所谓WeekYear的意思就是不安具体的时间来算年份分割，而是按星期来算，
        //  比如：如在2019年12月31日(星期二)这天，年是2019年，但周年就成了2020年。
        // 2022年和2020年
 //       instance.set(2020, Calendar.DECEMBER, 30);
        // 获取当前WeekYear全年有多少个星期
        int weeksInWeekYear = instance.getWeeksInWeekYear();
        // 获取WeekYear，如当日期是2020.12.30是weekyear是2021
        int weekYear = instance.getWeekYear();
        System.out.println(weeksInWeekYear);
        System.out.println(weekYear);

        // TODO: 4.获取内容
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int date = instance.get(Calendar.DATE);
        int hourOf12 = instance.get(Calendar.HOUR);
        int minute = instance.get(Calendar.MINUTE);
        int second = instance.get(Calendar.SECOND);
        int millisecond = instance.get(Calendar.MILLISECOND);
        int hourOf24 = instance.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = instance.get(Calendar.DAY_OF_MONTH);
        int dayOfWeekInMonth = instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int weekOfMonth = instance.get(Calendar.WEEK_OF_MONTH);
        int weekOfYear = instance.get(Calendar.WEEK_OF_YEAR);
        int ERA = instance.get(Calendar.ERA);
        int AM_PM = instance.get(Calendar.AM_PM);
        int zoneOffset = instance.get(Calendar.ZONE_OFFSET);
        int DSTOffset = instance.get(Calendar.DST_OFFSET);

        // TODO: 5.运算相关
        // roll以日为单位增加,不会超出当前月(在当前月循环),以月为单位不会超过当前年,以年为单位增加,会一直累加
        instance.add(Calendar.DATE,111);
        System.out.println(instance.get(Calendar.DATE));
        System.out.println(instance.get(Calendar.MONTH));
        instance.roll(Calendar.DATE,10);
        System.out.println(instance.get(Calendar.DATE));
        System.out.println(instance.get(Calendar.MONTH));

        Calendar cloneInstance = (Calendar) instance.clone();
        cloneInstance.set(2022, Calendar.JANUARY, 21);
        // 某一个时间是否在另一个时间之前|之后
        boolean before = cloneInstance.before(instance);
        boolean after = instance.after(cloneInstance);

        // TODO: 6.
        boolean lenient = cloneInstance.isLenient();

        Calendar calendar = Calendar.getInstance();
//        calendar.set();
//        calendar.setTime();
    }
}
