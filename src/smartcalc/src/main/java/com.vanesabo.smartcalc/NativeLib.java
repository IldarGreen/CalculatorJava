package com.vanesabo.smartcalc;

public class NativeLib {
    static {
        String currentDirectory = System.getProperty("user.dir");
        System.load(currentDirectory + "/src/main/resources/lib/native_lib.so");
    }

    public native double Graph(String str);

    public native double MainFunRunner(String input, double x);
}


// g++ -c -Wall -Werror -Wextra -std=c++17 -fpic model.cc
// g++ -shared -o native_lib.so model.o

// javac -d bin/ src/smartcalc/src/main/java/com.vanesabo.smartcalc/NativeLib.java
// cd bin
// javah my.mega.pack.NativeLib

//javac src/smartcalc/src/main/java/com.vanesabo.smartcalc/NativeLib.java
//javah NativeLib