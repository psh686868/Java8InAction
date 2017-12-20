package reactive.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Create by psh
 * Date: 2017/11/23
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private int rollNo;
    private int total_marks;
}

