package learnnetty.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/27
 */
@Slf4j
public class DiskUtil {
    private static final int DEFAULT_THRESHOLD = 10 * 12024 *1024;
    public static void test () {
        File path = new File(".");
        long usableSpace = path.getUsableSpace();

        if (usableSpace >= DiskUtil.DEFAULT_THRESHOLD) {
           log.info("有多余的磁盘可用");
        } else {
            log.warn("磁盘空间不足，只有:%l",usableSpace);
        }

        log.info("total: %s , free: %s , threshold: %s",path.getTotalSpace(),usableSpace,DEFAULT_THRESHOLD);
    }

    public static void main(String[] args) {
        DiskUtil.test();
    }
}
