package gui;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class GrabFrame extends FilterFrame {

private SnellWlx sw;

private NetImageSelector nis;

Menu grabMenu = new Menu("Grab");
Menu netMenu = new Menu("net");
 MenuItem cover_mi = addMenuItem(netMenu, "cover");
 MenuItem ublogo_mi = addMenuItem(netMenu, "logo");
 MenuItem author_mi = addMenuItem(netMenu, "the author");
 
 MenuItem testPattern_mi = addMenuItem(grabMenu, "[T]est Patterns");

 MenuItem grabTestPattern_mi = addMenuItem(grabMenu, "[E-G]rab Patterns");

public void actionPerformed(ActionEvent e) {
	if (match(e, author_mi)) {
		netImageSelector("http://www.docjava.com/Pub/Documentation/me.gif");
		return;
	}
	if (match(e, cover_mi)) {
		netImageSelector("http://www.docjava.com/book/1558515682.m.gif");
		return;
	}
	if (match(e, ublogo_mi)) {
		netImageSelector( "http://www.docjava.com/bgublogo.gif");
		return;
	}
	if (match(e, testPattern_mi)) {
		testPattern();
		return;
	}
	if (match(e, grabTestPattern_mi)) {
		grabTestPattern();
		return;
	}
	super.actionPerformed(e);
}
public GrabFrame(String title) {
	super(title);
	grabMenu.add(netMenu);

	fileMenu.add(grabMenu);
}
public void testPattern() {
	sw = new SnellWlx();
	sw.init();
	sw.start();
	sw.setSize(256,256);
}
public void netImageSelector(String st) {
       Toolkit tk = Toolkit.getDefaultToolkit();
       Image img;
       URL url=null;
       
      System.out.println("Loading..."+st);
 	  try {url=new URL(st);}
 	  
      catch (MalformedURLException e) {
                        e.printStackTrace();
         }
                
      img=tk.getImage(url);
      if (img == null) {
        System.out.println("netload failed");
        return;
       }
      setImage(img);
      setSize(width,height);
      

}

public void grab(Container f) {
  	width = f.getSize().width;
  	height = f.getSize().height;
  	Image backBuffer = createImage(width, height);
  	Graphics g = backBuffer.getGraphics();
  	f.paint(g);
  	setImage(backBuffer);
  	g = getGraphics();
  	g.clearRect(0,0,getSize().width,getSize().height);
  	setSize(width,height);
  	repaint();
}

public void grabTestPattern() {
		if (sw == null) {
			testPattern();
		}
		grab(sw);
}
	
public static void main(String args[]) {
   	GrabFrame gf = new GrabFrame("Grab Frame");
   	gf.testPattern();
}
}
 

