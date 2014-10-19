// SplashScreen.java
// A simple application to show a title screen in the center of the screen
// for the amount of time given in the constructor.  This class includes
// a sample main() method to test the splash screen, but it's meant for use
// with other applications.
//

import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow {

	private int duration = 10000000;

	private String[] results;

	public void showSplash() {
		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		// Set the window's bounds, centering the window
		int width = 450;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width) / 2;
		int y = (screen.height-height) / 2;
		setBounds(x, y, width, height);

		// Fill results
		results = new String[100];
		for(int i = 0; i < 100; i++) {
			results[i] = i + "";
		}
		
		// Build the screen
		JLabel copyrt = new JLabel("Word", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(copyrt, BorderLayout.NORTH);
		
		JList<String> list = new JList<String>(results);
		list.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		content.add(new JScrollPane(list), BorderLayout.CENTER);
		
		content.setBorder(BorderFactory.createLineBorder(new Color(51, 255, 204), 10));

		// Display it
		setVisible(true);
	}

	public void start() {
		// Throw a nice little title page up on the screen firs
		// Normally, we'd call splash.showSplash() and get on with the program.
		// But, since this is only a test...
		showSplash();
	}
}