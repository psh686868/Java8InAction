package lambdasinaction.FunctionInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by psh
 * Date: 2017/11/9
 */
public class Lower2Capital {
    public static void main(String[] args) {
        Map<String, Object> stringObjectMap = Lower2Capital.testException();
        System.out.println(stringObjectMap);
        Lower2Capital lower2Capital = new Lower2Capital();
        String aaa = lower2Capital.testLow2Capical("bbb","aaa", (bb) -> bb.toUpperCase());
        System.out.println(aaa);
    }
     String testLow2Capical (String str2,String str , StringLower2Capital stringLower2Capital) {
        return stringLower2Capital.loew2Captial(str);
     }

    public static Map<String,Object> testException() {
        Map<String ,Object> map = new HashMap<>();
        try {
            throw new RuntimeException("ceshi");
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }
}
