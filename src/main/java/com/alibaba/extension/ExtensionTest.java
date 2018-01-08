//package com.alibaba.extension;
//
//import com.alibaba.dubbo.common.extension.ExtensionLoader;
//import com.alibaba.dubbo.rpc.Protocol;
//import org.junit.Test;
//
///**
// * Created by tend on 2017/9/19.
// */
//public class ExtensionTest {
//    public static void main(String[] args) {
//        Protocol p= ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
//        System.out.print(p.getDefaultPort());
//    }
//
//    /**
//     * 测试wrapper包装protocol的关系
//     */
//    @Test
//    public void testInheritWrapper(){
//        Protocol p= ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
//        System.out.print(p.getClass().getSimpleName());
//    }
//
//}
