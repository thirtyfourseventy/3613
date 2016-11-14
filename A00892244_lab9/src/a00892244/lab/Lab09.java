/**
 * 
 */
package a00892244.lab;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import a00892244.lab.view.LabFrame;

/**
 * @author elambke
 *
 */
public class Lab09 {

	/**
	 * 
	 */
	public Lab09() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	// create frame and display
	public static void main(String[] args) {
		LabFrame app = new LabFrame();
		app.pack();
		Toolkit theKit = app.getToolkit();
		Dimension windowSize = theKit.getScreenSize();
		app.setLocation((windowSize.width - app.getWidth()) / 2, (windowSize.height - app.getHeight()) / 2);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		app.setVisible(true);
	}

}
