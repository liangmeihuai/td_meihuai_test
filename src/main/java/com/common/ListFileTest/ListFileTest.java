package com.common.ListFileTest;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tend on 2018/3/8.
 */
public class ListFileTest {
    private static final String dir = "D:\\meihuai\\alluxio";
    public static void main(String[] args) {
//        File[] files = new File(dir).listFiles();
//        System.out.println(Arrays.deepToString(files));
          List<File> fileList = new ArrayList<>();
        System.out.println(getFileList(dir, fileList));
    }

    public static List<File> getFileList(String strPath, List<File> fileList) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()){
                    return true;
                }else {
//                    String name = file.getName();
//                    if (name.contains("252")){
//                        return true;
//                    }else {
//                        return false;
//                    }
                    return true;
                }
            }
        }); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath(),fileList); // 获取文件绝对路径
                } else {
                    String strFileName = files[i].getAbsolutePath();
//                    System.out.println("---" + strFileName);
                    fileList.add(files[i]);
                }
            }

        }
        return fileList;
    }
}
