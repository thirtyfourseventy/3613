package a00123456.lab.loadclass;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import a00123456.lab.decode.Decoder;

/**
   @version 1.20 2001-08-23
   @author Cay Horstmann modified by Paul Mills
*/



/**
   This program demonstrates a custom class loader that decrypts
   class files.

   This frame contains two text fields for the name of the class
   to load and the decryption key.
*/

   

/**
   This class loader loads encrypted class files.
*/
public class AClassLoader extends ClassLoader
{

	/**
		Constructs a crypto class loader.
		@param k the decryption key
	*/
	public AClassLoader() {
		 
	}

	@SuppressWarnings("rawtypes")
	protected Class<?> findClass(String name)
		throws ClassNotFoundException
	{
		byte[] classBytes = null;
		try {
			classBytes = loadClassBytes(name);
		} catch (IOException exception) {
			throw new ClassNotFoundException(name);
		}

		Class cl = defineClass(name, classBytes, 0, classBytes.length);
		if (cl == null)
            throw new ClassNotFoundException(name);
		return cl;
	}
	
	public Class<?> loadClassFromString(String classString) throws ClassNotFoundException {
		byte[] classBytes = classString.getBytes();

		System.out.println(classString);
		String className = classString.split("public class ")[1].split(" ")[0];
		
		System.out.println("NAME: " + className);
		System.out.println(classString);

		Class<?> c = defineClass(className, classBytes, 0, classBytes.length);
		if (c == null)
            throw new ClassNotFoundException(className);
		
		return c;

	}

	/**
		Loads and decrypt the class file bytes.
		@param name the class name
		@return an array with the class file bytes
	*/
	private byte[] loadClassBytes(String name)
		throws IOException
	{
		String cname = name.replace('.', '/') + ".class";
		FileInputStream in = null;
		try {
			in = new FileInputStream(cname);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				byte b = (byte)(ch);
				buffer.write(b);
			}
			in.close();
			return buffer.toByteArray();

		} finally{
			if (in != null)
				in.close();
		}
	}
}
