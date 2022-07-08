package Java08.homework;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static void main(String[] args) {
        int i = RandomUtil.randomIntInBounds(50, 5000);
        System.out.println(i);
    }

    private static Random random = new Random();
    public static int randomInt(){
        return random.nextInt();
    }
    public static double randomDouble(){
        return random.nextDouble();
    }
    public static short randomShort(){
        int i = random.nextInt(Short.MAX_VALUE + 2);
        if (i == (Short.MAX_VALUE + 1) || !random.nextBoolean()){
            i = -i;
        }
        String s = String.valueOf(i);
        return Short.parseShort(s);
    }
    public static byte randomByte(){
        int i = random.nextInt(Byte.MAX_VALUE + 2);
        System.out.println(i);
        if (i == (Byte.MAX_VALUE + 1) || !random.nextBoolean()){
            i = i * -1;
        }
        String s = String.valueOf(i);
        return Byte.parseByte(s);
    }
    public static long randomLong(){
        return random.nextLong();
    }
    public static float randomFloat(){
        return random.nextFloat();
    }
    public static boolean randomBoolean(){
        return random.nextBoolean();
    }
    public static char randomUnicode(){
        int i = random.nextInt(Short.MAX_VALUE + 1);
        char a = (char) i;
        System.out.println(a);
        return a;
    }
    public static char randomCharacter(){
        int i = random.nextInt(Byte.MAX_VALUE + 1);
        char a = (char) i;
        return a;
    }


    public static <T> T randomArraySelect(T[] array){
        int i = random.nextInt(array.length);
        return array[i];
    }

    public static int randomIntInBounds(int low, int high) {
        int bound = high - low + 1;
        int boundGap = random.nextInt(bound);
        int result = low + boundGap;
        return result;
    }
    public static byte[] randomBytes(int length){
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }
    public static int[] randomInts(int length){
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            int i1 = random.nextInt();
            ints[i] = i1;
        }
        return ints;
    }

    public static char[] randomCharacters(int length){
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = randomCharacter();
        }
        return chars;
    }
    public static char[] randomUnicodes(int length){
        char[] unicodes = new char[length];
        for (int i = 0; i < unicodes.length; i++) {
            unicodes[i] = randomUnicode();
        }
        return unicodes;
    }

    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    private static Object arrayRef;
    private static Object[] arrayCopyRef;
    public static <T> Object randomArraySelectUncycle(T[] array){
        if(arrayRef == null){
            arrayCopyRef = Arrays.copyOf(array, array.length, array.getClass());
            arrayRef = array;
        } else if(arrayRef != array){
            // 替换数组
            arrayCopyRef = Arrays.copyOf(array, array.length, array.getClass());
            arrayRef = array;
        }
        Object ret = null;
        int i;
        do{
            i = random.nextInt(array.length);
        }while((ret = arrayCopyRef[i]) == null);
        arrayCopyRef[i] = null;
        return ret;
    }

}
