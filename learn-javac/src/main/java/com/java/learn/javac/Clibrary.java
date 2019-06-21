package com.java.learn.javac;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Clibrary extends Library {

    /**
     * 获取当前操作系统的类型
     */
    String os = System.getProperty("os.name");
    /**
     * windows操作系统为1 否则为0
     */
    int beginIndex = os != null && os.startsWith("Windows") ? 1 : 0;

    Clibrary INSTANTCE = (Clibrary) Native.synchronizedLibrary(
            (Clibrary) Native.loadLibrary(
//                    Clibrary.class.getResource("/lib.so") // 相对路径
//                            .getPath()
//                            .substring(beginIndex)
                    "/home/honglujun/lib.so".substring(beginIndex)
                    , Clibrary.class
            )
    );

    // 此方法为so文件中的c语言函数1 -> int test_return_C(void);
    //  ##备注: 这里的void代表无参
    int test_return_C();

    // 此方法为so库中的c语言函数2 -> char* Decrpyt( char * input);
    // ## 备注: 这里的char* 是c语言中的指针，与java中的String相对应
    String Decrpyt(String input);
    void test_a();

}
