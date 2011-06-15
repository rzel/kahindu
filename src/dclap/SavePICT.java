// savepict.java
// d.g.gilbert, 1997

package dclap;
// pack edu.indiana.bio.dclap;


import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextComponent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;



public class SavePICT{
  Applet app = null;
  
  public SavePICT() { }

  public SavePICT( Applet app) { this.app= app; }
  
  protected void showStatus( String msg) {
    if (app==null) System.out.println( msg);  
    else app.showStatus( msg);
    }
      
      
  public boolean saveToFile(PrintStream ps, String data) {  
    boolean okay= false;
    try {
      ps.print(data);
      ps.close();
      showStatus("Printing Complete.");
      okay= true;
      } 
    catch (Throwable t) {
      showStatus("Error while saving file.");
      System.out.println(t);
      }
    return okay;
  } 

  public void saveAsPict(Component theView, OutputStream os) {
    Gr2PICT pict= new Gr2PICT(os, theView.getGraphics());
    theView.paintAll(pict);  
    paintInside(theView,pict);
    pict.finalize(); // make sure pict end is written
  }
  
  /**
  	toFile - saves any Frame to a pict file.
  	@author Douglas Lyon
  	@date 1/7/99
  	@version Kahindu 2.5
  */
  public static void toFile(Frame f) {
   	FileDialog fd = new FileDialog(f,
 		"Enter a pict file name",FileDialog.SAVE);
 	fd.show();
 	fd.setVisible(false);
 	String fn = fd.getDirectory()+fd.getFile();
 	//if usr canceled, return
 	if (fd.getFile() == null) return;
 	try {
 	 FileOutputStream fos = 
		new FileOutputStream(fn);
    	Component c = (Component) f;
     	SavePICT p = new SavePICT();
    	p.saveAsPict( c,(OutputStream)fos);
    	fos.close();
	
	} catch (IOException e) {
		System.out.println(e);
	}
  }

  

  protected void paintInside(Component comp, Gr2PICT g) {
    Point p = comp.getLocation();

      // set the origin for this component
    g.translate( p.x, p.y);    

      // now draw this component's children inside its coordinate system
    if (comp instanceof Container) {
      Component[] comps= ((Container) comp).getComponents();
      for (int i=0; i<comps.length; i++)
        paintInside(comps[i], g);
      }
    else
    	updateComponent(comp,g);

      // restore the coordinate system
    g.translate( -p.x, -p.y);
  }
  

  protected void updateComponent(Component c, Graphics g) {
    // draw a few special types of Component
    Rectangle b = c.getBounds();
    int halfheight = b.height/2;
    Color backc= c.getBackground(); // Color.white
    Color forec= c.getForeground(); // Color.black;
    
    if (c instanceof Button) {
      if (c.getFont() != null)  g.setFont(c.getFont());
      g.setColor(backc);  
      g.fillRoundRect(0,0,b.width, b.height, 12,12);
      g.setColor(forec);  
      g.drawRoundRect(0,0,b.width, b.height, 12,12);
      String labs= ((Button) c).getLabel();
      int x= (b.width - g.getFontMetrics().stringWidth(labs)) / 2;
      g.drawString(labs, x, halfheight+3);
      } 
      
    else if (c instanceof Checkbox) {
      Checkbox cbx= (Checkbox) c;
      int cwide= 12;
      int cwide2= cwide/2;
      int cwide3= (cwide - cwide2) / 2;
      int x= 3, y = 3;
      if (c.getFont() != null) g.setFont(c.getFont());
      
      if (cbx.getCheckboxGroup() != null) {
        // radio button
        g.setColor(backc);  
        g.fillOval(x,y,cwide,cwide);
        g.setColor(forec);  
        g.drawOval(x,y,cwide,cwide);
        if (cbx.getState()) g.fillOval(x+cwide3,y+cwide3,cwide2,cwide2);
        }
        
      else {
        // checkbox
        g.setColor(backc);  
        g.fillRect(x,y,cwide,cwide);
        g.setColor(forec);  
        g.drawRect(x,y,cwide,cwide);
        if (cbx.getState()) {
          g.drawLine(x,y,x+cwide-1,y+cwide-1);
          g.drawLine(x+cwide-1,y,x,y+cwide-1);
          }
        }
      x += cwide+2;
      g.drawString(cbx.getLabel(), x+2, halfheight+3);
      } 
      
    else if (c instanceof Choice) {
      if (c.getFont() != null)  g.setFont(c.getFont());
      g.setColor(Color.black);  
      g.fillRect(3,3,b.width-4, b.height-4);
      g.setColor(Color.white);  
      g.fillRect(0,0,b.width-2, b.height-2);
      g.setColor(forec);  
      g.drawRect(0,0,b.width-2, b.height-2);

        // drop arrow
      int[] xp= { b.width - 16, b.width - 6,  b.width - 11, b.width - 16};
      int[] yp= { halfheight - 3, halfheight - 3,  halfheight + 2, halfheight - 3};
      g.fillPolygon( xp, yp, 4);  
      
      g.drawString(((Choice) c).getSelectedItem(), 4, halfheight+3);
      } 
      
    else if (c instanceof Label) {
      if (c.getFont() != null)  g.setFont(c.getFont());
      g.setColor(forec);  
      g.drawString(((Label) c).getText(), 2, halfheight+3);
      } 
      
    else if (c instanceof TextComponent) {
      if (c.getFont() != null)  g.setFont(c.getFont());
      g.setColor(backc);  
      g.fillRect(0,0,b.width, b.height);
      g.setColor(forec);  
      g.drawRect(0,0,b.width, b.height);
      g.drawString(((TextComponent) c).getText().trim(), 2, halfheight+3);
      } 
      
    else {
      // let it draw itself
      c.update(g);
      }
  }

}