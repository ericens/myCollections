package org.zlx.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class utls {
	@SuppressWarnings("unchecked")
	public static void main(String strag[]) {
		Set set=new HashSet<String>();
		Collections.synchronizedSet(set);

		WeakHashMap<String,String> sHashMap=new WeakHashMap<>();
		sHashMap.put("", "");


		Vector vector=new Vector<>();
		vector.add("vector");

		vector.remove(0);

		ArrayList arrayList=new ArrayList<>();
		arrayList.add("array");
		arrayList.remove(0);


		CopyOnWriteArrayList x=new CopyOnWriteArrayList();
		x.add("ccc");


		Hashtable hashtable=new Hashtable<>();
		hashtable.put("key", "value");

		HashMap map=new HashMap<>();
		map.put("key", "value");


		ConcurrentHashMap cMap=new ConcurrentHashMap<>();
		cMap.put("key", "value");
		cMap.put("key2", "value");
		cMap.put("xxxxx", "value");


	}


}
