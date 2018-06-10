package utils.testhelp;


import java.util.List;
import lombok.Data;

/**
 * @author PSH
 * Date: 2017/12/23
 */
@Data
public class Person {
    private String name;
    private String sex;
    private Integer age;
    private Integer pageNo;

    private Integer pageSize;

    public int  getStart() {
        return ((pageNo == null ? 1 : pageNo)-1) * (pageSize == null ? 20 : pageSize);
    }

    private List<String> collection;

    public Person() {
    }

    public Person(String name,  int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCollection() {
        return collection;
    }

    public Person setCollection(List<String> collection) {
        this.collection = collection;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", age=" + age +
            ", collection=" + collection +
            '}';
    }
}
