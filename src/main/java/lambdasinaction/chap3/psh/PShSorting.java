package lambdasinaction.chap3.psh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class PShSorting {

    public static void main(String...args){

        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red")));
        System.out.println(inventory);
        //进行对水果的重量进行排序
        //[排序前Apple{color='green', weight=80}, Apple{color='green', weight=155}, Apple{color='red', weight=120}]
        //排序后[Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        //System.out.println(inventory);
        inventory.sort(new AppleComparator());
        //System.out.println(inventory);

        /**** 2. 使用匿名类进行传递排序方法 *********/
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o2.getColor().compareTo(o1.getColor());
            }
        });

        //System.out.println(inventory);
        /**** 使用匿名类进行传递排序方法 end *********/


        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        


        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        // 3
        // [Apple{color='red', weight=20}, Apple{color='green', weight=30}, Apple{color='green', weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //System.out.println(inventory);
        
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));
        
        // 4
        // [Apple{color='red', weight=10}, Apple{color='red', weight=20}, Apple{color='green', weight=155}]
        inventory.sort(comparing(Apple::getWeight));
        //System.out.println(inventory);

        List<String> names = new ArrayList();
        names.add("lisi");
        names.add("wangwu");
        names.add("b3");
        names.add("pangliu");
        names.add("ali");
        //names.stream().filter(str->str.length()<7).collect(C);
    }



}
