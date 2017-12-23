package utils;

import lombok.Builder;
import lombok.Getter;

/**
 * @author PSH
 * Date: 2017/12/23
 */
@Builder
@Getter
public class Person {
    private String name;
    private String sex;
    private int age;
}
