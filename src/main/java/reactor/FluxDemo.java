package reactor;


import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author PSH
 * Date: 2017/12/18
 */
public class FluxDemo {

    public static void main(String[] args) {
        Flux<String> datas = Flux.just("foo", "bar" ,"foobar");
        Flux<String> datasAway = Flux.fromIterable( Arrays.asList("foo", "bar", "foobar"));
        Greeter greeter = new Greeter ();

        //or
    }

    static class Greeter implements Function<String, String> {
        public String apply(String name) {
            return "Hello " + name;
        }
    }
}
