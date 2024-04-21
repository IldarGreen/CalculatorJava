#include "com_greenone_smartcalc_NativeLib"
#include "Model.h"
#include <string>
#include <sstream>
#include <iostream>
#include <string.h>

s21::Model model;

JNIEXPORT jdouble JNICALL Java_com_greenone_smartcalc_NativeLib_Graph
    (JNIEnv *jEnv, jobject jObj, jstring jString) {

  }


JNIEXPORT jdouble JNICALL Java_com_greenone_smartcalc_NativeLib_MainFunRunner
    (JNIEnv *jEnv, jobject jObj, jstring jString, jdouble jDouble) {

  }


JNIEXPORT jstring JNICALL Java_com_greenone_smartcalc_NativeLib_Calculate
    (JNIEnv *jEnv, jobject jObj, jString jString, jdouble jDouble) {

        const char *nativeString = jenv->GetStringUTFChars(jString, null);
        std::string arg = nativeString;
        jEnv->ReleaseStringUTFChars(jString, nativeString);

        model.

  }

