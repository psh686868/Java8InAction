package lambdasinaction.chap5.psh;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shitou
 * 这一章练习Stream中的所有操作
 * http://ifeve.com/stream/ 这个网址解释的很好
 *
 */
public class PracticePSh {
    public static void main(String[] args) {
        //匹配所有是蔬菜的美食
        List<Dish> dishList = Dish.menu;

        dishList.stream().filter(Dish::isVegetarian)
                .collect(Collectors.toList()).forEach(System.out::println);
        /***########################流还支持distinct limit sort, 类比数据库的sql 语句 它是根据 hashcode 和equalse方法实现的 测试如下 ########################################*/

        List<Integer> numbers = Arrays.asList(1,3,1,4,7,9,3,5,6);
       /* numbers.stream().filter(number -> number > 2)
                .distinct()
                .sorted()
                .limit(5)
                .forEach(System.out::println);*/
        /***########################end ########################################*/

        /***#########流还支持skip 返回一个扔掉了前n个元素的流。如果流中的元素不足n个，则返回一个空流，请注意：limit（n）和skip(n)是互补的 如下面 number > 5 得到 7，9，6 limit（2）7，9 skip(2) 6 两个加起来是一个集合 ##################*/
        System.out.println("######################################");
        List<Integer> numbers2 = Arrays.asList(1,3,1,4,7,9,3,5,6);
        numbers2.stream().filter(number -> number > 5)
                .limit(2)
                .forEach(System.out::println);
        /***########################end ########################################*/

        /***######################## map 和 flatMap 一个非常场景件的数据处理套路就是从某些对象中选择信息，比如在sql里，你可以从表中选择一列，Stream API 也通过map和flatMap方法提供类似的工具
         * Map ：： 流支持map方法，它会接受一个函数作为参数，这个函数被应用到每个元素上，并将其映射城一个新的原色，但其中的席位差别在于他是创建一个新版本，而不是去修改。
         * ########################################*/
        System.out.println("######################################");
        dishList.stream().map(dish -> dish.getName())
                .forEach(System.out::println);
        //给定一个单词列表,你想要返回另一个列表 显示每个 单词中有几个字母
        System.out.println("######################################");
        List<String> words = Arrays.asList("java 8","Lambdas","In","Action");
        words.stream().map(String::length)
                .forEach(System.out::println);

        /*****************************流的扁平化 如：上面的列子 对于一个单词列表，如何返回一张列表，列出里面各不相同的字符 */
        //给定单词列表 ["Hello","Word"] 返回单词列表["H","e","l","l","o","W","o","r","d"]

        System.out.println("######################################");
        List<String> helloWord = Arrays.asList("Hello","Word");
        helloWord.stream().map(word -> word.split(""))
                .distinct()
                .forEach(System.out::println);
         /**这个方法的问题在于，传递给map方法的lambda为每个单词返回一个String[]的，map 返回的流实际上是Stream<String[]>类型的，你真正想要的是用 Stream<String[]> 来表示一个字符流 是有意上面打印的是对象的hash码 */

        System.out.println("######################################");

        //Arrays.Stream的方法可以接受一个数组 并产生一个 流 但这是一个流的列表 但是flatMap可以映射流的内容

        helloWord.stream().map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        /*****flatMap的方法让你把一个流中的每个值都换另一个流 ，然后把所有的流链接起来成为一个流  个人理解 双重循环时让里面的循环 返回意义流，然后 flatMap处理流 */
        System.out.println("######################################");
        helloWord.stream().map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        System.out.println("#################### 1 ##################");
        //练习 1. 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢 列如：给定 [1,2,3,4,5],应该返回[1,4,9,16,25]
        //    2.给定 两个数字列表 如何返回所有的数对呢 ？ 列如 给定 列表[1,2,3]和列表[3,4]应该返回 [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
        //    3. 将练习2 返回一个 总和能被3整除的数对   如: 只返回 (2,4),(3,3)
        List<Integer> numbersPractice = Arrays.asList(1,2,3,4,5);
        numbersPractice.stream().map(number->number*number)
                .sorted()
                .forEach(System.out::println);
        System.out.println("#################### 2 ##################");
        List<Integer> numbersX = Arrays.asList(1,2,3);
        List<Integer> numbersY = Arrays.asList(3,4);
        List<int[]> collect = numbersX.stream()
                .flatMap(i -> numbersY.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println(collect);                       // size 为6  即数组类型

        List<Stream<int[]>> collect2 = numbersX.stream()
                .map(i -> numbersY.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println(collect2);        // size 为3 ，为流类型

        System.out.println("#################### 2 ##################");
        List<int[]> collect3 = numbersX.stream().flatMap(i -> numbersY.stream()
                .filter(j -> (j + i) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println(collect3);



    }
}
