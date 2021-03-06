package gui;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Hashtable;

 public class BeanFrame extends ShortCutFrame
	implements  BeanInfo, ActionListener {
	public MenuItem 
			addMenuItem(Menu m, String s,String Command) {
		ComMenuItem cmi = 
			ComMenuItem.addMenuItem(m,s,Command,this);
		addMenuItemShortCut(cmi,s);
		return
			cmi;
	}

	public BeanFrame(String title) {
		super(title);
	}
	 public void keyTyped (KeyEvent e){
	 	super.keyTyped(e);
	 	invokeMenuitemForString(getPS());
	 }
	public void actionPerformed(ActionEvent e) {
		if (!(e.getSource() instanceof ComMenuItem)) {
			super.actionPerformed(e);
			return;
		}	
		ComMenuItem cmi = (ComMenuItem) e.getSource();
		invokeMenuItem(cmi);
	}
	public void invokeMenuItem(ComMenuItem cmi) {
		checkMenuItem(cmi);
		cmi.invoke();
	}
	public Method getMethod(Class cls, String methodName) 
		throws IntrospectionException {
		Method methods[] = cls.getMethods();
		for (int i = 0; i < methods.length; i++) 
			if (methods[i].getName().equals(methodName))
				return methods[i];
		throw new IntrospectionException("No method \""+ methodName);
	}
	
	public void addMenuItemShortCut(ComMenuItem cmi, String s) {
		String sc = getShortCutString(s);
		if (sc == null) return;
		h.put(sc, cmi);
	}
	
	public String getShortCutString(String s) {
		int i = s.indexOf(']');
		if (i < 0) return null;
		return s.substring(0,i+1);
	}
 

	public void invokeMenuitemForString(String s) {
		Object o = h.get(s);
		if (!(o instanceof ComMenuItem)) return;
 		invokeMenuItem((ComMenuItem) o);
 	}
	
	public void checkMenuItem(ComMenuItem cmi) {
 		if (cmi.m == null)
			try {
				Method m = getMethod(
					this.getClass(),cmi.commandString);
				cmi.m = m;
				}
			catch (Exception ex) 
				{System.out.println(ex);}
	}
	protected Hashtable h = new Hashtable();
    public BeanDescriptor getBeanDescriptor() {
	return null;
    }

    /**
     * Deny knowledge of properties. You can override this
     * if you wish to provide explicit property info.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return null;
    }

    /**
     * Deny knowledge of a default property. You can override this
     * if you wish to define a default property for the bean.
     */
    public int getDefaultPropertyIndex() {
	return -1;
    }

    /**
     * Deny knowledge of event sets. You can override this
     * if you wish to provide explicit event set info.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return null;
    }

    /**
     * Deny knowledge of a default event. You can override this
     * if you wish to define a default event for the bean.
     */
    public int getDefaultEventIndex() {
	return -1;
    }

    /**
     * Deny knowledge of methods. You can override this
     * if you wish to provide explicit method info.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return null;
    }

    /**
     * Claim there are no other relevant BeanInfo objects.  You
     * may override this if you want to (for example) return a
     * BeanInfo for a base class.
     */
    public BeanInfo[] getAdditionalBeanInfo() {
	return null;
    }

    /**
     * Claim there are no icons available.  You can override
     * this if you want to provide icons for your bean.
     */
    public java.awt.Image getIcon(int iconKind) {
	return null;
    }

}