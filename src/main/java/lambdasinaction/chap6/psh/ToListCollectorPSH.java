package lambdasinaction.chap6.psh;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * 现在我们可以一个个来分析Collector接口声明的五个方法了。
 * 通过分析，你会注意到，前四个方法都会返回一个会被collect方法调用的函数,
 * 而第五个方法characteristics则提供了一系列特征，
 * 也就是一个提示列表，告诉collect方法在执行归约操作的时候可以应用哪些优化
 *
 * 我们也可以实现它的接口
 *
 * 你可以把它们都融合起来
 * @param <T>
 */
public class ToListCollectorPSH<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 创建集合操作的起始点
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {

        return ArrayList::new;
    }

    /***
     * 累积遍历过的项目，原位修改累加器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }


    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            //修改第一个累加器，将其与第二个累加器的内容合并
            list1.addAll(list2);
            //返回修改后的第一个累加器
            return list1;
        };
    }

    /***
     * 恒等函数
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 为收集器添加IDENTITY_FINISH和CONCURRENT标志
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }

    public static void main(String[] args) {
        String sd = "9166.7";

        Double v = Double.parseDouble(sd);
        double mm = v / 1000;
        double m = mm / 60;
        
        double s = mm % 60;

        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String hms = formatter.format(v);

       double days    = v / 1000 / 60 / 60 / 24;
        double floor = Math.floor(days);
        System.out.println(floor);
        // var hours    = shijiancha/ 1000 / 60 / 60 - (24 * daysRound);
       // var hoursRound   = Math.floor(hours);
       // var minutes   = v / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
       // var minutesRound  = Math.floor(minutes);
       // var seconds   = shijiancha/ 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);

        System.out.println(m+"'"+s);
    }
}
