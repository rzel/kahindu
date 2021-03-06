package gui;
import java.awt.Menu;
import java.awt.MenuItem;
import java.lang.reflect.Method;

class ComMenuItem extends MenuItem {
	public Method m;
	private BeanFrame bf;
	public String commandString;
	
	ComMenuItem(String item) {
		super(item);
	}
	
	public void invoke() {
		String targetParameters[] = null;
		try
			{m.invoke(bf,targetParameters);}
		catch (Exception ex) {System.out.println(ex);};
	}
	static ComMenuItem addMenuItem(
			Menu aMenu, 
			String itemName,
			String _command, 
			BeanFrame _bf) {
	  ComMenuItem cmi = new ComMenuItem(itemName);
	  cmi.bf = _bf;
	  cmi.addActionListener(cmi.bf);
	  aMenu.add(cmi);
	  cmi.commandString = _command;
	  return(cmi);
	}
	
	public static String trimShortCutString(String s) {
		int i = s.indexOf(']');
		if (i < 0) return s;
		return s.substring(i+1,s.length());
	}


}
