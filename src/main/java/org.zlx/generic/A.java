package org.zlx.generic;

import java.util.ArrayList;

import org.junit.Test;

public class A {
	@Test
	public  void generic_onlyRuntime() {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		Class c1 = a.getClass();
		Class c2 = b.getClass();
		System.out.println(c1== c2); //true ?  false

		//使用javap 查看的类 org.zlx.extendtions.A 的字节码，却查看		ArrayList<String 的字节码，所以要自己定义才可以
	}
}
