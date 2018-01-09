package com.deep.jvm.chaptertwo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author zzm
 */
public class DirectMemoryOOM {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws Exception {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
//		Unsafe unsafe = (Unsafe) unsafeField.get("theUnsafe");
		System.out.println("unsafa=" + unsafe);
			while (true) {
			unsafe.allocateMemory(_1MB);
		}
	}



	private Unsafe getUnsafe() throws Throwable {

		Class<?> unsafeClass = Unsafe.class;

		for (Field f : unsafeClass.getDeclaredFields()) {

			if ("theUnsafe".equals(f.getName())) {

				f.setAccessible(true);

				return (Unsafe) f.get(null);

			}

		}

		throw new IllegalAccessException("no declared field: theUnsafe");

	}
}

