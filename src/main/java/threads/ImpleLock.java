package threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author PSH
 * Date: 2018/4/17
 * 根据 CAS 仿写Lock
 */
public class ImpleLock implements Lock {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void lock() {
        for (;;) {
            if ( atomicInteger.get() == 1) {
                continue;
            } else if (atomicInteger.compareAndSet(0,1)) {
                break;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        atomicInteger.set(0);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
