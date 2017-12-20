package common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class B {
    public static void main(String args[]) throws Throwable {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass",
                new Class[]{String.class, byte[].class, int.class, int.class});
        defineClass.setAccessible(true);
        System.out.println(B.class.getName());
        File file = new File(B.class.getClassLoader().getResource("common/B.class").getFile());
        byte[] bcs = new byte[(int) file.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            while ((in.read(bcs)) != -1) {
            }
        } catch (Exception e) {

        } finally {
            System.out.println("aaaaaaaa");
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        while (true) {
            try {
                Object invoke = defineClass.invoke(B.class.getClassLoader(), new Object[]{"BBBB", bcs, 0, bcs.length});
                System.out.println(invoke);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }
}
