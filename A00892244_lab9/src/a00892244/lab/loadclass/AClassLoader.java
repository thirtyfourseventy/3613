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
 * @author Edward Lambke A00892244
 *
 */
public class AClassLoader extends ClassLoader
{

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
