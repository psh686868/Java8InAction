package utils.testhelp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author PSH
 * Create by psh
 * Date: 2018/1/3
 */
public class TestPropertyMapper{
    private static ProMapTarget target = new ProMapTarget();

    TestPropertyMapper(){

    }


    public static void main(String[] args) {
        TestPropertyMapper.to(target::setData1);
        System.out.println(target);
    }

    public static void to(Consumer<List<String>> consumer) {
        consumer.accept(new ArrayList<>());
    }
}
