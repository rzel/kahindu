
package gui;



public class Hsb extends FloatPlane {
 
public Hsb(ColorFrame _cf) {
  	super(_cf);
}

private float hsbArray[] = new float[3];

 public void fromRgb() {      
      for (int x=0; x < width; x++) 
      	for (int y=0; y < height; y++) {
      		rgbtohsb(r[x][y],g[x][y],b[x][y],hsbArray);
			r[x][y] = hsbArray[0];
			g[x][y] = hsbArray[1];
			b[x][y] = hsbArray[2];
		}
		
   }

   public void toRgb()  {
      for (int x=0; x < width; x++) 
      	for (int y=0; y < height; y++) {
      		hsbtorgb(r[x][y],g[x][y],b[x][y],hsbArray);
			r[x][y] = hsbArray[0];
			g[x][y] = hsbArray[1];
			b[x][y] = hsbArray[2];
		}
		
   }

   public void hsbtorgb(float hue, float saturation, float brightness,float triplet[]) {
	int r = 0, g = 0, b = 0;
    	if (saturation == 0) {
	    r = g = b = (int) (brightness * 255);
	} else {
	    double h = (hue - Math.floor(hue)) * 6.0;
	    double f = h - java.lang.Math.floor(h);
	    double p = brightness * (1.0 - saturation);
	    double q = brightness * (1.0 - saturation * f);
	    double t = brightness * (1.0 - (saturation * (1.0 - f)));
	    switch ((int) h) {
	    case 0:
		r = (int) (brightness * 255);
		g = (int) (t * 255);
		b = (int) (p * 255);
		break;
	    case 1:
		r = (int) (q * 255);
		g = (int) (brightness * 255);
		b = (int) (p * 255);
		break;
	    case 2:
		r = (int) (p * 255);
		g = (int) (brightness * 255);
		b = (int) (t * 255);
		break;
	    case 3:
		r = (int) (p * 255);
		g = (int) (q * 255);
		b = (int) (brightness * 255);
		break;
	    case 4:
		r = (int) (t * 255);
		g = (int) (p * 255);
		b = (int) (brightness * 255);
		break;
	    case 5:
		r = (int) (brightness * 255);
		g = (int) (p * 255);
		b = (int) (q * 255);
		break;
	    }
	}
	triplet[0]=r;
	triplet[1]=g;
	triplet[2]=b;
    }
    
   public void rgbtohsb(float r, float g, float b, float[] hsbvals) {
	float hue, saturation, brightness;
    	float cmax = (r > g) ? r : g;
	if (b > cmax) cmax = b;
	float cmin = (r < g) ? r : g;
	if (b < cmin) cmin = b;

	brightness = ((float) cmax) / 255.0f;
	if (cmax != 0)
	    saturation = ((float) (cmax - cmin)) / ((float) cmax);
	else
	    saturation = 0;
	if (saturation == 0)
	    hue = 0;
	else {
	    float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
	    float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
	    float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
	    if (r == cmax)
		hue = bluec - greenc;
	    else if (g == cmax)
	        hue = 2.0f + redc - bluec;
            else
		hue = 4.0f + greenc - redc;
	    hue = hue / 6.0f;
	    if (hue < 0)
		hue = hue + 1.0f;
	}
	hsbvals[0] = hue;
	hsbvals[1] = saturation;
	hsbvals[2] = brightness;
    }


}

