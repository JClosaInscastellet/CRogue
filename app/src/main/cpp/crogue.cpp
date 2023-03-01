// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("crogue");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("crogue")
//      }
//    }
#include <string>
#include <jni.h>

char* return32(){
    char* login = "Login";
    return login;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_crogue_StartScreen_00024Companion_return32(JNIEnv *env, jobject thiz) {
    // TODO: implement return32()
    return (env)->NewStringUTF(return32());
}