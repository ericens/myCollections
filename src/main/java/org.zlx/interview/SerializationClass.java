package org.zlx.interview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationClass implements Serializable {

	private static final long serialVersionUID = 5024741671582526226L;

	public SerializationClass() {
		try {
			File file = new File("C:/CL_Article/Serialization/test.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.reset();
			oos.close();
			fos.close();
			oos = null;
			fos = null;
			file = null;
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
