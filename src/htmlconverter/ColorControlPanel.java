package htmlconverter;
import java.awt.GridLayout;
import java.awt.Panel;

public class ColorControlPanel extends Panel {

	ColorControlField ccf0, ccf1, ccf2, ccf3;
	HtmlGenerator parent;

	ColorControlPanel(HtmlGenerator mf) {
		parent = mf;
		ccf0 = new ColorControlField(this, "Main Text:");
		ccf0.c0.select("Black");
		ccf0.c1.setEnabled(false);
		ccf1 = new ColorControlField(this, "Comments:");
		ccf1.c0.select("Black");
		ccf1.c1.select("Italic");
		ccf2 = new ColorControlField(this, "Strings:");
		ccf2.c0.select("Black");
		ccf2.c1.select("Plain");
		ccf3 = new ColorControlField(this, "Keywords:");
		ccf3.c0.select("Black");
		ccf3.c1.select("Bold");

		setLayout(new GridLayout(4, 1));
		add(ccf0);
		add(ccf1);
		add(ccf2);
		add(ccf3);
	}

}
