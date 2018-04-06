package vm.test;

import java.io.IOException;

/**
 * @author PSH
 * Date: 2017/12/20
 *
 * user : -server -Xmx20m -Xms20m -Xmn10m -Xss256k -XX:+UseParNewGC    -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly  -XX:CMSInitiatingOccupancyFraction=75 -XX:+PrintGCDetails  -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
 *  -server 服务器模式
 *
 *  -Xmx4G   服务器允许的开启的最大堆内存
 *  -Xms4G  jvm初始分配的堆内存一般和Xmx 配置成一样，以避免每次GC后jvm重新分配内存
 *
 *  -Xmn   年轻代内存大小，
 *
 *  -Xss256k 每个线程的栈的大小
 *
 *  -XX:UseParNewGC   -XX:+UseParNewGC -XX:+UseConcMarkSweepGC这两个参数。这指定了在年轻代使用parallel new收集器，
 *  -XX:+UseConcMarkSweepGC 开启 并发标记清除（CMS）收集器
 *  -XX:XX:CMSInitiatingOccupancyFraction=75  使用cms作为垃圾收回使用75%后开始cms收集
 *
 *  -XX:+PrintGCDetails  开启详细GC日志模式，这种模式下，日志格式和使用的GC算法有关
 *  -XX:+PrintGCDateStamps  将每一行就添加上了绝对的日期和时间。 2014-01-03T12:08:38.102-0100: [GC 66048K->53077K(251392K), 0.0959470 secs] \n 2014-01-03T12:08:38.239-0100: [GC 119125K->114661K(317440K), 0.1421720 secs]
 *  -XX:+PrintHeapAtGC   PrintHeapAtGC参数，则HotSpot在GC前后都会将GC堆的概要状况输出到log中
 *
 *  -XX:+UseCMSInitiatingOccupancyOnly
 *
 *
 *  我们用-XX+UseCMSInitiatingOccupancyOnly标志来命令JVM不基于运行时收集的数据来启动CMS垃圾收集周期。
 *  而是，当该标志被开启时，JVM通过CMSInitiatingOccupancyFraction的值进行每一次CMS收集，而不仅仅是第一次。然而，请记住大多数情况下，
 *  JVM比我们自己能作出更好的垃圾收集决策。因此，只有当我们充足的理由(比如测试)并且对应用程序产生的对象的生命周期有深刻的认知时，才应该使用该标志。
 *
 */
public class CMSJVMDemo {
    private static final int _1MB = 1024 *1024;
    private static  byte [] b2 = new byte[4 * _1MB];
    public static void main(String[] args) {
        byte [] b1 = new byte[2 * _1MB];
        byte [] b2 = new byte[2 * _1MB];
        byte [] b3 = new byte[2 * _1MB];
        byte [] b4 = new byte[4 * _1MB];

//        byte [] b5 = new byte[2 * _1MB];
//        byte [] b6 = new byte[1 * _1MB];

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
