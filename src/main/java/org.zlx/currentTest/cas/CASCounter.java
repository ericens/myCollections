package org.zlx.currentTest.cas;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CASCounter implements Counter {
	private volatile long counter = 0;
	private Unsafe unsafe;
	private long offset;




	public CASCounter() throws Exception {
		unsafe = getUnsafe();
		offset = unsafe.objectFieldOffset(CASCounter.class
				.getDeclaredField("counter"));
	}

	public void increment() {
		long before = counter;
		while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
			before = counter;
		}
	}

	public long getCounter() {

		return counter;
	}

	public static Unsafe getUnsafe() throws Exception{
		Unsafe unsafe=null;

		Class cc = sun.reflect.Reflection.getCallerClass(2);
		if (cc.getClassLoader() != null)
			throw new SecurityException("Unsafe");
		unsafe=Unsafe.getUnsafe();
		/*
		java -Xbootclasspath:/Library/Java/JavaVirtualMachines/jdk1.8.0_20.jdk/Contents/Home/jre/lib/rt.jar:. CASCounter
		-Xbootclasspath 启动时候，把加载程序appliation的 loader 设置为  bootstrap。 也就是添加bootstrap的 路径
		*/

		//利用反射获取该 single变量 field
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		unsafe = (Unsafe) theUnsafe.get(null);

		//利用反射获 Constructor
		Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
		unsafeConstructor.setAccessible(true);
		unsafe = unsafeConstructor.newInstance();

		return unsafe;
	}

}
