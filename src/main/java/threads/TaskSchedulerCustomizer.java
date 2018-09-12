package threads;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author PSH
 * Date: 2018/9/6
 */

@FunctionalInterface
public interface TaskSchedulerCustomizer {
    void customize(ThreadPoolTaskScheduler taskScheduler);
}
