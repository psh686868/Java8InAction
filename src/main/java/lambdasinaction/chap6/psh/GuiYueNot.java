package lambdasinaction.chap6.psh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GuiYueNot {
    public static void main(String[] args) {
        Stream<Integer> stream =
                Arrays.asList(1, 2, 3, 4, 5, 6)
                        .stream();
        List<Integer> numbers = stream.reduce( new ArrayList<Integer>(),
                    (List<Integer> l, Integer e) -> {
                        l.add(e);
                        return l;
                    },
                    (List<Integer> l1, List<Integer> l2) -> { l1.addAll(l2); return l1; }

        );
        System.out.println(numbers);

        /**
         * ：一个语义问题和一个实际问题。
         * 语义问题在于，
         * reduce方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。
         * 与此相反，collect方法的设计就是要改变容器，从而累积要输出的结果。
         * 这意味着，上面的代reduce方法，因为它在原地改变了作为累加器的List。
         * 你在下一章中会更详细地看到，以错误的语义使用reduce方法还会造成一个实际问题：
         * 这个归约过程不能并行工作，因为由多个线程并发修改同一个数据结构可能会破坏List本身。
         * 在这种情况下，如果你想要线程安全，就需要每次分配一个新的List，
         * 而对象分配又会影响性能。这就是collect方法特别适合表
         * 达可变容器上的归约的原因，更关键的是它适合并行操作，本章后面会谈到这一点。
         */
    }
}
