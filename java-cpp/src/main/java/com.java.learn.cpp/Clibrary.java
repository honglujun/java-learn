package com.java.learn.cpp;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface Clibrary extends Library {

    /**
     * 获取当前操作系统的类型
     */
    String os = System.getProperty("os.name");
    /**
     * windows操作系统为1 否则为0
     */
    int beginIndex = os != null && os.startsWith("Windows") ? 1 : 0;


    Clibrary INSTANCE = (Clibrary) Native.synchronizedLibrary(
            (Clibrary) Native.loadLibrary(
                    Clibrary.class.getResource("/test/libtest.so") // 相对路径
                            .getPath()
                            .substring(beginIndex)
//                    "/home/honglujun/lib_process.so".substring(beginIndex)
                    , Clibrary.class
            )
    );


    void load_model(String weights_path);
    void detect_save(String filename, String SavePath, float thresh, Pointer[] result);
    void setPoint(int x, int y);
    int main();
    void load_network();
    void test_a();

}
