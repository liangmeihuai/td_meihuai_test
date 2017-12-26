package com.nio.bytebuffer_learn;

import java.nio.ByteBuffer;

/**
 * Created by tend on 2017/12/22.
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println("position1 = " +byteBuffer.position() + " ,limit = " + byteBuffer.limit());
        byteBuffer.limit(3).position(1);
        System.out.println("position2 = " +byteBuffer.position() + " ,limit = " + byteBuffer.limit());
        byteBuffer.flip();
        System.out.println("position3 = " +byteBuffer.position() + " ,limit = " + byteBuffer.limit());
    }
}
