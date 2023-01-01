package Java08.Number;

public class MathDemo {
    public static void main(String[] args) {
        // 取绝对值
        int abs = Math.abs(-5);
        System.out.println(abs);
        // 比较谁大
        int max = Math.max(-6, -7);
        System.out.println(max);
        // 比较谁小
        int min = Math.min(9, 1);
        System.out.println(min);
        // 幂运算
        double pow = Math.pow(-6.9, 2.3);
        System.out.println(pow);
        // 四舍五入
        long round = Math.round(3.5454545);
        System.out.println(round);
        // 向上取整
        double ceil = Math.ceil(3.5454545);
        System.out.println(ceil);
        // 向下取整
        double floor = Math.floor(3.5454545);
        System.out.println(floor);

        // 三角函数
        double cos = Math.cos(0.5);
        double sin = Math.sin(0.5);
        double tan = Math.tan(0.5);
        double sinh = Math.sinh(0.5);
        double cosh = Math.cosh(0.5);
        double tanh = Math.tanh(0.5);
        System.out.println(cos);
        System.out.println(sin);
        System.out.println(tan);
        System.out.println(sinh);
        System.out.println(cosh);
        System.out.println(tanh);

        // 反三角函数
        double acos = Math.acos(0.5);
        double asin = Math.asin(0.5);
        double atan = Math.atan(0.5);
        double atan2 = Math.atan2(0.5, 0.5);
        System.out.println(acos);
        System.out.println(asin);
        System.out.println(atan);
        System.out.println(atan2);

        // 指数函数,对数函数
        double exp = Math.exp(5);
        double log = Math.log(100);
        System.out.println(exp);
        System.out.println(log);

        // 平方根
        double sqrt = Math.sqrt(36);
        System.out.println(sqrt);

        // 接近整数值
        double d = 100.675;
        double e = 100.500;
        double f = 100.200;
        System.out.println(Math.rint(d));
        System.out.println(Math.rint(e));
        System.out.println(Math.rint(f));
    }
}
