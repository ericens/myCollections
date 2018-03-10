package org.zlx.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Set;
import java.util.Vector;
/**
 * DAO：Data Access Object(数据访问对象)
 * 数据访问：CRUD，即增删改查
 * @author 孤傲苍狼
 * 此类是用来演示如何定义泛型类
 * 此泛型类中的<E>中的E代表实际操作的类型
 * 指明了操作类型E之后，GenericDAO类中定义的CRUD方法就都是针对于指定的类型
 */
public class GenericDAO<E> {
	private E field1; //定义泛型类型的成员变量
	public <E> void add(E x){
	}
	public <E> E findById(int id){
		return null;
	}
	public void delete(E obj){
	}
	public void delete(int id){
	}
	public void update(E obj){
	}
	// public static void update(E obj){}  //这样定义会报错，静态方法不允许使用泛型参数
	public static <asdf> void update2(asdf obj){}//这样定义就可以，此时的这个静态方法所针对的类型和GenericDAO<E>中指定的类型是两个不同的类型
	public Set<E> findByConditions(String where){
		return null;
	}

	public static void applyVector(Vector<Date> v) {
	}


	public static void main(String[] args) throws Exception {
		/**
		 * 通过这种方式得到的字节码中是没有办法得到泛型类的实际类型参数的，
		 * 因为在编译这个泛型类时就已经把这个泛型类的实际参数给去掉了
		 * Vector<Date> v = new Vector<Date>();
		 *  v.getClass();
		 */
		Method applyMethod = GenericDAO.class.getMethod(
				"applyVector", Vector.class);
		//得到泛型类型的参数化类型数组，Type类是Class类的父类
		Type[] types = applyMethod.getGenericParameterTypes();
		/**
		 * ParameterizedType这个类是一个参数化类型类，types数组中存储的都是参数化类型的参数，
		 * 这里取出第一个数组元素，并强制转换成ParameterizedType类型
		 */
		ParameterizedType pType = (ParameterizedType) types[0];
		System.out.println(pType.getRawType()/*得到原始类型，输出的结果为：class java.util.Vector*/);
		System.out.println(pType.getActualTypeArguments()[0]/*获得泛型的实际类型参数，输出的结果为：class java.util.Date*/);
	}

	public static void main2(String[] args) throws Exception {
		/**
		 * 通过这种方式得到的字节码中是没有办法得到泛型类的实际类型参数的，
		 * 因为在编译这个泛型类时就已经把这个泛型类的实际参数给去掉了
		 * Vector<Date> v = new Vector<Date>();
		 *  v.getClass();
		 */
		Method applyMethod = GenericDAO.class.getMethod(
				"applyVector", Vector.class);
		//得到泛型类型的参数化类型数组，Type类是Class类的父类
		Type[] types = applyMethod.getGenericParameterTypes();
		/**
		 * ParameterizedType这个类是一个参数化类型类，types数组中存储的都是参数化类型的参数，
		 * 这里取出第一个数组元素，并强制转换成ParameterizedType类型
		 */
		ParameterizedType pType = (ParameterizedType) types[0];
		System.out.println(pType.getRawType()/*得到原始类型，输出的结果为：class java.util.Vector*/);
		System.out.println(pType.getActualTypeArguments()[0]/*获得泛型的实际类型参数，输出的结果为：class java.util.Date*/);
	}




}
