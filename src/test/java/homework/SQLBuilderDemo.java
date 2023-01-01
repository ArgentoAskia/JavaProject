package homework;

import Java08.homework.SQLBuilder;
import org.junit.Test;

public class SQLBuilderDemo {

//    @Test
//    public void testInsert(){
//        // insert SQL test
//        SQLBuilder personInsertSQL = SQLBuilder.insert().into("person").values("123", "456", "789").build();
//        System.out.println(personInsertSQL);
//        SQLBuilder person = SQLBuilder.insert().into("person").columns("123", "456", "789").values("123", "456", "789").build();
//        System.out.println(person);
//        SQLBuilder person1 = SQLBuilder.insert().into("person").column("123").column("456").values("123", "456").build();
//        System.out.println(person1);
//        SQLBuilder person2 = SQLBuilder.insert().into("person").column("123").columns("345", "678", "3434")
//                .values("1231", "2312", "3131", "2331").build();
//        System.out.println(person2);
//        SQLBuilder person3 = SQLBuilder.insert().into("person").column("123").columns("345", "678", "3434")
//                .values("1231", "2312", "3131", "2331")
//                .values("32323", "2321", "2323232", "333323")
//                .build();
//        System.out.println(person3);
//
//
//        SQLBuilder person4 = SQLBuilder.update("person")
//                .set("123", "123")
//                .set("456", "456")
//                .where("123").equal("456");
//
//        SQLBuilder person5 = SQLBuilder.update("person")
//                .set("123", "123")
//                .set("456", "456")
//                .where("123").equal("456").and("456").large("5677");
//        System.out.println(person4);
//        System.out.println(person5);
//    }
}
