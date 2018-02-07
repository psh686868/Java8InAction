package threads;

public interface ThreadPool<Job extends Runnable> {
    // 执行一个job，这个job需要实现Runnable
    void excutor (Job job);
    // 关闭线程池
    void shutdown();
    // 增加工作者数量
    void addWorks (int num);
    // 减少工作者的数量
    void removeWorkers (int num);
    // 得到还未执行的任务数量
    int jobs ();
}
