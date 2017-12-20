package lambdasinaction.chap3.psh;

import org.apache.commons.lang3.StringUtils;

/***
 * 复合函数
 * 比如 funtion<Integer,Integer> f=x-> x+1
 *      funtion<Integer,Integer> g=x->x*2
 *     funtion<Integer,Integer>  h=f.andthen(g)
 *      int result = h.apply(1) 则 result=4
 *      类比数学函数
 *
 *      应用场景 对一封信最文本转换，比如在一封信上添加 信头和 信尾
 *
 *
 *
 */
public class RecombinationFunction {
    public static String addHead(String content) {
        if(StringUtils.isBlank(content)){
            return "";
        }
        return "this is add Letter heater"+content;
    }

    public static String addFoot(String content) {
        if(StringUtils.isBlank(content)){
            return "";
        }
        return content+"!!! this is add foot";
    }

    public static String checkContent(String content) {
        if(StringUtils.isBlank(content)){
            return "";
        }
        return content.replaceAll("goog","bad");
    }

    public static void main(String[] args) {
        String letterContent = "这是一封信，里面有很多good 的东西";

    }
}
