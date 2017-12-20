package MasteringLambdasExcepress.chap2;

import java.util.function.IntUnaryOperator;

/**
 * Create by shitou
 * Date: 2017/11/2
 */
public class Foo {
    Object i,j;
    IntUnaryOperator iuo = i -> { int j = 3; return i+j;};
}
