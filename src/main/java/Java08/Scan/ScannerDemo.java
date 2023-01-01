package Java08.Scan;

import java.io.EOFException;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.toString();
        System.out.println(s);

        if (scanner.hasNext()){
            String next = scanner.next();
        }
        if (scanner.hasNextBigDecimal()){
            BigDecimal bigDecimal = scanner.nextBigDecimal();
        }
        if (scanner.hasNextBigInteger()){
            BigInteger bigInteger = scanner.nextBigInteger();
        }
        if (scanner.hasNextBoolean()){
            boolean b = scanner.nextBoolean();
        }
        if (scanner.hasNextByte()){
            byte b = scanner.nextByte();
        }
        if (scanner.hasNextDouble()){
            double v = scanner.nextDouble();
        }
        if (scanner.hasNextFloat()){
            float v = scanner.nextFloat();
        }
        if (scanner.hasNextInt()){
            int i = scanner.nextInt();
        }
        if (scanner.hasNextLong()){
            long l = scanner.nextLong();
        }
        if (scanner.hasNextShort()){
            short i = scanner.nextShort();
        }
        if (scanner.hasNextLine()){
            String s1 = scanner.nextLine();
        }
    }
}
