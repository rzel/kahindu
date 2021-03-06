package gui;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

public class EventTester extends 
	ShortCutFrame  {
	Menu m1 = new Menu("Event Menu");
	MenuItem item1_mi = addMenuItem(m1,"[1] one");
	MenuItem item2_mi = addMenuItem(m1,"[T-2] two");
	
	Menu hierarchicMenu = new Menu("Hierarchic Menu");
	MenuItem itemH1_mi = addMenuItem(hierarchicMenu,"[E-3] three");
	MenuItem itemH2_mi = addMenuItem(hierarchicMenu,"[E-T-4] four");
	
	public static void main(String args[]) {
		EventTester et = new EventTester("Event Tester");
	}
	
	public EventTester(String title) {
		super(title);
		initMenuBar();
		show();
	}
	
	public void initMenuBar() {		
		MenuBar mb = new MenuBar();
		m1.add(hierarchicMenu);
		mb.add(m1);
		setMenuBar(mb);
	}
	public void paint(Graphics g) {
		g.drawString("event tester",50,50);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if (match(e, item1_mi))
			System.out.println("Item 1!");
		else if (match(e, item2_mi))
			System.out.println("Item 2!");
		else if (match(e, itemH1_mi))
			System.out.println("Item h1!");
		else if (match(e, itemH2_mi))
			System.out.println("Item h2!");
		super.actionPerformed(e);  

	}
}