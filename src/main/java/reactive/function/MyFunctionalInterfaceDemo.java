package reactive.function;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyFunctionalInterfaceDemo {
    public static void main(String[] args) {
        MyFunctionalInterface demo = (x,y) -> { return x+y;};
        BiFunction<Integer,Integer,String>  demoBiFnction = (n1,n2) ->  {
            System.out.println("hello word");
            return "Test Bifunction:" + (n1 + n2);
        };

        String apply = demoBiFnction.apply(10, 20);
        System.out.println(apply);
        System.out.println("-------------------------------------");

        Consumer<Integer> consumer = num -> System.out.println("num: " + num);
        List<Integer> data = Arrays.asList(144, 256, 4, 55);
        printNumber(data,consumer);

        System.out.println("-------------------------------------");
        List<String> fruits = Arrays.asList("apple", "mango",
                "Lichi", "Strawberry");
        display(fruits, (fmt, arg) -> String.format(fmt, arg));
        System.out.println("-------------------------------------");
        List<Student> students=new ArrayList<>();
//        students.add(new Student("A",1,89));
//        students.add(new Student("B",12,80));
//        students.add(new Student("A1",10,67));
//        students.add(new Student("C",7,56));
//        students.add(new Student("A",5,90));

        Consumer<Student> stuConsumer =   System.out::println;
        Consumer<Student> stuConsumer2 =  student -> System.out.println(student);

        students.forEach(  stuConsumer);

        System.out.println("-------------------------------------");

        Function<String,String> fun_to_lower = String::toLowerCase;
//        students.stream()
//                .map(student -> student.getName())
//                .map(fun_to_lower)
//                .forEach(System.out::println);

        System.out.println("-------------------------------------");

        //String s = LazyFunction.matchMe(LazyFunction.evaluateMe("one"), LazyFunction.evaluateMe("two"));
        //System.out.println(s);

        System.out.println(LazyFunction.matchMeLazy(()->LazyFunction.evaluateMe("abcd"),
                ()->LazyFunction.evaluateMe("a")));


        Map<String,Object> map = new HashMap<>();

        map.put("key1","value1");
        Object key1 = map.get("key1");
        System.out.println(key1);

    }

    private static void printNumber(List<Integer> data, Consumer<Integer> consumer) {
        for (Integer val: data) {
            consumer.accept(val);
            //consumer.andThen(num -> System.out.println("and then" + num));
        }
    }

    public static void display(List<String> fruits,
                               DisplayData displayData) {
        for (String item : fruits)
            System.out.println(displayData.write(item+"data:- %s",
                    item));
    }
}
