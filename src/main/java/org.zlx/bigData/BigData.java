package org.zlx.bigData;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;




public class BigData {
	Logger logger=Logger.getLogger(BigData.class.toString());

	String fileSplit="";
	String valueSplitString="";
	String PRESENT="";

	int topN=100; //第一百个为0

	//save the top 100 url, with the format   url:count
	SortedMap<String,Integer> fullMap=new TreeMap<String,Integer>();

	//save the top 100 url, with the format   count:url
	TreeMap<MyItem,String> topMap=new TreeMap<MyItem,String>();


	public void out(Object o){
		System.out.println(o);
	}


	public class MyItem{
		public String keyString;
		public int count;

		public MyItem(String kString,int c){
			keyString=kString;
			count=c;
		}


		public int hashcode(){
			logger.info("....................................hashcode:"+keyString.hashCode());
			return keyString.hashCode();

		}
		public boolean equal(MyItem i1){
			if(i1.count==count && i1.keyString.equals(keyString))
				return true;
			return false;
		}
		public String toString(){
			return count+valueSplitString+keyString;
		}
	}


	public void splitToFile(String bigFile){

	}



	/**
	 * 根据key 的值。更新 sortedSet
	 * @param key
	 * @return
	 */
	public void updateTopN(MyItem item){


		if(topMap.size()<topN){
			//不到一百个，则直接加入
			topMap.put(item, PRESENT );

		}
		else {
			//更新Map的  count:key
			MyItem last=topMap.lastKey();
			if(item.count>last.count){
				// 应该是找出对应哪个key，删除它，再添加它
				/*
				如果在这里。set
				12 7 5 3 2 1
				           2。  更新最后一个
				       4 2 2。  更新倒数第三个
				不在set 里。
				是更新了map  （key：count）

				*/
				//topMap.remove(last); //不应该删除最后一个。而是key 的那个
				MyItem item2=new MyItem(item.keyString, item.count-1);
				topMap.remove(item2);
				topMap.put(item, PRESENT);
			}
		}
	}
	public void printTopN(){

	}
	/**
	 *
	 * @param littleFile
	 * @throws FileNotFoundException
	 */
	public void getTopOflittleFile(String littleFile) throws Exception{


		FileReader reader=new FileReader(new File(littleFile));

		InputStream in=new FileInputStream(new File(littleFile));
		DataInputStream  dtaInputStream=new DataInputStream(in);


		String line=null;
		while( (line=dtaInputStream.readLine())!=null){
			String a[]=line.split(fileSplit);
			if(a!=null &&a.length >17){
				String key=a[16];
				Integer count=fullMap.get(key);
				//更新map 中的 key：count 对
				if(count==null){
					fullMap.put(key,new Integer(1));
				}
				else {
					count++;
					fullMap.put(key,count);
				}

				//更新sortedSet的  count:key
				updateTopN(new MyItem(key, count));
			}
		}
	}

}
