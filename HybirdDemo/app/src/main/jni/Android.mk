LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE     := patch_diff
LOCAL_SRC_FILES  := PatchUtils.c DiffUtils.c

LOCAL_LDLIBS     := -lz -llog

include $(BUILD_SHARED_LIBRARY)