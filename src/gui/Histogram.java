/**
 * Victor Silva - University of Bridgeport 27 April 1997
 *
 * Computes and displays the histogram for each primary
 * color of an image that was previously read.
 *
 * modified by DL 3/12/98...made 2D, added CMF, etc.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.PrintJob;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import dclap.SavePICT;

public class Histogram extends ShortCutFrame 
	implements ActionListener{

   private int FRAME_WIDTH = 275;
   private int FRAME_HEIGHT = 150;
   private int Xsize=(int)
   	getToolkit().getScreenSize().width;
   private int Ysize=(int)
   	getToolkit().getScreenSize().height;

   private Color drawColor;
   private int numPixels;
   // k = number of intensities
   private static final int k = 256;
   private double  PMF[] = new double[k];
   private double  CMF[] = new double[k];
   private static int startx=256;
   private static int starty=0;
   
   private Menu fileMenu = new Menu("File");
   private MenuItem print_mi = addMenuItem(fileMenu,"[p]rint");
   private MenuItem save_mi = addMenuItem(fileMenu,"[s]ave as pict");
   private Font f = new Font("Times", Font.PLAIN, 12);
   
   private double getMax(double a[]) {
   	double max = -1;
   	for (int i=0; i <a.length; i++) {
   		if (a[i] > max) max = a[i];
   	}
   	return max;
   }

   
   private void initMenu() {
	MenuBar mb = new MenuBar();
	mb.add(fileMenu);
	setMenuBar(mb);
   }

 public double[] getPMF() {
   	return PMF;
   }
   
   public double[] getCMF() {
   	return CMF;
   }
   
   private void computeCMF() {
    double cumulative = 0;
   	for (int i = 0; i < PMF.length; i++) {
   		cumulative = cumulative + PMF[i];
   		CMF[i] = cumulative;
   	}
   }

private void moveFrame(int x, int y) {
	setBounds(x, y, 
		FRAME_WIDTH, FRAME_HEIGHT);
}

public void myShow() {
	moveFrame(startx, starty);
	starty += FRAME_HEIGHT;
	if (starty *1.5 > Ysize) starty=0;
	super.setVisible(true);
}

   
   public Histogram(short plane[][], String title) {
       super(title);
      int width = plane.length;
      int height = plane[0].length;
      setSize(FRAME_WIDTH, FRAME_HEIGHT);  
      initMenu();
      setResizable(true); 
      int total = 0;    

      for(int i=0; i<width; i++)
      	for (int j=0; j < height; j++) {
         PMF[plane[i][j] & 0xFF]++;
         total++;
        }
      // Normalize
      for(int i=0; i<256; i++)
         PMF[i] = (PMF[i] / total);
      computeCMF();
   }

public void printPMF() {
	System.out.println("PMF");
	for (int i=0; i < PMF.length; i++) {
		System.out.println(PMF[i]);
	}
}

public void printCMF() {
	System.out.println("CMF");
	for (int i=0; i < CMF.length; i++) {
		System.out.println(CMF[i]);
	}
}

  private  void print() {
		Toolkit tk = Toolkit.getDefaultToolkit();
			PrintJob printJob = 
		    	tk.getPrintJob(
		    		this,
		    		"print me!",
		    		null);
			Graphics g = printJob.getGraphics();
			print(g);
			printJob.end();
 }
 
 private void save() {
 	FileDialog fd = new FileDialog(this,
 		"Enter a pict file name",FileDialog.SAVE);
 	fd.show();
 	fd.setVisible(false);
 	String fn = fd.getDirectory()+fd.getFile();
 	//if usr canceled, return
 	if (fd.getFile() == null) return;
 	try {
 	 FileOutputStream fos = 
		new FileOutputStream(fn);
    	Component c = (Component) this;
     	SavePICT p = new SavePICT();
    	p.saveAsPict( c,(OutputStream)fos);
    	fos.close();
	
	} catch (IOException e) {
		System.out.println(e);
	}
 	
 }
 
 private void drawYAxis(Graphics g, int x, int y) {
 	g.drawLine(x,0, x, y);
 }
 
 public void actionPerformed(ActionEvent e) {

	if (match(e, save_mi)) {
		save();
		return;
	}
	if (match(e, print_mi)) {
		print();
		return;
	}
	super.actionPerformed(e);  

  }
	
  public void update(Graphics g) {
   Rectangle r = getBounds();
   FRAME_WIDTH = r.width;
   FRAME_HEIGHT = r.height;
   g.clearRect(0,0,r.width, r.height);
   paint(g);
 }

  public void paint(Graphics g) {
   int  x2, y2;
   int leftMargin = 15;
   int topMargin = 10;
   int bottomMargin = FRAME_HEIGHT-45;
   int stringY;
   g.setFont(f);
   float max = (float)getMax(PMF);
   float yscale = (float) (0.9*bottomMargin/max);
   
   g.drawString("PMF max="+max,
    	leftMargin, bottomMargin+12);
    	
   for(int i=0,x1=leftMargin; i<256; i++,x1=i+leftMargin)      
       g.drawLine(x1, bottomMargin, x1, 
          bottomMargin  - (int)(yscale*PMF[i]));      
   }

}