package org.zlx.interview;

import java.util.Map;
import java.util.WeakHashMap;


public class Reference {
	public static void main(String[] args) {
//        Map<String, String> list = new HashMap<String, String>();  ?
        Map<String, String> list = new WeakHashMap<String, String>();
        long i = 1;
        while (i < 100000000L) {
            list.put(
                    String.valueOf(i),
                    "JDJJDJJJJJJJJJJ%%%%%%%%JJJJJJJJJJJJJJJKKKKKKKKKKKKKKKKKJJJJJJ"
                            + "JJJKKKKKHDDDJDJDJDJDJDJDJDJJDJDJDJDJDJDJJDJDJDJDJJDJDJJJJJJJJJ"
                            + "JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ"
                            + "JJJJJJJJJJJJJJJJJJJJJJJJJJJJ"+i);

            // 测试第一个是否依然存活
            if (i % 1000000 == 0) {
                System.out.print(i);
            }

            i++;
        }
    }
}
