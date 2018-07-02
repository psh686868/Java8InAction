package vm.test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PSH
 * Date: 2018/6/14
 * 堆外内存测试
 * DisableExplicitGC会导致SyStem.gc()不可用
 * -XX:+DisableExplicitGC
 * -XX:MaxDirectMemorySize=40M -verbose:gc -XX:+PrintGCDetails
 */
public class TestDirectByteBuffer {

    public static void main(String args[]) {
        List<ByteBuffer> list = new ArrayList<>();
        List<ByteBuffer> list2 = new ArrayList<>();
        int i = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1 * 1024 * 1024);
//            if (list != null) {
//                list.add(buffer);
//            } else {
//                list2.add(buffer);
//            }
//
//            System.out.println("i = " + i);
//            if (i == 18) {
//                list = null;
//            }

            i++;
            System.out.println("i = " + i);
        }
    }
}
