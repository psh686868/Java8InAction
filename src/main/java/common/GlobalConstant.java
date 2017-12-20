
package common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author psh
 * @date 2017/09/25
 * 全局常量
 *
 */
public class GlobalConstant {

    private GlobalConstant(){

    }

    /**
     * ZERO
     */
    public static final int ZERO = 0;
    
    /**
     * ONE
     */
    public static final int ONE = 1;
    
    /**
     * TWO
     */
    public static final int TWO = 2;
    
    /**
     * THREE
     */
    public static final int THREE = 3;
    
    /**
     * FOUR
     */
    public static final int FOUR = 4;

    /**
     * VR DetailPageUrl
     */
    public static final String VR_DETAIL_PAGE_URL = "https://f2e-assets.souche.com/projects/finance/car-vr/static/viewer/krpano.html?xml=360/index.xml&modelCode=";


    /**
     * 懒加载的方式 ， 当使用的时候创建线程池 ，
     * 线程池 这个默认大小为  cpu核数的2倍,
     *
     */
    public static class GlobalThreadPool {
        private  static  ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        public static ExecutorService getExecutor() {
            return executor;
        }
    }

}
