package thread.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/***
 * 需求： 计算1+2+3+4的结果
 *     使用fork/join进行任务的切分，然后再合并任务
 *     RecursiveTask用于有返回结果的任务
 *     RecursiveAction 用于没有返回结果的任务
 *
 *
 */
public class CountTaskUserForkJoin extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        return null;
    }
}
