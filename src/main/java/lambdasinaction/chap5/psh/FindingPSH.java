package lambdasinaction.chap5.psh;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author shitou
 * @date
 * 练习匹配 即match模式
 * 这里将练习 allMatch ， anyMatch , nomeMatch,findFirst 和 findAny
 *
 */
public class FindingPSH {
    public static void main(String[] args) {
        List<Dish> dishes = Dish.menu;

        /********************
         *  anyMatch 方法可以回答 流找那个是否有一个元素能匹配给定的谓词 比如 用它看看菜种是否有 素食可以选择 注意是 是否有一个 集合的概念
         *  而allMatch 则表示的 是否都能匹配  也是集合的概念 ，不过 是 都  即 所有的满足要求才算满足要求     也是java种 && 和 || 运算符
         *  而 noneMAtch 则和allMatch相对 它表示的是 流中 没有任何 原与给定的谓词匹配
         *  而findAny方法将返回当前流中的任意元素。
         * 个人理解类似正则表达式*/
        if(dishes.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("有素食+anyMatch");
        }else {
            System.out.println("无素食+anyMatch");
        }

        if(dishes.stream().allMatch(Dish::isVegetarian)){
            System.out.println("全部都是素食+allMatch");
        }else {
            System.out.println("不全部是素食+allMatch");
        }

        if(dishes.stream().noneMatch(Dish::isVegetarian)){
            System.out.println("全部都不是素食+noneMatch");
        }else {
            System.out.println("至少有一个不是素食+noneMatch");
        }

        Optional<Dish> dish = dishes.stream()
                                .filter(Dish::isVegetarian)
                                .findAny();
        System.out.println(dish);

        System.out.println("#######################");
        dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny()                    //返回一个Optioncal<Dish>
                .ifPresent(d-> System.out.println(d.getName()));     //会在值存在的时候给定代码块

        /**
         * 有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由List或排序好的数据列生成的流）。
         * 对于这种流，你可能想要找到第一个元素。为此有一个findFirst方法，它的工作方式类似于findany
         * 你可能会想，为什么会同时有findFirst和find-Any呢？答案是并行。找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，
         * 请使用find-Any，因为它在使用并行流时限制较少。
         * 如：，给定一个数字列表，下面的代码能找出第一个平方能被3整除的数
         * */

        System.out.println("#######################");
        List<Integer> numberForFind = Arrays.asList(1,2,3,4,5);
        numberForFind.stream()
                .filter(x->(x*x)%3==0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
