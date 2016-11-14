package a00892244.lab.decode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// EncipherDecipher.java

public class Decoder {

	/**
	 * 
	 */

	// salt for password-based encryption-decryption algorithm
	private static final byte[] salt = { (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a, (byte) 0xb2, (byte) 0xcc,
			(byte) 0xe4, (byte) 0x7f };

	// iteration count
	private int iterationCount = 100;

	// frame constructor
	public Decoder() {

	} // end constructor

	public byte[] readFromFileAndDecrypt(String password, String fileName) {

		// used to rebuild byte list
		@SuppressWarnings("rawtypes")
		Vector fileBytes = new Vector();

		// create secret key
		Cipher cipher = null;

		try {
			// create password based encryption key object
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

			// obtain instance for secret key factory
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

			// generate secret key for encryption
			SecretKey secretKey = keyFactory.generateSecret(keySpec);

			// specifies parameters used with password based encryption
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);

			// obtain cipher instance reference.
			cipher = Cipher.getInstance("PBEWithMD5AndDES");

			// initialize cipher in decrypt mode
			cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
		}

		// handle NoSuchAlgorithmException
		catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeySpecException
		catch (InvalidKeySpecException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeyException
		catch (InvalidKeyException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle NoSuchPaddingException
		catch (NoSuchPaddingException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidAlgorithmParameterException
		catch (InvalidAlgorithmParameterException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// read and decrypt contents from file
		// try {
		// File file = new File(fileName);
		// FileInputStream fileInputStream = new FileInputStream(file);
		//
		// CipherInputStream in = new CipherInputStream(fileInputStream,
		// cipher);
		//
		// // read bytes from stream.
		// byte contents = (byte) in.read();
		//
		// @SuppressWarnings("unused")
		// int count = 0;
		// while (contents != -1) {
		// count++;
		// fileBytes.add(new Byte(contents));
		// contents = (byte) in.read();
		// }
		// in.close();

		FileInputStream inputfile = null;
		byte[] outputArray = null;
		try {
			inputfile = new FileInputStream(fileName);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			CipherInputStream in = new CipherInputStream(inputfile, cipher);
			int i = 0;
			while ((ch = in.read()) != -1) {
				byte b = (byte) (ch);
				buffer.write(b);
				i++;
			}
			in.close();
			outputArray = buffer.toByteArray();

		}

		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		return outputArray;

	}
}
