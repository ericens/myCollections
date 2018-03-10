package org.zlx.com.taobao.rp.test;



import java.io.File;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Self;
import  static com.sun.btrace.BTraceUtils.*;
// every time new File(String) is called, print the file name

@BTrace(unsafe=true)
public class HelloBTrace {
	@OnMethod(
			clazz="java.io.File",
			method = "getPath",
			location = @Location(Kind.RETURN)
			)
	public static void onNewFile(@Self File self) {
		println(self.getAbsolutePath() +":");
	}





}
