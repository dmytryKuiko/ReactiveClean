#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_dimi_reactiveclean_data_network_HeaderInterceptor_getNativeApiKey(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "ZDQ3NGJkMmQtMjg2NS00ZjkxLWJkMzEtMDE4ZWUyMDQyMmQ2");
}
