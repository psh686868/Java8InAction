package threads;

import java.util.ArrayList;
import java.util.List;
import utils.testhelp.Person;

/**
 * @author PSH
 * Date: 2018/6/9
 * 使用多线程按顺序打印 一组数据，-》 数据库迁移
 */
public class UseJoinPrint {
   private static final List<Person> data = new ArrayList<>(60);

   static {
       data.add(new Person("nema1", 11));
       data.add(new Person("nema2", 11));
       data.add(new Person("nema3", 11));
       data.add(new Person("nema4", 11));
       data.add(new Person("nema5", 11));
       data.add(new Person("nema6", 11));
       data.add(new Person("nema7", 11));
       data.add(new Person("nema8", 11));
       data.add(new Person("nema9", 11));
       data.add(new Person("nema10", 11));
       data.add(new Person("nema11", 11));
       data.add(new Person("nema12", 11));
       data.add(new Person("nema13", 11));
       data.add(new Person("nema14", 11));
       data.add(new Person("nema15", 11));
       data.add(new Person("nema16", 11));
       data.add(new Person("nema18", 11));
       data.add(new Person("nema19", 11));
       data.add(new Person("nema20", 11));
       data.add(new Person("nema21", 11));
       data.add(new Person("nema22", 11));
       data.add(new Person("nema23", 11));
       data.add(new Person("nema24", 11));
       data.add(new Person("nema25", 11));
       data.add(new Person("nema26", 11));
       data.add(new Person("nema27", 11));
       data.add(new Person("nema28", 11));
       data.add(new Person("nema29", 11));
       data.add(new Person("nema30", 11));
       data.add(new Person("nema31", 11));
       data.add(new Person("nema32", 11));
       data.add(new Person("nema33", 11));
       data.add(new Person("nema34", 11));
       data.add(new Person("nema35", 11));
       data.add(new Person("nema36", 11));
       data.add(new Person("nema37", 11));
       data.add(new Person("nema38", 11));
       data.add(new Person("nema39", 11));
       data.add(new Person("nema40", 11));
   }

    public static void main(String[] args) {
        try {
            Thread.sleep(1000 * 40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        for (int i = 0; i < 1; i++) {
            final int index = i + 1;
            DataDealthread dataDealthread = new DataDealthread(thread, () -> {
                //System.out.println("Thread = " + Thread.currentThread());
                Person person1 = data.get(index);
                final int incread = index + 1;
                Person person2 = data.get(incread);
                System.out.println("person" + index + "= " + person1 + "; person" + incread + "= " + person2);
            });
            thread = dataDealthread;
            dataDealthread.start();
        }
    }

}
class DataDealthread extends Thread {
    final Thread preThrad;
    final Runnable target;

    DataDealthread(Thread preThradt, Runnable runnable) {
        preThrad = preThradt;
        target = runnable;
    }

    @Override
    public void run(){
        try {
            preThrad.join();
            if (target != null) {
                target.run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
