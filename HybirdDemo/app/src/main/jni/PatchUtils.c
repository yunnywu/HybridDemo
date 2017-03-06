#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <err.h>
#include <unistd.h>
#include <fcntl.h>
#include <android/log.h>
#include <jni.h>

#include "bzip2/bzlib.c"
#include "bzip2/crctable.c"
#include "bzip2/compress.c"
#include "bzip2/decompress.c"
#include "bzip2/randtable.c"
#include "bzip2/blocksort.c"
#include "bzip2/huffman.c"
#include "bsdiff.c"

#include "PatchUtils.h"


/*
 * Class:     com_noahyijie_ygb_util_PatchUtil_patch
 * Method:    patch
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_wu_cy_com_hybirddemo_util_PatchDiffUtil_patch(JNIEnv *env,
		jobject obj, jstring old, jstring new, jstring patch) {

	char * ch[4];
	ch[0] = "bspatch";
	ch[1] = (char*) ((*env)->GetStringUTFChars(env, old, 0));
	ch[2] = (char*) ((*env)->GetStringUTFChars(env, new, 0));
	ch[3] = (char*) ((*env)->GetStringUTFChars(env, patch, 0));

	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "old = %s ", ch[1]);
	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "new = %s ", ch[2]);
	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "patch = %s ", ch[3]);

	int ret = ff_patch(ch[1], ch[3], ch[2]);

	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "applypatch result = %d ", ret);

	(*env)->ReleaseStringUTFChars(env, old, ch[1]);
	(*env)->ReleaseStringUTFChars(env, new, ch[2]);
	(*env)->ReleaseStringUTFChars(env, patch, ch[3]);

	return ret;
}

/*
 * Class:     com_noahyijie_ygb_util_PatchUtil_patch
 * Method:    patch
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_wu_cy_com_hybirddemo_util_PatchDiffUtil_diff(JNIEnv *env,
		jobject obj, jstring old, jstring new, jstring patch) {

	char * ch[4];
	ch[0] = "bspatch";
	ch[1] = (char*) ((*env)->GetStringUTFChars(env, old, 0));
	ch[2] = (char*) ((*env)->GetStringUTFChars(env, new, 0));
	ch[3] = (char*) ((*env)->GetStringUTFChars(env, patch, 0));

	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "old = %s ", ch[1]);
	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "new = %s ", ch[2]);
	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "patch = %s ", ch[3]);

	int ret = ff_diff(ch[1], ch[2], ch[3]);

	__android_log_print(ANDROID_LOG_DEBUG, "ApkPatchLibrary", "applyDiff result = %d ", ret);

	(*env)->ReleaseStringUTFChars(env, old, ch[1]);
	(*env)->ReleaseStringUTFChars(env, new, ch[2]);
	(*env)->ReleaseStringUTFChars(env, patch, ch[3]);

	return ret;
}