package a00123456.lab.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import a00123456.lab.decode.Decoder;
import a00123456.lab.loadclass.AClassLoader;

public class LabFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// salt for password-based encryption-decryption algorithm
	private static final byte[] salt = { (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a, (byte) 0xb2, (byte) 0xcc,
			(byte) 0xe4, (byte) 0x7f };

	private JLabel label1 = new JLabel("Class Name", SwingConstants.RIGHT);
	private JLabel label2 = new JLabel("Decryption Key:", SwingConstants.RIGHT);
	private JTextField nameField = new JTextField(30);
	private JTextField keyField = new JTextField(4);
	private JButton load = new JButton("Load the Program");

	// iteration count
	private int iterationCount = 100;

	// user input components.
	private JTextField passwordTextField;

	private JTextField fileNameTextField;

	private JEditorPane fileContentsEditorPane;

	// frame constructor
	public LabFrame() {

		{
			Decoder encipherDecipher = new Decoder();
			setTitle("ClassLoaderTest");
			Container c = getContentPane();

			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new GridLayout(3, 1));
			labelPanel.add(label1);
			labelPanel.add(label2);

			JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 1));
			fieldPanel.add(nameField);
			fieldPanel.add(keyField);

			load.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					runClassString(encipherDecipher.readFromFileAndDecrypt(keyField.getText(), nameField.getText()));
				}
			});

			c.add(labelPanel, BorderLayout.WEST);
			c.add(fieldPanel, BorderLayout.CENTER);
			c.add(load, BorderLayout.SOUTH);
		}

	}

	public void runClassString(String classString) {
		try {
			AClassLoader classLoader = new AClassLoader();
			Class<?> c = classLoader.loadClassFromString(classString);
			if (c == null)
				throw new ClassNotFoundException("name");

			String[] args = new String[] {};

			Method m = c.getMethod("main", new Class[] { args.getClass() });
			m.invoke(null, new Object[] { args });

		} catch (Throwable e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}



}
