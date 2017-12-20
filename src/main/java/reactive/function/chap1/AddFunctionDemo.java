package reactive.function.chap1;

/**
 * Create by psh
 * Date: 2017/11/26
 */
public class AddFunctionDemo {
    public static int addMe(int x, int y) {
        return x + y;
    }

    public static double calculateAverage(int x, int y) {
        return addMe(x, y) / 2.0;
    }

    public static void main(String[] args) {
        AddFunctionDemo.calculateAverage(10,20);
        //函数调用链条，即函数的返回值作为另外函数的输入 ，因为是函数是基于栈的所有没有副作用
        //AddFunctionDemo.calculateAverage(AddFunctionDemo.addMe(10, 20));
    }
}
