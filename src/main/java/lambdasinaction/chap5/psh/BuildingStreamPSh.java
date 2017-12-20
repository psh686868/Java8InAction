package lambdasinaction.chap5.psh;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建流的方式
 * 1. 由值创建流
 * 2. 由数组创建流
 * 3. 由文件生成流
 * 4. 由函数生成流：创建无限流
 */
public class BuildingStreamPSh {
    public static void main(String[] args) {
        //测试1 由值创建流 查看源码底层是接收的一个数组 Arrays.stream(values)
        Stream<String> stringStream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> empty = Stream.empty();

        //Arrays.stream 由数组创建流
        int[] numbers = {2,3,5,7,11,13};
        IntStream stream = Arrays.stream(numbers);
        int sum = stream.sum();
        System.out.println("#########");
        System.out.println(sum);

        //由文件生成流 使用的是nio

        System.out.println("!!!!!!!!!!");
        try {
            Path path = Paths.get(BuildingStreamPSh.class.getClassLoader().getResource("lambdasinaction/chap5/data.txt").getFile());
            Stream<String> lines = Files.lines(path);
            lines.forEach(System.out::println);
            //根据流 1.生成单词流 2. 删除重复项 3. 数一数有多少各不相同的单词
            Files.lines(path).flatMap(line -> Arrays.stream(line.split("")))
                    .distinct()
                    .count();
            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        // 创建无限流
        Optional<Integer> reduce = Stream.iterate(0, n -> n + 2)
                .limit(20)
                .reduce(Integer::sum);
        if(reduce.isPresent()){
            System.out.println(reduce.get());
        }

        //使用generate 创建流 generate和 iterate的区别 请看网页版382页手机版406页

        Stream.generate(Math::random)
                .limit(20)
                .forEach(System.out::println);
    }
}
