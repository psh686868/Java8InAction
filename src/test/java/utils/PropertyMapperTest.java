package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.testhelp.ProMapTarget;
import utils.testhelp.ProMapSource;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/29
 */
public class PropertyMapperTest {
    private ProMapSource proMapSource = new ProMapSource();

    private ProMapTarget target = new ProMapTarget();

    private PropertyMapper map = PropertyMapper.get();
    private static PropertyMapper smap = PropertyMapper.get();

    @Before
    public void before () {

    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

        map.from(()->{
            if (true) {
                return null;
            } else {
                return true;
            }
        })
                .to(target::setFlag);

        Assert.assertEquals(new Long(1800),target.getMaxAge());

    }

    @Test
    public void fromWhenSupplierIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Supplier must not be null");
        this.map.from(null);
    }

    @Test
    public void toWhenConsumerIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Consumer must not be null");
        this.map.from(() -> "").to(null);
    }

    @Test
    public void toShouldMapFromSupplier() {
        ExampleSource source = new ExampleSource("test");
        ExampleDest dest = new ExampleDest();
        this.map.from(source::getName).to(dest::setName);
        Assert.assertEquals(dest.getName(),"test");
    }

    @Test
    public void asIntShouldAdaptSupplier() {
        Integer result = this.map.from(() -> "123").asInt(Long::valueOf)
                .toInstance(Integer::new);
        //Assert.assertEquals(result).isEqualTo(123);
    }

    @Test
    public void asWhenAdapterIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Adapter must not be null");
        this.map.from(() -> "").as(null);
    }

    @Test
    public void asShouldAdaptSupplier() {
        ExampleDest dest = new ExampleDest();
        this.map.from(() -> 123).as(String::valueOf).to(dest::setName);
        Assert.assertEquals(dest.getName(),"123");
    }

    @Test
    public void whenNonNullWhenSuppliedNullShouldNotMap() {
        this.map.from(() -> null).whenNonNull().as(String::valueOf).toCall(Assert::fail);
    }

    @Test
    public void whenNonNullWhenSuppliedThrowsNullPointerExceptionShouldNotMap() {
        this.map.from(() -> {
            System.out.println(Thread.currentThread().getName() + "; from");return 1;
        }).whenNonNull().as(String::valueOf).toCall(()->{
            System.out.println(Thread.currentThread().getName() + "; toCall");
            System.out.println(1);
        });
    }


    @Test
    public void whenTrueWhenValueIsTrueShouldMap() {
        Boolean result = this.map.from(() -> true).whenTrue().toInstance(Boolean::new);
        //assertThat(result).isTrue();
    }

    @Test
    public void whenTrueWhenValueIsFalseShouldNotMap() {
        this.map.from(() -> false).whenTrue().toCall(Assert::fail);
    }

    @Test
    public void whenFalseWhenValueIsFalseShouldMap() {
        Boolean result = this.map.from(() -> false).whenFalse().toInstance(Boolean::new);
        //assertThat(result).isFalse();
    }

    @Test
    public void whenFalseWhenValueIsTrueShouldNotMap() {
        this.map.from(() -> true).whenFalse().toCall(Assert::fail);
    }

    @Test
    public void whenFlagWhenValueIsTrueShouldNotMap() {
        this.map.from(() -> true).when(Boolean.TRUE::equals).toCall(Assert::fail);
    }

    @Test
    public void whenHasTextWhenValueIsNullShouldNotMap() {
        //this.map.from(() -> null).whenHasText().toCall(Assert::fail);
    }

    @Test
    public void whenHasTextWhenValueIsEmptyShouldNotMap() {
        //this.map.from(() -> "").whenHasText().toCall(Assert::fail);
    }

    @Test
    public void whenHasTextWhenValueHasTextShouldMap() {
        //Integer result = this.map.from(() -> 123).whenHasText().toInstance(Integer::new);
       // assertThat(result).isEqualTo(123);
    }

    @Test
    public void whenEqualToWhenValueIsEqualShouldMatch() {
        String result = this.map.from(() -> "123").whenEqualTo("123")
                .toInstance(String::new);
        //assertThat(result).isEqualTo("123");
    }

    @Test
    public void whenEqualToWhenValueIsNotEqualShouldNotMatch() {
        this.map.from(() -> "123").whenEqualTo("321").toCall(Assert::fail);
    }

    @Test
    public void whenWhenValueMatchesShouldMap() {
        String result = this.map.from(() -> "123").when("123"::equals)
                .toInstance(String::new);
        //assertThat(result).isEqualTo("123");
    }

    @Test
    public void whenWhenValueDoesNotMatchShouldNotMap() {
        this.map.from(() -> "123").when("321"::equals).toCall(Assert::fail);
    }

    @Test
    public void whenWhenCombinedWithAsUsesSourceValue() {
        Count<String> source = new Count<>(() -> "123");
        Long result = this.map.from(source).when("123"::equals).as(Integer::valueOf)
                .when((v) -> v == 123).as(Integer::longValue).toInstance(Long::new);
        //assertThat(result).isEqualTo(123);
        //assertThat(source.getCount()).isOne();
    }

    @Test
    public void alwaysApplyingWhenNonNullShouldAlwaysApplyNonNullToSource() {
        this.map.alwaysApplyingWhenNonNull().from(() -> null).toCall(Assert::fail);
    }

    private static class Count<T> implements Supplier<T> {

        private final Supplier<T> source;

        private int count;

        Count(Supplier<T> source) {
            this.source = source;
        }

        @Override
        public T get() {
            this.count++;
            return this.source.get();
        }

        public int getCount() {
            return this.count;
        }

    }

    private static class ExampleSource {

        private final String name;

        ExampleSource(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

    }

    private static class ExampleDest {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

    }


}
