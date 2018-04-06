package threads;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PSH
 * Date: 2018/3/15
 * 仿写ArrayBlocking 练习锁
 */
public class ArrayBlockingQueuePSH<E> extends AbstractQueue<E> implements BlockingDeque<E>, java.io.Serializable {
    private static final long serialVersionUID = -817911632652898426L;

    private final ReentrantLock lock;

    private final Condition empty;

    private final Condition full;

    private final Object[] items;

    private int count , addIndex , removeIndex;

    private static final int DEFAULSIZE = 16;


    ArrayBlockingQueuePSH () {
        this(DEFAULSIZE,false);
    }
    ArrayBlockingQueuePSH (int size) {
        this(size,false);
    }

    ArrayBlockingQueuePSH (int size, boolean fair) {
        lock = new ReentrantLock();
        empty = lock.newCondition();
        full = lock.newCondition();
        items = new Object[size];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public void putFirst(E e) throws InterruptedException {

    }

    @Override
    public void putLast(E e) throws InterruptedException {

    }

    @Override
    public boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E takeFirst() throws InterruptedException {
        return null;
    }

    @Override
    public E takeLast() throws InterruptedException {
        return null;
    }

    @Override
    public E pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public E pollLast(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            if (count == items.length) {
                full.await();
            }
            System.out.println(e);
            final Object[] items = this.items;
            items[addIndex] = e;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            count++ ;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0) {
                empty.await();
            }

            final Object[] item = this.items;
            E e = (E)item[removeIndex] ;
            item[removeIndex] = null;
            if (++removeIndex == item.length) {
                removeIndex = 0;
            }
            count-- ;

            full.signal();
            return e;
        } finally {
            lock.unlock();
        }

    }
    public int getCount () {
        return this.count;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    public static void main(String[] args) {
        ArrayBlockingQueuePSH<String> quence = new ArrayBlockingQueuePSH(18);


        for (int i = 0; i < 500; i++) {
            final int index =i ;
            if (new Random().nextBoolean()) {
                new Thread(() -> {
                    try {
                        quence.put("put： " + Thread.currentThread().getName() + ": " +index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
            } else {
                new Thread(() -> {
                    try {
                        String take = quence.take();
                        System.out.println("take : " + take);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }


        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(quence.size());
    }
}
