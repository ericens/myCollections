package org.zlx.interview;

import java.net.*;

public class VisibilityTest {
	public static void main(String[] args) {
		try {
			URLClassLoader mycl = new URLClassLoader(new URL[] { new URL(
					"file://Users/ericens/java/workspace/Interview/target/") });

			Class c2 = mycl.loadClass("org.zlx.extendtions.A");
			Class.forName("");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
