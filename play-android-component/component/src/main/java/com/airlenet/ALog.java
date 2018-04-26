package com.airlenet;

import com.airlenet.component.BuildConfig;

import java.io.IOException;

/**
 * Created by lig on 16/6/27.
 */
public class ALog {
    public static final boolean ZLOG_ON = BuildConfig.DEBUG;

    /**
     * dalvik.system.VMStack.getThreadStackTrace(Native Method)
     * java.lang.Thread.getStackTrace(Thread.java:)
     * com.airshiplay.ALog.getTAG(ALog.java:)
     * com.airshiplay.ALog.w(ALog.java:)
     * com.yikang.fragment.PersonFragment.onViewCreated(PersonFragment.java:)
     *
     * @return tag
     */
    private static String getClassName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }

    /**
     * dalvik.system.VMStack.getThreadStackTrace(Native Method)
     * java.lang.Thread.getStackTrace(Thread.java:)
     * com.airshiplay.ALog.getTAG(ALog.java:)
     * com.airshiplay.ALog.w(ALog.java:)
     * com.yikang.fragment.PersonFragment.onViewCreated(PersonFragment.java:)
     *
     * @return tag
     */
    private static String getTAG() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int count = stackTraceElements.length;
        int index = 0;
        for (int i = 0; i < count; i++) {
            StackTraceElement stackTraceElement = stackTraceElements[i];
            if (stackTraceElement.getClassName().contains("com.airshiplay.ALog") && !stackTraceElement.getMethodName().equals("getTAG")) {
                index = i + 1;
                break;
            }
        }
        return Thread.currentThread().getStackTrace()[index].getClassName();
    }

    public static void printStackTrace(Appendable err, StackTraceElement[] traceElements)
            throws IOException {
        err.append("\n");
        if (traceElements != null) {
            boolean start = false;
            for (int i = 0; i < traceElements.length; i++) {
                StackTraceElement stack = traceElements[i];

                if (!start && stack.getClassName().contains("com.airshiplay.ALog") && !stack.getMethodName().equals("getTAG")) {
                    start = true;
                }

                if (stack.getClassName().startsWith("android.app") || stack.getClassName().startsWith("android.support")) {
                    break;
                }
                err.append("\tat ");
                err.append(traceElements[i].toString());
                err.append("\n");
            }
        }
    }

    public static void e(String msg) {
        if (ZLOG_ON)
            android.util.Log.e(getTAG(), msg);
    }

    public static void e(Throwable throwable) {
        if (ZLOG_ON)
            android.util.Log.e(getTAG(), throwable.getMessage(), throwable);
    }

    public static void d(String msg) {
        if (ZLOG_ON)
            android.util.Log.d(getTAG(), msg);
    }

    public static void w(String msg) {
        if (ZLOG_ON)
            android.util.Log.w(getTAG(), msg);
    }
}
