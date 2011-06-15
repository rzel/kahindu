package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class IconFrame 
	extends ClosableFrame implements ActionListener {
	
	private Panel iconPanel = new Panel(new FlowLayout());


	IconComponent eraserIcon = 
			getIconComponent(eraser);
	IconComponent pencilIcon = 
			getIconComponent(pencil);
	IconComponent brushIcon = 
			getIconComponent(brush);
	IconComponent handIcon = 
			getIconComponent(hand);
	IconComponent magnifyingGlassIcon = 
			getIconComponent(magnifyingGlass);
	IconComponent marqeeIcon = 
			getIconComponent(marqee);
	IconComponent paintCanIcon = 
			getIconComponent(paintCan);
	IconComponent eyeDropperIcon = 
			getIconComponent(eyeDropper);
	IconComponent xImageIcon = 
			getIconComponent(xImage);
	//IconComponent warpIcon = 
	//		getIconComponent(warp);
			
			
	Vector iconList = new Vector();
	private void addIcons() {
		addIcon(eraserIcon,iconPanel);
		addIcon(pencilIcon,iconPanel);
		addIcon(brushIcon,iconPanel);
		//addIcon(handIcon, iconPanel);
		addIcon(magnifyingGlassIcon,iconPanel);
		//addIcon(marqeeIcon,iconPanel);
		//addIcon(paintCanIcon,iconPanel);
		addIcon(eyeDropperIcon,iconPanel);
		addIcon(xImageIcon,iconPanel);
		//addIcon(warpIcon,iconPanel);		
		add(iconPanel);
	}
	public IconComponent getIconComponent(byte icon[][]) {
		IconComponent ic = new IconComponent(icon);
		return ic;
	}
	public void addIcon(IconComponent ic, Container c) {
		ic.addActionListener(this);
		c.add(ic);
		iconList.addElement(ic);
	}
	
	public void deselectOtherIcons(IconComponent selectedIcon) {
		for (int i=0; i < iconList.size(); i++) {
			IconComponent ic = (IconComponent)iconList.elementAt(i);
			ic.invert(ic.equals(selectedIcon));
		}
	}
	public Label width = new Label("W:0   ");
	public Label height = new Label("H:0   ");
	public Label red = new Label("R:0   ");
	public Label green = new Label("G:0   ");
	public Label blue = new Label("B:0   ");
	public Label xPosition = new Label("x:0   ");
	public Label yPosition = new Label("y:0   ");
	
	private void addInfo() {
		Panel p = new Panel(new GridLayout(1,2));
		p.add(width);
		p.add(height);
		p.add(red);
		p.add(green);
		p.add(blue);
		p.add(xPosition);
		p.add(yPosition);		
		
		add(p, BorderLayout.CENTER);

	}

	public IconFrame() {
		super("Kahindu Tool Bar");
		setLayout(new GridLayout(0,1));
		addIcons();
		addInfo();
		pack();	
	}
	public void setLabels(int w, int h,int r, int g, int b) {
		width.setText("W:"+w);
		height.setText("H:"+h);
		red.setText("R:"+r);
		green.setText("G:"+g);
		blue.setText("B:"+b);
		repaint();
	}
	
	public void setPosition(int x, int y) {
		xPosition.setText("x:"+x);
		yPosition.setText("y:"+y);
	}


	public static void main(String args[]) {
		System.out.println("Icon concepts!");
		IconFrame iconFrame = new IconFrame();
		iconFrame.setVisible(true);
	}
	
	IconComponent selectedIcon;
	
	public IconComponent getSelectedIcon() {
		return selectedIcon;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println(e);
		
		selectedIcon = (IconComponent)e.getSource();
		deselectOtherIcons(selectedIcon);
		if (selectedIcon == handIcon) {
			System.out.println("Handy!");
		}
	}
	private static byte eraser[][] = {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
{1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1},
{1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0},
{1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0},
{1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1},
{1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,1},
{1,1,1,0,1,1,1,1,1,0,1,0,0,1,1,1},
{1,1,0,0,0,0,0,0,0,1,0,0,1,1,1,1},
{1,1,0,1,1,1,1,1,0,0,0,1,1,1,1,1},
{1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,1},
{1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
			
public static byte brush[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
{1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
{1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
{1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
{1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
{1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
{1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1},
{1,1,1,0,1,0,1,0,1,0,1,0,0,1,1,1},
};

public static byte hand[][] = {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1},
{1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1},
{1,1,0,1,1,0,0,1,1,0,1,1,0,1,1,1},
{1,1,0,1,1,0,0,1,1,0,1,1,0,1,0,1},
{1,1,1,0,1,1,0,1,1,0,1,1,0,0,1,0},
{1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0},
{1,0,0,1,0,1,1,1,1,1,1,1,0,1,1,0},
{0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0},
{0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1},
{1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1},
{1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
{1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1},
};
public static byte magnifyingGlass[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1},
{1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1},
{1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1},
{1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1},
{1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1},
{1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1},
{1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1},
{1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1},
{1,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1},
{1,1,1,1,1,0,0,0,0,1,0,0,0,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
};
public static byte marqee[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,0,0,0,1,1,0,0,0,0,1,1,0,0,0,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
public static byte paintCan[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1},
{1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
{1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
{1,1,1,1,1,0,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1},
{1,1,1,1,1,0,1,1,0,1,1,0,0,1,1,1},
{1,1,1,1,0,1,1,1,0,1,1,1,0,0,1,1},
{1,1,1,0,1,1,1,0,1,0,1,1,0,0,1,1},
{1,1,0,1,1,1,1,1,0,1,1,1,0,0,1,1},
{1,1,0,1,1,1,1,1,1,1,1,0,0,0,1,1},
{1,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1},
{1,1,1,1,0,1,1,1,1,0,1,1,0,0,1,1},
{1,1,1,1,1,0,1,1,0,1,1,1,0,0,1,1},
{1,1,1,1,1,1,0,0,1,1,1,1,0,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
public static byte eyeDropper[][] = {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
{1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
{1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,1},
{1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,1},
{1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1},
{1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1},
{1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1},
{1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1},
{1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1},
{1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1},
{1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1},
{1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1},
};

private static byte xImage[][] = {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1},
{1,1,1,0,0,1,1,1,1,1,1,1,0,0,1,1},
{1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
{1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
{1,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
{1,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1},
{1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
{1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
{1,1,1,0,0,1,1,1,1,1,1,1,0,0,1,1},
{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1},
{0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
private static byte pencil[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1},
{1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1},
{1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1},
{1,1,1,1,1,1,1,1,0,1,0,0,0,1,1,1},
{1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1},
{1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1},
{1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1},
{1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1},
{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
{1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1},
{1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
{1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1},
{1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
{1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1},
};

public static byte warp[][] = {

{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0},
{1,1,0,0,1,1,1,0,0,1,1,1,1,1,0,1},
{1,1,0,0,1,1,1,0,0,1,1,1,1,1,0,1},
{1,1,1,0,0,1,1,1,0,0,1,1,1,0,1,1},
{1,1,1,0,0,1,1,1,0,0,1,1,1,0,1,1},
{1,1,1,1,0,0,1,1,0,0,0,1,1,0,1,1},
{1,1,1,1,0,0,1,0,1,0,0,1,0,1,1,1},
{1,1,1,1,0,0,1,0,1,0,0,1,0,1,1,1},
{1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,1},
{1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,1},
{1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
{1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
	
}


