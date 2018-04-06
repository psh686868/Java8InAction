package vm.test;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Date: 2018/2/7
 * String.intern() 导致的YGC不断变长
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xmx2G -Xms2G -Xmn100M
 */
public class StringTableTest {
    public static void main(String[] args) {
        IntStream.range(0,1000000).forEach((i) -> {
            createUUID();
            if (i == 100000) {
                System.gc();
            }
        });
    }

    private static String createUUID() {
        return UUID.randomUUID().toString().intern();
    }
}
