package utils;

import org.junit.Assert;
import org.junit.Test;
import utils.testhelp.Person;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/29
 */
public class CopyUtilFunctionTest {
    @Test
    public void testProperty() {
        Person psh = new Person("psh","nan",18);
        Person ps = new Person("1","nan",18);
        Person psh2 = new Person();
        Person psh3 = new Person();
        Person psh4 = new Person();

        CopyUtilFunction.copyIfNotNull(psh::getName,psh2::setName);
        CopyUtilFunction.copyIfNotNull(psh::getName,psh3::setName,String::toUpperCase);
        CopyUtilFunction.copyIfNotNull(ps::getName,psh3::setAge,Integer::parseInt);

        Assert.assertEquals("psh",psh2.getName());
        Assert.assertEquals("PSH",psh3.getName());
        //Assert.assertEquals(1,psh3.getAge());
    }
}
