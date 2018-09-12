package threads;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.Assert;
import utils.CollectionUtils;
import utils.PropertyMapper;

/**
 * @author PSH
 * Date: 2018/9/6
 */
public class TaskSchedulerBuilder {

    private final Integer poolSize;

    private final String threadNamePrefix;

    private final Set<TaskSchedulerCustomizer> customizers;

    public TaskSchedulerBuilder() {
        this.poolSize = null;
        this.threadNamePrefix = null;
        this.customizers = null;
    }

    public TaskSchedulerBuilder(Integer poolSize, String threadNamePrefix,
        Set<TaskSchedulerCustomizer> taskSchedulerCustomizers) {
        this.poolSize = poolSize;
        this.threadNamePrefix = threadNamePrefix;
        this.customizers = taskSchedulerCustomizers;
    }

    /**
     * Set the maximum allowed number of threads.
     * @param poolSize the pool size to set
     * @return a new builder instance
     */
    public TaskSchedulerBuilder poolSize(int poolSize) {
        return new TaskSchedulerBuilder(poolSize, this.threadNamePrefix,
            this.customizers);
    }

    /**
     * Set the prefix to use for the names of newly created threads.
     * @param threadNamePrefix the thread name prefix to set
     * @return a new builder instance
     */
    public TaskSchedulerBuilder threadNamePrefix(String threadNamePrefix) {
        return new TaskSchedulerBuilder(this.poolSize, threadNamePrefix,
            this.customizers);
    }


    public TaskSchedulerBuilder customizers(TaskSchedulerCustomizer... customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        return customizers(Arrays.asList(customizers));
    }

    public TaskSchedulerBuilder customizers(
        Iterable<TaskSchedulerCustomizer> customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        return new TaskSchedulerBuilder(this.poolSize, this.threadNamePrefix,
            append(null, customizers));
    }


    public TaskSchedulerBuilder additionalCustomizers(
        TaskSchedulerCustomizer... customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        return additionalCustomizers(Arrays.asList(customizers));
    }


    public TaskSchedulerBuilder additionalCustomizers(
        Iterable<TaskSchedulerCustomizer> customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        return new TaskSchedulerBuilder(this.poolSize, this.threadNamePrefix,
            append(this.customizers, customizers));
    }


    public ThreadPoolTaskScheduler build() {
        return configure(new ThreadPoolTaskScheduler());
    }


    public <T extends ThreadPoolTaskScheduler> T configure(T taskScheduler) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(this.poolSize).to(taskScheduler::setPoolSize);
        map.from(this.threadNamePrefix).to(taskScheduler::setThreadNamePrefix);
        if (!CollectionUtils.isEmpty(this.customizers)) {
            this.customizers.forEach((customizer) -> customizer.customize(taskScheduler));
        }
        return taskScheduler;
    }

    private <T> Set<T> append(Set<T> set, Iterable<? extends T> additions) {
        Set<T> result = new LinkedHashSet<>((set != null) ? set : Collections.emptySet());
        additions.forEach(result::add);
        return Collections.unmodifiableSet(result);
    }

}