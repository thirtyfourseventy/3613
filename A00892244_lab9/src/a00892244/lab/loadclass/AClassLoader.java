package a00892244.lab.loadclass;
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

import a00892244.lab.decode.Decoder;

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

	
	public Class<?> loadClassFromByteString(String className, byte[] classBytes) throws ClassNotFoundException {
		String classString = new String(classBytes);
		System.out.println(classString);
		
		System.out.println("NAME: " + className);
		System.out.println(classString);

		Class<?> c = defineClass(className, classBytes, 0, classBytes.length);
		if (c == null)
            throw new ClassNotFoundException(className);
		
		return c;

	}


}
