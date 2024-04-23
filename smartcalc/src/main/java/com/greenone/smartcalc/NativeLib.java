package com.greenone.smartcalc;

public class NativeLib {
    static {
        String currentDirectory = System.getProperty("user.dir");
        System.load(currentDirectory + "/src/main/resources/lib/native_lib.so");
    }

//    public native double Graph(String str);//

    public native String MainFunRunner(String input, String x);
}


// g++ -c -Wall -Werror -Wextra -std=c++17 -fpic model.cc
// g++ -shared -o native_lib.so model.o

//javac -h . NativeLib.java

//g++ -shared -fPIC -o libMyLibrary.so MyLibrary.cpp
//g++ -shared -fPIC -o native_lib.so model.cc native_lib.cpp


//g++ -I/Users/vanesabo/Library/Java/JavaVirtualMachines/openjdk-22.0.1/Contents/Home/include/ -I/Users/vanesabo/Library/Java/JavaVirtualMachines/openjdk-22.0.1/Contents/Home/include/darwin -shared -fPIC -o native_lib.so model.cc native_lib.cpp
