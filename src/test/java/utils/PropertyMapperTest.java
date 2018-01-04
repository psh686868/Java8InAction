package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.testhelp.ProMapTarget;
import utils.testhelp.ProMapSource;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/29
 */
public class PropertyMapperTest {
    private ProMapSource proMapSource = new ProMapSource();

    private ProMapTarget target = new ProMapTarget();

    private PropertyMapper map = PropertyMapper.get();

    @Before
    public void before () {

    }

    @Test
    public void testPropertyFromTo() {

        map.from(proMapSource::getAllowedOrigins).to(target::setData1);

        Assert.assertNotNull(target.getData1());

    }


    @Test
    public void testPropertyWhenNot() {

        map.from(proMapSource::getAllowedHeaders).whenNot(CollectionUtils::isEmpty)
                .to(target::setData2);

        Assert.assertNotNull(target.getData2());

    }

    @Test
    public void testPropertyWhenNonNull() {
        map.from(proMapSource::getAllowCredentials).whenNonNull()
                .to(target::setFlag);
        Assert.assertNotNull(target.getData4());
        Assert.assertEquals(true,target.isFlag());

    }

    @Test
    public void testPropertyWhenNonNullAs() {

        map.from(proMapSource::getMaxAge).whenNonNull().as(Duration::getSeconds)
                .to(target::setMaxAge);

        Assert.assertEquals(new Long(1800),target.getMaxAge());

    }


}
