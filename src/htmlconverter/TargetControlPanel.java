package htmlconverter;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class TargetControlPanel extends Panel {

	Choice c;
	
	TargetControlPanel() {
		c = new Choice();
		c.addItem("Java");
		c.addItem("C");
		c.addItem("C++");
		c.select("Java");
		setLayout(new GridLayout(1, 3, 10, 10));
		add(new Label("Input Language:", Label.LEFT));
		add(c);
	}
	
}
		
		
