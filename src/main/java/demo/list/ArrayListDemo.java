package demo.list;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayListDemo {
    private static final  ArrayList<String> stringArrayList = new ArrayList<>();
    private static List<String> randomList(){
        ArrayList<String> arrayList = new ArrayList();
        Random random = new Random();
        int i1 = random.nextInt(5);
        for (int i = 0; i < (3 + i1); i++) {
            arrayList.add(uuid());
        }
        return arrayList;
    }
    private static String uuid(){
        return UUID.randomUUID().toString();
    }
    private static void printList(List<?> list, String listVarName){
        Iterator<?> iterator = list.iterator();
        System.out.print("列表" + listVarName + "内有：[");
        while(iterator.hasNext()){
            Object next = iterator.next();
            System.out.print(next);
            if (iterator.hasNext()){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    private static List<String> list = null;
    /**
     * 添加元素类的方法
     */
    private static void methodsAdd(){

        // 1.添加元素到列表尾部
        System.out.println("stringArrayList添加第一个元素：" + "uuid1");
        boolean add2 = stringArrayList.add("uuid1");
        if (add2) System.out.println("第一个元素添加成功");
        else      System.out.println("第一个元素添加失败");

        System.out.println("stringArrayList添加第二个元素：" + "123");
        boolean add1 = stringArrayList.add("123");
        if (add1)    System.out.println("第二个元素添加成功");
        System.out.println("stringArrayList添加第三个元素：" + "456");
        boolean add = stringArrayList.add("456");
        if (add)     System.out.println("第三个元素添加成功");
        System.out.println("再添加个456");
        stringArrayList.add("456");
        printList(stringArrayList, "stringArrayList");

        // 2.在指定的位置添加元素，默认0就是第一个位置
        System.out.println("在index=3的位置添加元素（第四个元素），默认0就是第一个位置");
        String uuid = uuid();
        stringArrayList.add(3,uuid);
        printList(stringArrayList, "stringArrayList");

        // 3.甚至可以把一个集合拼接在另一个集合的后面
        list = randomList();
        List<String> list1 = randomList();
        boolean b1 = stringArrayList.addAll(list);
        if (b1)      System.out.println("尾部拼接list集合成功");
        printList(stringArrayList, "stringArrayList");

        // 4.甚至可以在一个集合的某个位置拼接另一个集合
        boolean b = stringArrayList.addAll(4, list1);
        if (b)      System.out.println("在第五个元素（index=4）那里开始拼接list1集合成功");
        printList(stringArrayList, "stringArrayList");
        // -------------------------------------------------------
    }
    private static void methodsContains(){
        // 1.判断列表中是否包含"123"元素
        boolean contains = stringArrayList.contains("123");
        System.out.println("判断列表中是否包含123元素：" + contains);
        printList(list, "list");
        // 2.判断stringArrayList列表中是否包含list集合
        boolean b2 = stringArrayList.containsAll(list);
        System.out.println("判断stringArrayList列表中是否包含list列表中的所有元素：" + b2);
    }

    private static void methodsIndex(){
        // 1.获取集合中第3个元素,index默认0开始
        String s = stringArrayList.get(2);
        System.out.println("获取集合中第3个元素:" + s);
        // 2.获取元素456在集合中最早出现的位置(index)
        int i = stringArrayList.indexOf("456");
        System.out.println("获取元素456在集合中最早出现的位置(index):" + i);
        // 获取元素456在集合中最后一次出现的位置(index):
        int i1 = stringArrayList.lastIndexOf("456");
        System.out.println("获取元素456在集合中最后一次出现的位置(index):" + i1);
    }

    private static void methodJudge(){
        // 1.判断list是否为空
        boolean empty = stringArrayList.isEmpty();
        System.out.println("stringArrayList列表是否为空？" + empty);
        // 2.获取list中元素的数量
        int size = stringArrayList.size();
        System.out.println("stringArrayList列表成员数：" + size);
    }
    private static void methodReplace(){
        // 1.替换某个位置的元素，返回替换前的元素
        System.out.println("替换前index=4的字符串为：" + stringArrayList.get(4));
        String itemBefore = stringArrayList.set(4, "4567890-");
        System.out.println("返回值为替换之前的值：" + itemBefore);
        System.out.println("替换后index=4的字符串为：" + stringArrayList.get(4));


        printList(stringArrayList, "替换多个前");
        // 2.替换多个
        stringArrayList.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                if (s.contains("-")){
                    s = s.replace("-", "");
                }
                return s;
            }
        });
        printList(stringArrayList, "替换多个后");

    }

    private static void methodRemove(){
        List<String> list2 = new LinkedList<>(stringArrayList);
        list2.add("4234242424");
        list2.add("42342424242");
        list2.add("423424242423");
        list2.add("4234242424235");
        list2.add("42342424242355555555555555555555555555555555555555555555555555566666666666");
        list2.add("4234242424235666666666666666666666666666666666666666666666666666666666666666");
        list2.add("4234242424235666666666666666666666666666666666666666666666666666666");
        list2.add("423424242423577777777777777777777777777777777777777777777777777777777777777777777");

        List<String> list3 = new LinkedList<>();
        list3.add("4234242424");
        list3.add("42342424242");
        list3.add("423424242423");
        list3.add("4234242424235");
        printList(list2, "list2");
        // 1. 删除元素，返回true、false代表元素的删除成功还是失败。
        System.out.println("删除list2列表中的123元素");
        boolean remove = list2.remove("123");
        System.out.println("list2中的123元素是否已经删除：" + remove);
        printList(list2, "list2");
        // 移除第五个元素，返回该元素的内容
        System.out.println("删除list2列表中的第5个元素");
        String remove1 = list2.remove(4);
        System.out.println("list2列表中的第5个元素已删除,是：" + remove1);
        printList(list2, "list2");
        // 移除list2中和参数中集合元素相同的元素,如果list2元素改变了则返回true
        printList(list3, "list3");
        System.out.println("删除list2列表中的list3中的元素");
        boolean b3 = list2.removeAll(list3);
        System.out.println("list2列表中list3的元素是否删除成功：" + b3);
        printList(list2, "list2");
        // 条件删除，删除字符串元素长度大于30的成员。
        System.out.println("条件删除，删除所有长度大于30的字符串");
        final boolean b = list2.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 30;
            }
        });
        System.out.println("删除成功？" + b);
        printList(list2, "list2");
        // 两个集合的交集，会删除stringArrayList中的元素，直到让stringArrayList等于交集。
        // 如果stringArrayList内有变动则返回true
        System.out.println("两个集合的交集");
        printList(stringArrayList, "stringArrayList");
        boolean b4 = stringArrayList.retainAll(list2);
        System.out.println("交集合并成功：" + b4);
        printList(stringArrayList, "stringArrayList");
    }

    public static void methodSortAndSub(){
        System.out.println("排序前");
        printList(stringArrayList, "stringArrayList");
        // list排序
        stringArrayList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        System.out.println("排序后");
        printList(stringArrayList, "stringArrayList");
        // 截取子列表
        System.out.println("截取第二个元素（包含）到第五个元素（不包含）作为列表，选2、3、4三个index的元素");
        List<String> list4 = stringArrayList.subList(1, 4);
        printList(list4, "list4（截取出来的新列表）");
        System.out.println("删除多余位置");
        // ArrayList专属，缩小预分配的空间
        stringArrayList.trimToSize();
    }
    private static void methodToArray(){
        // 将List转为数组
        Object[] objects = stringArrayList.toArray();
        String[] strings = stringArrayList.toArray(new String[0]);
        System.out.println(Arrays.toString(objects));
        System.out.println(Arrays.toString(strings));
    }
    private static void methodIterator(){
        // 作用相同，但是ListIterator可以向前遍历
        Iterator<String> iterator = stringArrayList.iterator();
        ListIterator<String> stringListIterator = stringArrayList.listIterator();
        // 从第三个元素开始遍历
        ListIterator<String> stringListIterator1 = stringArrayList.listIterator(2);

        // 顺序遍历一遍
        while (stringListIterator.hasNext()){
            String next = stringListIterator.next();
            // 最后一个元素的下标等于list的大小
            int i1 = stringListIterator.nextIndex();
            System.out.print("next：stringArrayList的第" + i1 + "个元素是：" + next);
            System.out.println();
        }
        // 返回再次往前遍历一遍
        while (stringListIterator.hasPrevious()){
            String previous = stringListIterator.previous();
            // 第一个元素的下标是-1
            int i = stringListIterator.previousIndex();
            System.out.print("previous：stringArrayList的第" + i + "个元素是：" + previous);
            System.out.println();
        }

        // 只能顺序遍历
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.print(next);
            if (iterator.hasNext()){
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args) {

        methodsAdd();
        System.out.println();
        methodsContains();
        System.out.println();
        methodsIndex();
        System.out.println();
        methodJudge();
        System.out.println();
        methodReplace();
        System.out.println();
        methodRemove();
        System.out.println();
        methodSortAndSub();
        System.out.println();
        methodToArray();
        System.out.println();
        methodIterator();
    }

}
