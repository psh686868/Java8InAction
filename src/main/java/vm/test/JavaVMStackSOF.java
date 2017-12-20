package vm.test;

public class JavaVMStackSOF {
    private int stackLength = 1;

    /**
     * -Xss160K
     * 使用 使用-Xss参数可以调节栈内存容量。 jvm默认是2GB
     * 心得：
     *  栈空间，加上堆内存大小，再加上最大方法区容量和程序计数器消耗的 等于操作系统分配给jvm的大小，但程序计数器消耗的很少，基本可以忽略不计
     *  所以调节内存大小可以从这几方面入手
     *
     *  引用深入理解jvm 虚拟机web 183页： 针对多线程导致 sof错误
     *  如果使用虚拟机默认参数，栈深度在大多数情况下（因为每个方法压入栈的帧大小并不是一样的，所以只能说在大多数情况下）达到1000～2000完全没有问题，
     *  对于正常的方法调用（包括递归），这个深度应该完全够用了。但是，如果是建立过多线程导致的内存溢出，
     *  在不能减少线程数或者更换64位虚拟机的情况下，就只能通过减少最大堆和减少栈容量来换取更多的线程。如果没有这方面的处理经验，
     *  这种通过“减少内存”的手段来解决内存
     */
    public  void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("!!!!!stack length is :"+javaVMStackSOF.stackLength);
            throw e;
        }

    }
}
