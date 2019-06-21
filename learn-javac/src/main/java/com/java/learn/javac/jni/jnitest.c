
#include  <string.h>
#include  <jni.h>

jstring
Java_com_byl_jnitest2_JniUtil_getSec
   (JNIEnv* env,jobject thiz){
	return (*env)->NewStringUTF(env,"调用jni成功");
}