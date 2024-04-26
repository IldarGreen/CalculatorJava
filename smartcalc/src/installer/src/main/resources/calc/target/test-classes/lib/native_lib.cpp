#include <jni.h>
#include "model.h"
#include "validator.h"
#include <string>
#include <sstream>
#include <iostream>
#include <string.h>

extern "C" {

    s21::Model model;
    s21::Validator validator;

JNIEXPORT jstring JNICALL Java_com_greenone_smartcalc_NativeLib_MainFunRunner
    (JNIEnv *jEnv, jobject jObj, jstring jString1, jstring jString2) {
        const char *nativeString1 = jEnv->GetStringUTFChars(jString1, NULL);
        std::string arg1 = nativeString1;
        jEnv->ReleaseStringUTFChars(jString1, nativeString1);

        const char *nativeString2 = jEnv->GetStringUTFChars(jString2, NULL);
        std::string arg2 = nativeString2;
        jEnv->ReleaseStringUTFChars(jString2, nativeString2);


        std::string message = model.MainFunRunner(arg1, arg2);
        return jEnv->NewStringUTF(message.c_str());
    }

JNIEXPORT int JNICALL Java_com_greenone_smartcalc_NativeLib_FieldValidatorIntDouble
    (JNIEnv *jEnv, jobject jObj, jstring jString) {
        const char *nativeString = jEnv->GetStringUTFChars(jString, NULL);
        std::string arg = nativeString;
        jEnv->ReleaseStringUTFChars(jString, nativeString);

        int result = validator.FieldValidatorIntDouble(arg);
        return result;
    }
}
