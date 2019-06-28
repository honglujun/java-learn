package com.java.learn.cpp;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.io.File;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {

        Clibrary instance = Clibrary.INSTANCE;
        String weights_path = "/data/cvg/model_files/abc.weights";
        instance.load_model(weights_path);

        float[][] in = new float[5][100];

        Pointer[] results = new Pointer[in.length];

        for (int i = 0; i < in.length; i++) {
            results[i] = new Memory(100 * Float.SIZE);
            results[i].write(0, in[i], 0, in[0].length);
        }

        float thresh = 0.5f;
        String savePath = "";
        String pathName = "/data/cvg/imgs/";
//        String pathName = "/home/honglujun/222/333";
        String fileName = "";

        //获取pathName的File对象
        File dirFile = new File(pathName);
        //判断该文件或目录是否存在，不存在时在控制台输出提醒
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return;
        }

        //获取此目录下的所有文件名与目录名
        String[] fileList = dirFile.list();
        for (int i = 0; i < fileList.length; i++) {
            //遍历文件目录
            String string = fileList[i];
            //File("documentName","fileName")是File的另一个构造器
            File file = new File(dirFile.getPath(), string);
            String name = file.getName();
            fileName = file.getAbsolutePath();
            // /data/cvg/imgs_dets
            savePath = "/data/cvg/imgs_dets/" + name;
//            savePath = "/home/honglujun/222/444"+name;
            System.out.println(fileName);
            System.out.println(savePath);
            instance.detect_save(fileName, savePath, thresh, results);
        }
    }

}
