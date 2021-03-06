/**
 * Victor Silva - University of Bridgeport 20 Feb. 1997
 * Doug Lyon - revised heavily 8/3/98.
 *
 * Use this class by calling HLS.fromRGB to convert an
 * int array containing image pixels into HLS color space.
 *
 * HLS and RGB Converter class.  This class allows conversion
 * between RGB and HLS (Hue, Lightness and Saturation).
 * The conversion algorithm is from Foley and van Dam
 * "Computer Graphics".
 // there is a bug here someplace....oops...I just have
 // no time to fix it now....8/14/98 DL.
 *
*/
package gui;



public class Hls extends FloatPlane {
 
public Hls(ColorFrame _cf) {
  	super(_cf);
}


 public void fromRgb() {
 	printStatistics();
 	normalize(); 
 	printStatistics();
      float max = 0f;
      float min = 255f;
      float h=0;
      float l=0;
      float s=0;
      float delta=0;
      
      for (int x=0; x < width; x++) 
      	for (int y=0; y < height; y++) {
      		max = max(r[x][y],g[x][y],b[x][y]);
      		min = min(r[x][y],g[x][y],b[x][y]);
      		l = (max + min) / 2;     		
      		if (max == min) {
				s = 0; // Achromatic case
				h = 0; // h is really undefined
				r[x][y] = h;
				g[x][y] = l;
				b[x][y] = s;
				continue;
			}
			delta = max - min;
			
           // Calculate saturation
            if (l <= 0.5) 
            	s = delta / (max + min);
            else
            	s = delta / (2-delta);
            
            if (r[x][y] == max) 
            	h = (g[x][y] - b[x][y]) / delta;
            else
            	if (g[x][y] == max) 
            		h = (float)(2.0 + (b[x][y] - r[x][y]) / delta);
            else
            	if (b[x][y] == max) 
            		h = (float)(4.0 + (r[x][y] - g[x][y]) / delta);
            h *= 60;
            if (h < 0) h += 360; // no negative degrees
            r[x][y] = h;
            g[x][y] = l;
            b[x][y] = s;
     	}

   }



// assume that luminance is stored in HLS_g
public void autoScale() {
	float max = getMax(g);
	float min = getMin(g);
	System.out.println("Max luminance = " + max);
	System.out.println("Min luminance = " + min);
	float sf = (float) (255.0 / (max - min));
	System.out.println("Scaling in luminance by" + sf);
	for (int x=0; x < width; x++) 
		for (int y=0; y < height; y++) 
			g[x][y] *= sf;
}


   public void toRgb()  {
     int totalPix = r.length;

      double m1, m2;
      float h,l,s;
      double R, G, B;

      for (int x=0; x < width; x++) 
      	for (int y=0; y < height; y++) {
      		h = r[x][y];
      		l = g[x][y];
      		s = b[x][y];
      		if (s == 0.0) {
      			r[x][y] = l;
      			g[x][y] = l;
      			b[x][y] = l;
      			continue;
      		}
            m2 = (l <= 0.5) ? (l * (l + s)) : (l + s - (l * s));
            m1 = 2.0 * l - m2;
            r[x][y] = value(m1, m2, h + 120.0);
            g[x][y] = value(m1, m2, h);
            b[x][y] = value(m1, m2, h - 120.0);
     	}
     scale(255);
   }

   float value(double n1, double n2, double hue) {
      if (hue > 360.0)
         hue -= 360.0;
      else
         if (hue < 0.0) hue += 360.0;

      if (hue < 60.0) 
         return((float)(n1 + ((n2 - n1) * hue) / 60.0));

      else
      {
         if (hue < 180.0) return((float)n2);
         else
            if (hue < 240.0)
               return((float)(n1 + ((n2 - n1) * (240.0 - hue)) / 60.0));
            else return((float)n1);
      }
   }

}

