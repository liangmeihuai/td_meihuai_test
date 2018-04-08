package com.jdkeight;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/30 2:34
 * @since 1.0
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        String initialReference = "initial value referenced";

        AtomicReference<String> atomicStringReference =
                new AtomicReference<String>(initialReference);

        String newReference = "new value referenced";
        String newReference2 = "new value referenced2";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
//        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
//        exchanged = atomicStringReference.compareAndSet(newReference, newReference2);
        System.out.println("exchanged: " + exchanged);
    }
}
