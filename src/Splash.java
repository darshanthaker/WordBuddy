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
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;

public class Splash extends JWindow {

	private int duration = 10000000;

	private String[] results;
	private String text;
	private JLabel copyrt;
	private JPanel content;
	private JList list;
	private int width, height, mx, my;
	
	public void showSplash() {
		content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Set the window's bounds, centering the window
		width = 450;
		height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = getWindowPlacement();
		mx = (int) p.getX();
		my = (int) p.getY();
		setBounds(mx, my, width, height);
        
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
		
		content.setBorder(BorderFactory.createLineBorder(Color.cyan, 10));
		copyrt.updateUI();

		// Display it
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) { 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.RED;
        Color color2 = Color.GREEN;
        GradientPaint gp = new GradientPaint(0, 0,
                getBackground().brighter().brighter(), 0, getHeight(),
                getBackground().darker().darker());
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
	}
	
	public void clear()
	{
		if (content != null)
			content.removeAll();
	}
	
	public String checkElement(int x, int y)
	{
		
		//System.out.println("x = " + x);
		//System.out.println("y = " + y);
		//System.out.println("mx = " + mx);
		//System.out.println("my = " + my);
		
		for (int i = 1; i <= results.length; i++)
		{
			if (y >= my + 25*i && y <= my + 25*i + 25)
			{
				return results[i - 1];
			}
		}
		return null;
		
	}

	private Point getWindowPlacement() {
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY();
		
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		double screenHeight = dimen.getHeight();
		double screenWidth = dimen.getWidth();
		 
		if (x + width > screenWidth) {
			x = x - width;
		} 
		if(y + height > screenHeight) {
			y = y - height;
		}
		
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
