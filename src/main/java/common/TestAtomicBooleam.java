package common;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestAtomicBooleam {
    private static final long serialVersionUID = 11111111111111111L;
    //private static final Unsafe  unsafe = Unsafe.getUnsafe();

    private static long valueOffset;



    static {
        try {
            System.out.println("aaa");
            System.out.println("aaa"+valueOffset);

            Field value1 = TestAtomicBooleam.class.getDeclaredField("value");

            //valueOffset = unsafe.objectFieldOffset(TestAtomicBooleam.class.getDeclaredField("valueOffset"));
           // valueOffset = 1L;
           // System.out.println(valueOffset);
        }catch (Exception ex){
            throw new Error(ex);
        }
    }

    private volatile int  value;

    public static void main(String[] args) {
        System.out.println(TestAtomicBooleam.valueOffset);
    }
}
