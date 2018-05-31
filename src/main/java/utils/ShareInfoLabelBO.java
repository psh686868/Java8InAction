package utils;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShareInfoLabelBO implements Serializable {
    private static final String DEFAULT_TYPE = "brandCode";
    /**
     * 品牌code
     */
    private String code;

    /**
     * 品牌名字
     */
    private String name;

    private String type = DEFAULT_TYPE;

    private int count;

}