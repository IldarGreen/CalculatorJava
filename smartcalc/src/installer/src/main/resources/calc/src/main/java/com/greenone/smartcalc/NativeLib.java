package com.greenone.smartcalc;

public class NativeLib {
    static {
//        String currentDirectory = System.getProperty("user.dir");
//        System.load(currentDirectory + "/src/main/resources/lib/native_lib.so");

        /////////////////////////////////////////////////1
//        String jarPath = SmartCalcApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//        String libPath = jarPath.substring(0, jarPath.lastIndexOf("/")) + "/classes/lib/";
////        String libPath = jarPath.substring(0, jarPath.lastIndexOf("/")) + "/lib/";
//        System.load(libPath + "native_lib.so");

        /////////////////////////////////////////////////2
        String jarPath = SmartCalcApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        boolean contains = jarPath.contains("classes");
        if (contains) {
            System.out.println("classes __________________________________-------------------");
            System.out.println("classes __________________________________-------------------");
            System.out.println("classes __________________________________-------------------");
            System.out.println("classes __________________________________-------------------");
        }
        String libPath;
        if (contains) {
            libPath = jarPath.substring(0, jarPath.lastIndexOf("/classes")) + "/classes/lib/";
        } else {
            libPath = jarPath.substring(0, jarPath.lastIndexOf("/")) + "/classes/lib/";
        }
        System.load(libPath + "native_lib.so");
    }

    public native String MainFunRunner(String input, String x);

//    public native double Graph(String str);

    public native int FieldValidatorIntDouble(String x);
}


// g++ -c -Wall -Werror -Wextra -std=c++17 -fpic model.cc
// g++ -shared -o native_lib.so model.o

//javac -h . NativeLib.java

//g++ -shared -fPIC -o libMyLibrary.so MyLibrary.cpp
//g++ -shared -fPIC -o native_lib.so model.cc native_lib.cpp


//g++ -I/Users/vanesabo/Library/Java/JavaVirtualMachines/openjdk-22.0.1/Contents/Home/include/ -I/Users/vanesabo/Library/Java/JavaVirtualMachines/openjdk-22.0.1/Contents/Home/include/darwin -shared -fPIC -o native_lib.so model.cc native_lib.cpp
