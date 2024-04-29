package com.greenone.smartcalc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestApplication {

    @Test
    public void Test1() {
        NativeLib nativeLib = new NativeLib();
        double result = Double.parseDouble(nativeLib.MainFunRunner("10-7", "0"));
        assertEquals(3, result);
    }
//
//    @Test
//    public void Test2() {
//        NativeLib nativeLib = new NativeLib();
//        double result = Double.parseDouble(nativeLib.MainFunRunner("10-x", "6"));
//        assertEquals(4, result);
//    }
//
//    @Test
//    public void Test3() {
//        NativeLib nativeLib = new NativeLib();
//        double result = Double.parseDouble(nativeLib.MainFunRunner("2^3^2", "0"));
//        assertEquals(512, result);
//    }
//
//    @Test
//    public void Test4() {
//        NativeLib nativeLib = new NativeLib();
//        double result = Double.parseDouble(nativeLib.MainFunRunner("sin(sin(sin(x)))", "1"));
//        double epsilon = 0.0000001;
//        assertEquals(0.6784305, result, epsilon);
//    }
//
//    @Test
//    public void Test5() {
//        NativeLib nativeLib = new NativeLib();
//        String result = nativeLib.MainFunRunner("log(x)", "0");
//        assertEquals("result is INFINITY", result);
//    }
//
//    @Test
//    public void Test6() {
//        NativeLib nativeLib = new NativeLib();
//        double result = Double.parseDouble(nativeLib.MainFunRunner("sin(cos(tan(x)))", "7"));
//        double epsilon = 0.0000001;
//        assertEquals(0.6001744, result, epsilon);
//    }
}
