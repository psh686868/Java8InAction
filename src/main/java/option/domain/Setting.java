package option.domain;


/**
 * @author PSH
 * Date: 2017/12/28
 * 配置文件
 */
public class Setting {
    private String author;
    private String name;
    private String value;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Setting() {
    }

    public Setting(String author, String name, String value) {
        this.author = author;
        this.name = name;
        this.value = value;
    }
}
