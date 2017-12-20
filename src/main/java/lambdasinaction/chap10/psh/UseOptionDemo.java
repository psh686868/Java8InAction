package lambdasinaction.chap10.psh;

import common.OptionalUtility;

import java.util.*;

/***
 * 使用option重构以前的代码来代替null
 *  如果让项目的代码使用option来代替null肯定有很多的东西要做，
 *  比如map 的get("object")可能就是一个null 值，就需要对原来的null进行处理
 *  详见 web 751页
 *
 */
public class UseOptionDemo {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();

        /**
         * 使用option对null的处理
         */
        Optional<Object> value = Optional.ofNullable(map.get("test"));
        System.out.println(value.isPresent()?value.get():value.orElse("psh"));

        /***
         * 使用option 对异常的进行处理
         *  比如我们 使用Integer.parseInt("")进行转换，可能会出现异常，我们可以封装一个 工具类来进行
         *  处理已经封装，
         */


        Optional<Integer> integer = OptionalUtility.StringToInt("3");
        Optional<Integer> hello = OptionalUtility.StringToInt("hello");
        Optional<Integer> hashcod1 = OptionalUtility.StringToInt("");
        Optional<Integer> hashcod2 = OptionalUtility.StringToInt("");


        /**
         * 将Optional的各种方法整合到一起
         *
         */
        Properties properties = new Properties();
        properties.setProperty("a","5");
        properties.setProperty("b","true");
        properties.setProperty("c","-3");

        int a = readDurationBYOption(properties, "d");
        System.out.println("###########");
        System.out.println(a);


    }

    public static int readDuration(final Properties properties,final String name){
        String value = properties.getProperty(name);
        if(value!=null){
            try{
                int i = Integer.parseInt(value);
                if(i>0){
                    return i;
                }
                return 0;
            }catch (NumberFormatException e){
                return 0;
            }

        }
        return 0;
    }

    /***
     * 注意到使用Optional和Stream时的那些通用模式了吗？它们都是对数据库查询过程的反思，
     *    查询时，多种操作会被串接在一起执行
     *
     * @param properties
     * @param name
     * @return
     */
    public static int readDurationBYOption(final Properties properties,final String name){
        Optional<Integer> integer = Optional.ofNullable(properties.getProperty(name))
                .flatMap(OptionalUtility::StringToInt);

        Optional<String> property = Optional.ofNullable(properties.getProperty(name));
        return Optional.ofNullable(properties.getProperty(name))
                 .flatMap(OptionalUtility::StringToInt)
                 .filter(i -> i>0)
                 .orElse(0);
    }

}
