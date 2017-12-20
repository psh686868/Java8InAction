package vm.test;

/**
 * Create by psh
 * Date: 2017/11/26
 */
public class CmsGcTest {

    private static final int _1M = 1*1024*1024;
    private static final int _8M = 8*1024*1024;

    public static void main(String[] args) {

        // 预期YoungGC的次数
        int ygcTime = 3;

        for (int i=0; i<ygcTime; i++){
            // 由于Eden区设置为8M, 所以分配8个1M就会导致一次YoungGC
            for(int j=0; j<8; j++){
                byte[] tmp = new byte[_1M];
            }
        }

        for(int j=0; j<3; j++) {
            // 对象超过了Eden区, 所以直接在Old区分配;
            byte[] tmp = new byte[_8M];
        }

        try {
            // sleep一段时间是为了让CMS GC线程能够有足够的时间检测到Old区达到了触发CMS GC的条件，CMS GC线程默认2s扫描一次，可以通过参数CMSWaitDuration配置，例如-XX:CMSWaitDuration=3000
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
