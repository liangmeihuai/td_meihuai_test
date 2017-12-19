package com.common;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public class CommonUtil {
  /**
   * 看下文件是否存在，不存在则创建此父类文件夹和最终文件:如创建C://111//aaa.txt,如果111文件夹不存在，则创建，然后最终创建出来C://111//aaa.txt文件
   *
   * @throws IOException
   */
  public static File createFileAndParentDir(String filePath) throws IOException {
    File file = new File(filePath);//获取文件夹路径
    if (!file.exists()) {//判断文件夹是否创建，没有创建则创建新文件夹
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      file.createNewFile();
    }
    return file;
  }

  /**
   * 关闭流的通用方法
   */
  public static void closeStreams(Closeable... closeables) {
    if (closeables == null)
      return;
    for (Closeable closeable : closeables) {
      try {
        if (closeable != null)
          closeable.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
