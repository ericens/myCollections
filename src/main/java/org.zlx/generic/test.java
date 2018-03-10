package org.zlx.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class test {


	/*
	 * 本例向list类型集合中加入了一个字符串类型的值和一个Integer类型的值。（这样合法，因为此时list默认的类型为Object类型）。在之后的循环中，
	 * 由于忘记了之前在list中也加入了Integer类型的值或其他原因，
	 * 运行时会出现java.lang.ClassCastException异常。为了解决这个问题，泛型应运而生
	 * */
	@Test
	public  void noGeneric() {
		List list = new ArrayList();
		list.add("CSDN_SEU_Cavin");
		list.add(100);
		for (int i = 0; i < list.size(); i++) {
			String name = (String) list.get(i); //取出Integer时，运行时出现异常
			System.out.println("name:" + name);
		}

	}



	/*
	 * 泛型只在编译阶段有效,  //TODO
	 * */
	@Test
	public  void generic_onlyRuntime() {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		Class c1 = a.getClass();
		Class c2 = b.getClass();
		System.out.println(c1== c2); //true ?  false
	}



	/*
	 * 也就是说，成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段
	 * 通过反射 绕过
	 * */
	@Test
	public  void generic() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("CSDN_SEU_Cavin");
		Class c = a.getClass();
		try{
			Method method = c.getMethod("add",Object.class);
			method.invoke(a,100);
			System.out.println(a);
		}catch(Exception e){
			e.printStackTrace();
		}

		Collection<String> cx = new Vector(); //  ○ 参数化类型可以引用一个原始类型的对象，编译时编译器会报警告，例如：
		Collection cy = new Vector<String>(); //  ○ 原始类型可以引用一个参数化类型的对象，编译时编译器会报警告，例如：

		Vector v1 = new Vector<String>();
		Vector<Object> v=v1;
	}



	@Test
	public  void name2() {
		List<Integer> ex_int= new ArrayList<Integer>();
		/*
		List<Number> ex_num = ex_int; //非法的
		会出现编译错误，因为Integer虽然是Number的子类，但List<Integer>不是List<Number>的子类型。

		*/
		List<Integer> ex_num = ex_int; //非法的

	}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static class FX<T> {
		private T ob; // 定义泛型成员变量

		public FX(T ob) {
			this.ob = ob;
		}

		public T getOb() {
			return ob;
		}

		public void showTyep() {
			System.out.println("T的实际类型是: " + ob.getClass().getName());
		}
	}

	@Test
	public  void main() {
		FX<Integer> intOb = new FX<Integer>(100);
		intOb.showTyep();
		System.out.println("value= " + intOb.getOb());
		System.out.println("----------------------------------");

		FX<String> strOb = new FX<String>("CSDN_SEU_Calvin");
		strOb.showTyep();
		System.out.println("value= " + strOb.getOb());
	}

}
