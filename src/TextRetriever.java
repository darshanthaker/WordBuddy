import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.*;

public class TextRetriever
{

    public TextRetriever()
    {
        try 
        {
                Robot robot = new Robot();

                robot.keyPress(157);
                robot.keyPress(KeyEvent.VK_C);
                Thread.sleep(20);

                robot.keyRelease(157);
                robot.keyRelease(KeyEvent.VK_C);
                Thread.sleep(500);
            
                String text = getClipboardText();
                System.out.println("TEXT IS : " + text);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    static String getClipboardText() throws Exception {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }
}
