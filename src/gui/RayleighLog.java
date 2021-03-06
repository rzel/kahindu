package gui;
import java.awt.event.ActionEvent;
public class RayleighLog extends 
	DoubleLog{
private NegateFrame parent;

public RayleighLog(
		NegateFrame _parent,
		String title, 
		String prompts[], 
		String defaults[], 
		int fieldSize) {	
	super(_parent, title, prompts, defaults,fieldSize);
	parent = _parent;
	setButton.addActionListener(this);
	cancelButton.addActionListener(this);
}

public void actionPerformed(ActionEvent e) { 
  if (e.getSource() == setButton) {
  	double dui[] = getUserInputAsDouble(); 
  	parent.rnahe(dui[0]);
  	System.out.println("Invoking rnahe");
  	return ;
  }
  super.actionPerformed(e);
	}
}
