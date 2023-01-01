package Java08.homework;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.UUID;

/**
 * UUID、CDKey生成类
 *
 * @author Askia
 * @version 1.2
 * @since 1.0
 * @support www.argentoaskia.cn
 * @update 2022/11/1
 */
public class UUIDKeyUtil {
    public static void main(String[] args) {
        System.out.println(randomUUID());
        System.out.println(UUIDKeyUtil.randomUUID(false, true));
        String s = randomCDKey();
        System.out.println(s);
    }

    /**
     * 生成带{@code -}的随机小写的{@code UUID}。
     *
     * @since 1.0
     * @return  UUID,一个32位的唯一序列，比如这种：{@code acd7e584-0970-41e9-a26b-538f95b053ae}
     */
    public static String randomUUID(){
        return randomUUID(true);
    }

    /**
     * 生成随机的小写的{@code UUID}。
     *
     * @param withHyphen 指定这个{@code UUID}是否带{@code -},{@code true}代表带，{@code false}代表不带
     * @return 32位的{@code UUID}
     * @since 1.0
     */
    public static String randomUUID(boolean withHyphen){
        return randomUUID(withHyphen, false);
    }

    /**
     * 生成随机UUID。
     *
     * @param withHyphen 生成的UUID是否带{@code -}符号
     * @param isUpperCase 生成的UUID是否大写
     * @return 32位的{@code UUID}
     * @since 1.0
     */
    public static String randomUUID(boolean withHyphen, boolean isUpperCase){
        String uuid = UUID.randomUUID().toString();
        if (withHyphen && !isUpperCase){
            return uuid;
        }else if(withHyphen){
            return uuid.toUpperCase();
        }else {
            String[] split = uuid.split("-");
            String uuidWithoutHyphen = split[0] +
                    split[1] + split[2] +
                    split[3] + split[4];
            if (!isUpperCase){
                return uuidWithoutHyphen;
            }else{
                return uuidWithoutHyphen.toUpperCase();
            }
        }
    }

    private static final char[] charGaps =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G',
                    'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                    'V', 'W', 'X', 'Y', 'Z'
            };
    private static final char[] numGaps =
            {
                    '0', '1', '2', '3',
                    '4', '5', '6',
                    '7', '8', '9',
            };

    /**
     * 生成随机的{@code CD-KEY}. 比如说：{@code 05K56D-46T753-7SG172-E16M5J-O2MWVA}。该方法随机生成4-6节，每节4-6的字符的{@code CD-KEY}（因此有9种组合可能），如下面的都是这个方法可能生成的：
     * <pre>
     *     <code>05K56D-46T753-7SG172-E16M5J-O2MWVA</code>
     *     <code>05K6D-46T53-7G172-E165J-O2MVA</code>
     *     <code>5K6D-4653-7G12-E15J-O2MA-FF72D</code>
     *     <code>HJ633-SS45S-BJCEF-GR456</code>
     * </pre>
     *
     * <p>
     *     对于下面这个{@code 05K56D-46T753-7SG172-E16M5J-O2MWVA},我们一般称这个{@code CD-KEY}有5个节({@code session}),每个节有6个字符<br/>
     *     每个节使用{@code -}分割
     * @return {@code CD-KEY}
     * @since 1.1
     */
    public static String randomCDKey(){
        Random random = new Random();
        // randomKeyLength: 4-6
        int randomKeyLength = 4 + random.nextInt(3);
        // randomSectionLength: 4-6
        int randomSectionLength = 4 + random.nextInt(3);
        return randomCDKey(randomKeyLength, randomSectionLength);
    }

    private static final String INVALID_KEY_LENGTH_PARAMETER = "invalid key length parameter, key length parameter must >= 2";
    private static final String INVALID_SECTION_LENGTH_PARAMETER = "invalid section length parameter, section length parameter must >= 3";

    /**
     * 随机{@code CD-KEY},可以指定有多少个节和每个节的长度.
     *
     * @param keyLength CD-KEY有多少个节,长度必须大于{@code 1}
     * @param sectionLength 每个节有多长,长度必须大于{@code 2}
     * @return {@code CD-KEY}
     * @since 1.1
     */
    public static String randomCDKey(int keyLength, int sectionLength){
        if (keyLength <= 1){
            throw new InvalidParameterException(INVALID_KEY_LENGTH_PARAMETER);
        }
        if (sectionLength <= 2){
            throw new InvalidParameterException(INVALID_SECTION_LENGTH_PARAMETER);
        }
        return randomKey(keyLength, sectionLength, true);
    }

    /**
     * 随机{@code CD-KEY},可以指定有多少个节、每个节的长度和是否带{@code -}.
     *
     * @param keyLength 有多少个节
     * @param sectionLength 每个节长度
     * @param withHyphen 是否带{@code -}
     * @return 指定节数，长度的{@code CD-KEY}
     * @since 1.1
     */
    public static String randomKey(int keyLength, int sectionLength, boolean withHyphen){
        Random random = new Random();
        StringBuilder cdk = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            for (int j = 0; j < sectionLength; j++) {
                if (random.nextBoolean()) {
                    int charGapsIndex = random.nextInt(charGaps.length);
                    char randomChar = charGaps[charGapsIndex];
                    cdk.append(randomChar);
                }else{
                    int numGapsIndex = random.nextInt(numGaps.length);
                    char randomNum = numGaps[numGapsIndex];
                    cdk.append(randomNum);
                }
            }
            if (withHyphen) {
                cdk.append("-");
            }
        }
        return cdk.substring(0, cdk.length() - 1);
    }
}
