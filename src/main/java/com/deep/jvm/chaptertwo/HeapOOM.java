    package com.deep.jvm.chaptertwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tend on 2017/12/30.
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMerrorError
 *
 * HeapDumpOnOutOfMerrorError参数可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆
 * 转储快照以便时候进行分析
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
