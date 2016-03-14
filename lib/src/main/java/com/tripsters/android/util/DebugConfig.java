package com.tripsters.android.util;

/**
 * 测试配置
 */
public class DebugConfig {

    public static final boolean DEBUG = false;

    public static boolean sNetDebug;
    public static boolean sLogDebug;

    static {
        sNetDebug = DEBUG;
        sLogDebug = DEBUG;
    }

}
