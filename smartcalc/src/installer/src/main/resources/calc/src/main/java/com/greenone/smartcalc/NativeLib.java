package com.greenone.smartcalc;


public class NativeLib {
    static {
        String jarPath = SmartCalcApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String libPath;
        boolean contains = jarPath.contains("classes");
        if (contains) {
            libPath = jarPath.substring(0, jarPath.lastIndexOf("/classes")) + "/classes/lib/";
        } else {
            libPath = jarPath.substring(0, jarPath.lastIndexOf("/")) + "/classes/lib/";
        }
        System.load(libPath + "native_lib.so");
    }

    public native String MainFunRunner(String input, String x);

    public native int FieldValidatorIntDouble(String x);
}
