package vm.test;

import java.util.stream.IntStream;

/**
 * @author PSH
 * Date: 2018/4/6
 * 理解    内存逃逸（默认开启），      标量替换（默认开启） ，               线程本地内存（THread Local allaction Buffer）（默认开启）
 * -XX:+DoEscapeAnalysis    -XX:+PrintEliminateAllocations              -XX:-UseTLAB -XX:PrintGC
 */
public class JVMEscape {
    private Student student;
    static class Student {
        private int age;
        private String name;

        Student(int age ,  String name) {
            this.age = age;
            this.name = name;
        }



    }
    public void allacMem () {
        new Student(12,"test");
    }

    public Student allacMem (int age , String name) {
        return new Student(age,name);
    }

    public static void main(String[] args) {
        long l1 = System.nanoTime();
        JVMEscape jvmEscape = new JVMEscape();

        for (int i = 0; i < 1000000; i++) {
//            jvmEscape.allacMem();
            //jvmEscape.allacMem(i, "name");
        }
        long l2 = System.nanoTime();
        Long time = l2 - l1;
        System.out.println(" userTime-" + time.toString().length() + ": " + time );
    }
}
