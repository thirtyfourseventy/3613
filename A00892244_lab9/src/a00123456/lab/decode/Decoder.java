package a00123456.lab.decode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Decoder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// salt for password-based encryption-decryption algorithm
	private static final byte[] salt = { (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a, (byte) 0xb2, (byte) 0xcc, (byte) 0xe4, (byte) 0x7f };


	// iteration count
	private int iterationCount = 100;

	// user input components.
	private JTextField passwordTextField;

	private JTextField fileNameTextField;

	private JEditorPane fileContentsEditorPane;

	// frame constructor
	public Decoder() {

		// initialize main frame
		setSize(new Dimension(400, 400));
		setTitle("Encryption and Decryption Example");

		// construct top panel
		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		topPanel.setLayout(new BorderLayout());

		// panel where password and file name labels will be placed
		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new GridLayout(2, 1));
		JLabel passwordLabel = new JLabel(" Password: ");
		JLabel fileNameLabel = new JLabel(" File Name: ");
		labelsPanel.add(fileNameLabel);
		labelsPanel.add(passwordLabel);
		topPanel.add(labelsPanel, BorderLayout.WEST);

		// panel where password and file name textfields will be placed
		JPanel textFieldsPanel = new JPanel();
		textFieldsPanel.setLayout(new GridLayout(2, 1));
		passwordTextField = new JPasswordField();
		fileNameTextField = new JTextField();
		textFieldsPanel.add(fileNameTextField);
		textFieldsPanel.add(passwordTextField);
		topPanel.add(textFieldsPanel, BorderLayout.CENTER);

		// construct middle panel
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());

		// construct and place title label for contents pane
		JLabel fileContentsLabel = new JLabel();
		fileContentsLabel.setText(" File Contents");
		middlePanel.add(fileContentsLabel, BorderLayout.NORTH);

		// initialize and place editor pane within scroll panel
		fileContentsEditorPane = new JEditorPane();
		middlePanel.add(new JScrollPane(fileContentsEditorPane),
				BorderLayout.CENTER);

		// construct bottom panel
		JPanel bottomPanel = new JPanel();

		// create encrypt button
		JButton encryptButton = new JButton("Encrypt and Write to File");
		encryptButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				encryptAndWriteToFile();
			}
		});
		bottomPanel.add(encryptButton);

		// create decrypt button
		JButton decryptButton = new JButton("Read from File and Decrypt");
		decryptButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				readFromFileAndDecrypt();
			}
		});
		bottomPanel.add(decryptButton);

		// initialize main frame window
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(middlePanel, BorderLayout.CENTER);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	} // end constructor

	// obtain contents from editor pane and encrypt
	@SuppressWarnings("unchecked")
	private void encryptAndWriteToFile() {

		// obtain user input
		String originalText = fileContentsEditorPane.getText();
		String password = passwordTextField.getText();
		String fileName = fileNameTextField.getText();

		// create secret key and get cipher instance
		Cipher cipher = null;

		try {

			// create password based encryption key object
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

			// obtain instance for secret key factory
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");

			// generate secret key for encryption
			SecretKey secretKey = keyFactory.generateSecret(keySpec);

			// specifies parameters used with password based encryption
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,
					iterationCount);

			// obtain cipher instance reference
			cipher = Cipher.getInstance("PBEWithMD5AndDES");

			// initialize cipher in encrypt mode
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
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

		// create array of bytes
		byte[] outputArray = null;

		try {
			outputArray = originalText.getBytes("ISO-8859-1");
		}

		// handle UnsupportedEncodingException
		catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// create FileOutputStream
		File file = new File(fileName);
		FileOutputStream fileOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream(file);
		}

		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// create CipherOutputStream
		CipherOutputStream out = new CipherOutputStream(fileOutputStream,
				cipher);

		// write contents to file and close
		try {
			out.write(outputArray);
			out.flush();
			out.close();
		}

		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// contain bytes read from file
		@SuppressWarnings("rawtypes")
		Vector fileBytes = new Vector();

		// read contents from file to show user encrypted text
		try {
			FileInputStream in = new FileInputStream(file);

			// read bytes from stream.
			byte contents;

			while (in.available() > 0) {
				contents = (byte) in.read();
				fileBytes.add(new Byte(contents));
			}

			in.close();
		}

		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// create byte array from contents in Vector fileBytes
		byte[] encryptedText = new byte[fileBytes.size()];

		for (int i = 0; i < fileBytes.size(); i++) {
			encryptedText[i] = ((Byte) fileBytes.elementAt(i)).byteValue();
		}

		// update Editor Pane contents
		fileContentsEditorPane.setText(new String(encryptedText));
	}

	// obtain contents from file and decrypt
	@SuppressWarnings("unchecked")
	private void readFromFileAndDecrypt() {

		// obtain user input
		String password = passwordTextField.getText();
		String fileName = fileNameTextField.getText();

		// update Editor Pane contents.
		fileContentsEditorPane.setText(readFromFileAndDecrypt(password, fileName));
	}
	
	public String readFromFileAndDecrypt(String password, String fileName) {

		// used to rebuild byte list
		@SuppressWarnings("rawtypes")
		Vector fileBytes = new Vector();

		// create secret key
		Cipher cipher = null;

		try {
			// create password based encryption key object
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

			// obtain instance for secret key factory
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");

			// generate secret key for encryption
			SecretKey secretKey = keyFactory.generateSecret(keySpec);

			// specifies parameters used with password based encryption
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,
					iterationCount);

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
		try {
			File file = new File(fileName);
			FileInputStream fileInputStream = new FileInputStream(file);

			CipherInputStream in = new CipherInputStream(fileInputStream,
					cipher);

			// read bytes from stream.
			byte contents = (byte) in.read();

			@SuppressWarnings("unused")
			int count = 0;
			while (contents != -1) {
				count++;
				fileBytes.add(new Byte(contents));
				contents = (byte) in.read();
			}
			in.close();

		}

		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// create byte array from contents in Vector fileBytes
		byte[] decryptedText = new byte[fileBytes.size()];

		for (int i = 0; i < fileBytes.size(); i++) {
			decryptedText[i] = ((Byte) fileBytes.elementAt(i)).byteValue();
		}

		// update Editor Pane contents.
		return new String(decryptedText);
	}

	// create frame and display
	public static void main(String[] args) {
		Decoder crypto = new Decoder();
		crypto.validate();
		crypto.setVisible(true);
	}
}
