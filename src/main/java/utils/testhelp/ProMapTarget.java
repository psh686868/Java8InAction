package utils.testhelp;

import java.util.List;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/29
 */
public class ProMapTarget {
    private List<String> data1;
    private List<String> data2;
    private List<String> data3;
    private List<String> data4;
    private boolean flag ;
    private Long maxAge;

    public List<String> getData1() {
        return data1;
    }

    public void setData1(List<String> data1) {
        this.data1 = data1;
    }

    public List<String> getData2() {
        return data2;
    }

    public void setData2(List<String> data2) {
        this.data2 = data2;
    }

    public List<String> getData3() {
        return data3;
    }

    public void setData3(List<String> data3) {
        this.data3 = data3;
    }

    public List<String> getData4() {
        return data4;
    }

    public void setData4(List<String> data4) {
        this.data4 = data4;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }
}
