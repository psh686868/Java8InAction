package vm.test;

/**
 * 模拟创建很多线程导致内存溢出异常
 *  进行对上一个JavaVMStackSof的模拟
 *   VM Args: -Xss2M  慢慢变大，因为jvm线程映射操作系统的线程，可能会造成系统的假死的问题
 */
public class JavaVMStackOOM {

    public void dontStop(){
        while(true){

        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        while (true){
            Thread thread = new Thread(()->{
                javaVMStackOOM.dontStop();
            });
            thread.start();
        }
    }
}
