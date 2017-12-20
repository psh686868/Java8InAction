package common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * java8的一些工具类
 */
public class OptionalUtility {
    private final static Logger logger = LoggerFactory.getLogger(OptionalUtility.class);
    /***
     * 字符串转数字工具类
     *
     */
    public static Optional<Integer> StringToInt(final String s){
        if(StringUtils.isBlank(s)){
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            logger.error("数据转换异常"+e);
           return Optional.empty();
        }
    }
}
