package lambdasinaction.chap2.predicatepsh;


/**
 * 谓词：一个返回boolean值得函数
 * 比如要选择 重量，颜色等筛选苹果，运用策略模式
 *  行为参数化：让方法接受行为(或者战略）作为参数，并在内部使用，来完成不同的行为
 *  详见118页
 */
public interface PredicateGener<T> {
    boolean test(T apple);
}
