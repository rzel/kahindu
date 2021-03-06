package htmlconverter;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonControlPanel 
	extends Panel implements ActionListener {

	HtmlGenerator parent;
	Button b0, b1, b2, b3;
	
 	public void add(Button b) {
 		super.add(b);
 		b.addActionListener(this);
 	}
 	
 	public void actionPerformed(ActionEvent e) {
 		parent.actionPerformed(e);
 	}

	ButtonControlPanel(HtmlGenerator mf) {
		parent = mf;
		b0 = new Button(parent.MI_OPEN);
		b1 = new Button(parent.MI_SAVE);
		b2 = new Button(parent.MI_CONVERTALL);
		b3 = new Button(parent.MI_QUIT);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(b0);
		add(b1);
		add(b2);
		add(b3);
		
	}
		
}
