package vm.test;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

    static class OOMObject{

    }

    /**
     *-Xmx20M -Xms20M  -XX:+HeapDumpOnOutOfMemoryError
     *      (将堆的最小值-Xms参数与最大值-Xmx参数设置为一样即可避免堆自动扩展)
     *
     * -Xmx20M  堆的最大值 JVM最大分配的内存由-Xmx指定，默认是物理内存的1/4
     * -Xms20M JVM 最小分配内存（初始分配内存）由-Xms指定，默认是物理内存的1/64
     * -XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析
     *   此列子的dump文件位置在项目下
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
