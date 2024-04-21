/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_greenone_smartcalc_NativeLib */

#ifndef _Included_com_greenone_smartcalc_NativeLib
#define _Included_com_greenone_smartcalc_NativeLib
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_greenone_smartcalc_NativeLib
 * Method:    Graph
 * Signature: (Ljava/lang/String;)D
 */
JNIEXPORT jdouble JNICALL Java_com_greenone_smartcalc_NativeLib_Graph
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_greenone_smartcalc_NativeLib
 * Method:    MainFunRunner
 * Signature: (Ljava/lang/String;D)D
 */
JNIEXPORT jdouble JNICALL Java_com_greenone_smartcalc_NativeLib_MainFunRunner
  (JNIEnv *, jobject, jstring, jdouble);

/*
 * Class:     com_greenone_smartcalc_NativeLib
 * Method:    Calculate
 * Signature: (Ljava/lang/String;D)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_greenone_smartcalc_NativeLib_Calculate
  (JNIEnv *, jobject, jstring, jdouble);

#ifdef __cplusplus
}
#endif
#endif
