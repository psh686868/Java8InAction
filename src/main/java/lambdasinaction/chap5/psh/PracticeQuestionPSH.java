package lambdasinaction.chap5.psh;

import lambdasinaction.chap5.Trader;
import lambdasinaction.chap5.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeQuestionPSH {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        //Trader test = new Trader();//加一个测试的

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
                //new Transaction(test, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        //找到 2011 年所有的交易 并按交易额排序 （从低到高）

        transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparing(transaction -> transaction.getValue()))
                .collect(Collectors.toList());


        // Query 2: What are all the unique cities where the traders work?
        // 交易员都在哪些城市工作过 忘记使用 distink 这样会发生重复
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        //  Query 3: Find all traders from Cambridge and sort them by name.
        //  找到从Cambridge来的所有 交易者，并按照他们的名字进行排序

       /* transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getTrader().getName())
                .sorted(name->name.compareTo());*/
        System.out.println("######");
        List<Trader> collect = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(trader -> trader.getName()))
                .collect(Collectors.toList());
        System.out.println(collect);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        // 返回所有交易员的姓名的字符串，按字母顺序排序
        /*******此解决方案效率不高，所有字符串都被反复链接，每次迭代的时候都要建立一个新的String对象）。下一章，你将看到更有效
         *     的解决方案，她使用joining ，其内部使用的是StringBuilder
         *
         * */

        System.out.println("######");
        String str = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((a, b) -> a + b)
                .get();
        System.out.println(str);

        // Query 5: Are there any trader based in Milan?
        // 代码清单5-5　有没有交易员是在米兰工作的
        transactions.stream()
                .filter(transaction -> "Milan".equals(transaction.getTrader().getCity()))
                .count();//我写的 可以直接用anyMatch
        boolean isExit = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println("######");
        System.out.println(isExit);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        // 代码清单5-6　打印生活在剑桥的交易员的所有交易额
        Optional<Integer> reduce = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getValue())
                .reduce((a, b) -> a + b);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
       System.out.println(reduce.get());
       //Query7 找到交易额最小的交易
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(transaction -> transaction.getValue()));
        Optional<Transaction> reduce1 = transactions.stream()

                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(min.get());
        System.out.println(reduce1.get());

        // Query 8: Update all transactions so that the traders from Milan are set to Cambridge.
        // 更新 所有来自Milan的交易员 变为  Cambridge
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(transactions);
        transactions.stream()
                .filter(transaction -> "Milan".equals(transaction.getTrader().getCity()))
                .forEach(transaction -> transaction.getTrader().setCity("Cambridge"));
        System.out.println(transactions);

        // Query 7: What's the highest value in all the transactions?
        // 最高的交易额是多少
        Integer reduce2 = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::max);



    }
}
