// SplashScreen.java
// A simple application to show a title screen in the center of the screen
// for the amount of time given in the constructor.  This class includes
// a sample main() method to test the splash screen, but it's meant for use
// with other applications.
//

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Splash extends JWindow {

	private int duration = 10000000;

	private String[] results;
	private String text;

	public void showSplash() {
		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		// Set the window's bounds, centering the window
		int width = 450;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = getWindowPlacement();
		int x = (int) p.getX();
		int y = (int) p.getY();
		setBounds(x, y, width, height);
		
		// Build the screen
		JLabel copyrt = new JLabel("Word", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(copyrt, BorderLayout.NORTH);
		
		
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try
                {
                    TextRetriever retriever = new TextRetriever();
                    text = retriever.getText();
                }
                catch (Exception f)
                {
                    f.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent event)
            {
            }

            @Override
            public void mouseExited(MouseEvent event)
            {
            }

            @Override
            public void mousePressed(MouseEvent event)
            {
            }
  
            @Override
            public void mouseReleased(MouseEvent event)
            {
            }
        };
        FilePoller poller = new FilePoller(text);
		results = poller.getSynonyms();
		System.out.println(Arrays.toString(results));
		
        JList<String> list = new JList<String>(results);
		list.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		content.add(new JScrollPane(list), BorderLayout.CENTER);
        list.addMouseListener(listener);
       
		
		content.setBorder(BorderFactory.createLineBorder(new Color(51, 255, 204), 10));

		// Display it
		setVisible(true);
	}

	private Point getWindowPlacement() {
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY();

		Point p = new Point();
		p.setLocation(x, y);
		return p;
	}

	public void start() {
		// Throw a nice little title page up on the screen firs
		// Normally, we'd call splash.showSplash() and get on with the program.
		// But, since this is only a test...
		try
        {
            TextRetriever retriever = new TextRetriever();
            text = retriever.getText();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		showSplash();
        
	}
}
