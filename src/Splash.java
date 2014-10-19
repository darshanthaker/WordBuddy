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
	private JLabel copyrt;
	private JPanel content;
	private JList list;

	public void showSplash() {
		content = (JPanel) getContentPane();
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
        copyrt = new JLabel(text, JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(copyrt, BorderLayout.NORTH);
		
        FilePoller poller = new FilePoller(text);
		results = poller.getSynonyms();
		if (results[0] == null)
		{
			results[0] = "Sorry there are no thesaurus entries";
			for (int i = 1; i <= 4; i++)
			{
				results[i] = "";
			}
		}
		System.out.println(Arrays.toString(results));
		
        list = new JList(results);
		list.setFont(new Font("Sans-Serif", Font.BOLD, 19));
		content.add(new JScrollPane(list), BorderLayout.CENTER);
        //list.addMouseListener(listener);
       
		
		content.setBorder(BorderFactory.createLineBorder(new Color(51, 255, 204), 10));
		copyrt.updateUI();

		// Display it
		setVisible(true);
	}
	
	public void clear()
	{
		content.removeAll();
	}
	
	public String checkElement(int x, int y)
	{
		Point p = getWindowPlacement();
		int mx = (int) p.getX();
		int my = (int) p.getY();
		
		if (y >= my && y <= my + 30)
		{
			return results[0];
		}
		else if (y >= my + 30 && y <= my + 60)
		{
			return results[1];
		}
		else if (y >= my + 60 && y <= my + 90)
		{
			return results[2];
		}
		else if (y >= my + 90 && y <= my + 120)
		{
			return results[3];
		}
		else if (y >= my + 120 && y <= my + 150)
		{
			return results[4];
		}
		return text;
	}

	private Point getWindowPlacement() {
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY();

		Point p = new Point();
		p.setLocation(x, y);
		return p;
	}

	public void start() {
		// Throw a nice little title page up on the screen first
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
