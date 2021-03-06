package gui;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class EdgeFrame extends SpatialFilterFrame 
	implements Doable {

 Menu laplacianMenu = new Menu("Laplacian");
  Menu edgeMenu = new Menu("Edge");
 Menu templateMenu = new Menu("Template");
 Menu threshMenu = new Menu("Threshold");

  
 MenuItem laplacian5_mi = addMenuItem(laplacianMenu,"[E-T-4] 5");
 MenuItem laplacian3_mi = addMenuItem(laplacianMenu,"[E-T-5] 3");
 MenuItem laplacian3Minus_mi = addMenuItem(laplacianMenu,"[E-T-6] 3 Minus");
 MenuItem laplacian3Prewitt_mi = addMenuItem(laplacianMenu,"[E-T-p]3Prewitt");
 MenuItem laplacian3_4_mi = addMenuItem(laplacianMenu,"[E-T-l]3_4");
 MenuItem laplacian9_mi = addMenuItem(laplacianMenu,"[E-T-7]9");
 MenuItem hat13_mi = addMenuItem(laplacianMenu,"[E-T-8]hat 13x13");
 MenuItem hat13v2_mi = addMenuItem(laplacianMenu,"[E-T-8]hat 13x13 v2");

 MenuItem pixelDifference_mi = addMenuItem(templateMenu,"Pixel Difference");
 MenuItem roberts2_mi = addMenuItem(edgeMenu,"[E-T-r]oberts 2");
 MenuItem medianSquare2x2_mi = addMenuItem(edgeMenu,"[E-)]medianSquare2x2 ");
 MenuItem median2x1_mi = addMenuItem(edgeMenu,"[E-}]median2x1");
 MenuItem median1x2_mi = addMenuItem(edgeMenu,"[E-{]median1x2");
 MenuItem magOfDerivativeOfGauss13_mi = addMenuItem(edgeMenu,"magOfDerivativeOfGauss13");
 

 MenuItem sobel3_mi = addMenuItem(templateMenu,"[E-T-2]Sobel3");
 MenuItem separatedPixelDifference_mi = 
 	addMenuItem(templateMenu,"Separated Pixel Difference");
 MenuItem prewitt_mi = addMenuItem(templateMenu,"Prewitt");

 MenuItem freiChen_mi = addMenuItem(templateMenu,"Frei-Chen"); 

 MenuItem zeroCross_mi = addMenuItem(edgeMenu,"[E-T-z]eroCross");
 MenuItem sizeDetector_mi = addMenuItem(edgeMenu,"[E-T-s]ize detector");
 MenuItem printVariance_mi = addMenuItem(edgeMenu,"[E-T-p]rintVariance");



 MenuItem printSigma_mi = addMenuItem(edgeMenu,"printSigma"); 
 
 MenuItem thresh_mi = addMenuItem(threshMenu,"[E-T-9]thresh");
 MenuItem threshold_mi = addMenuItem(threshMenu,"[E-T]hreshold...");


 
 public void actionPerformed(ActionEvent e) {

    	if (match(e, magOfDerivativeOfGauss13_mi)) {
	    	magOfDerivativeOfGauss13();
	    	return;
	    }
    	if (match(e, median2x1_mi)) {
	    	median2x1();
	    	return;
	    }
    	if (match(e, median1x2_mi)) {
	    	median1x2();
	    	return;
	    }
    	if (match(e, medianSquare2x2_mi)) {
	    	medianSquare2x2();
	    	return;
	    }
    	if (match(e, freiChen_mi)) {
	    	freiChen();
	    	return;
	    }
    	if (match(e, prewitt_mi)) {
	    	prewitt();
	    	return;
	    }
    	if (match(e, separatedPixelDifference_mi)) {
	    	separatedPixelDifference();
	    	return;
	    }
    	if (match(e, threshold_mi)) {
	    	threshLog();
	    	return;
	    }

 	    if (match(e, pixelDifference_mi)) {
	    	pixelDifference();
	    	return;
	    }
 	    if (match(e, roberts2_mi)) {
	    	roberts2();
	    	return;
	    }
 	    if (match(e, printSigma_mi)) {
	    	printSigma();
	    	return;
	    }
 	    if (match(e, printVariance_mi)) {
	    	printVariance();
	    	return;
	    }
 	    if (match(e, laplacian3_4_mi)) {
	    	laplacian3_4();
	    	return;
	    }
 	    if (match(e, sizeDetector_mi)) {
	    	sizeDetector();
	    	return;
	    }
 	    if (match(e, zeroCross_mi)) {
	    	zeroCross();
	    	return;
	    }
 	    if (match(e, laplacian3Prewitt_mi)) {
	    	laplacian3Prewitt();
	    	return;
	    }
 	    if (match(e, laplacian3Minus_mi)) {
	    	laplacian3Minus();
	    	return;
	    }
 	    if (match(e, thresh_mi)) {
	    	thresh();
	    	return;
	    }
	    if (match(e, sobel3_mi)) {
	    	sobel3();
	    	return;
	    }
	    if (match(e, hat13v2_mi)) {
	    	hat13v2();
	    	return;
	    }
	    if (match(e, hat13_mi)) {
	    	hat13();
	    	return;
	    }
	    if (match(e, shadowMask_mi)) {
	    	shadowMask();
	    	return;
	    }
	    if (match(e, laplacian3_mi)) {
	    	laplacian3();
	    	return;
	    }
	    if (match(e, laplacian5_mi)) {
	    	laplacian5();
	    	return;
	    }
	    if (match(e, laplacian9_mi)) {
	    	laplacian9();
	    	return;
	    }

	super.actionPerformed(e);  

 }

EdgeFrame(String title) {
		super(title);
		edgeMenu.add(threshMenu);
		edgeMenu.add(laplacianMenu);
		edgeMenu.add(templateMenu);
		SpatialFilterMenu.add(edgeMenu);
	}



	
public void colorToRed() {
   for (int x=0; x < width; x++) 
	for (int y=0; y < height; y++) 
		r[x][y] = (short)
		   ((r[x][y] + g[x][y]  + b[x][y]) / 3);
}

public void medianSquare2x2() {
 short k[][] = {
	{1, 1, 0},
	{1, 1, 0},
	{0, 0, 0}
	};
	median(k);
}

// Note to the reader...
// the private Magnitude of Gaussian stuff
// is experimental stuff...DL
private void magOfDerivativeOfGauss13() {
	float k[][] = getMagnitudeOfTheDerivativeOfGauss(13,13,1.0);
	System.out.println("Experimental!!");
	convolve(k);
}
 private static double getMagnitudeOfTheDerivativeOfGauss(
 		double x, double y, 
 		double xc, double yc, double sigma) {
	  t2 = Math.pow(2.0*x-2.0*xc,2.0);
      t3 = sigma*sigma;
      t4 = t3*t3;
      t5 = t4*t4;
      t6 = 1/t5;
      t9 = Math.pow(x-xc,2.0);
      t11 = Math.pow(y-yc,2.0);
      t16 = Math.pow(Math.exp(-(t9+t11)/t3/2),2.0);
      t17 = Math.PI*Math.PI;
      t19 = t16/t17;
      t22 = Math.pow(2.0*y-2.0*yc,2.0);
      double ddx = t2*t6*t19;
      double ddy = t22*t6*t19;
      t26 = Math.sqrt(ddx+ddy);
      t27 = t26/4;
	  return t27;
  }
 private static void printMagnitudeOfTheDerivativeOfGauss(
		int M, int N, 
		double sigma) {
	float k[][] = getMagnitudeOfTheDerivativeOfGauss(M, N, sigma);
	//Mat.printKernel(k,"MagnitudeOfTheDerivativeOfGauss"+k.length);
}

private static float [][] getMagnitudeOfTheDerivativeOfGauss(
		int M, int N, 
		double sigma) {
	float k[][] = new float[M][N];
	int xc = M / 2;
	int yc = N / 2;
	for (int x=0; x < k.length; x++)
		for (int y=0; y < k[0].length; y++)
			k[x][y] = (float)
				getMagnitudeOfTheDerivativeOfGauss(x,y,xc,yc,sigma);
	Mat.normalize(k);
	return k;
} 
public void median2x1() {
 short k[][] = {
	{0, 1, 0},
	{0, 1, 0},
	{0, 0, 0}
	};
	median(k);
}
public void median1x2() {
 short k[][] = {
	{0, 0, 0},
	{1, 1, 0},
	{0, 0, 0}
	};
	median(k);
}
public void roberts2() {
   colorToRed();
   int p[] = new int[4];
   float delta_u = 0;
   float delta_v = 0;
   short t;
   for (int x=0; x < width-1; x++) 
	for (int y=0; y < height-1; y++) {
		p[0] = r[x][y];
		p[1] = r[x+1][y];
		p[2] = r[x][y+1];
		p[3] = r[x+1][y+1];
		delta_u = p[0] - p[3];
		delta_v = p[1] - p[2];
		t = (short)
		   Math.sqrt(delta_u*delta_u + delta_v*delta_v);
		//if (t > 48) t = 255;
		//else t=0;
		r[x][y] = t;
		g[x][y] = t;
		b[x][y] = t;
   }
   short2Image();

}
	

public void shadowMask() {
 float k[][] = {
	{-2, -1,  0},
	{-1,  0,  1},
	{0,   1,  2}
	};
  	convolve(k);
 }
 
public void sizeDetector() {
    r = sizeDetector(r);
    g = sizeDetector(g);
    b = sizeDetector(b);
  	short2Image();
}

 // p0 p1 p2
 // p3 p4 p5
 // p6 p7 p8
 // 
 //
 public short[][] sizeDetector(short f[][]) {
 	short a[][] = new short[f.length][f[0].length];
 	int p[] = new int[9];
 	int sum = 0;
 	for (int x = 1; x < f.length-1; x++)
 		for (int y=1; y < f[0].length-1; y++) {
 			sum = 0;
 			p[0] = f[x-1][y+1];
 			p[1] = f[x][y+1];
 			p[2] = f[x+1][y+1];
 			p[3] = f[x-1][y];
 			p[4] = f[x][y];
 			p[5] = f[x+1][y];
 			p[6] = f[x-1][y-1];
 			p[7] = f[x][y-1];
 			p[8] = f[x+1][y-1];
 			for (int i=0;i<p.length; i++) 
 			   sum += p[i];
 			if (sum > 255*5)
 				a[x][y] = 255;
 			else a[x][y] = 0;
 		}
 	return a;
 }

public void sobel3() {
 float k1[][] = {
	{-1,  -2, -1},
	{ 0,   0,  0},
	{ 1,   2,  1}
	};
 Mat.scale(k1,1/4.0);
 float k2[][] = {
	{1, 0,  -1},
	{2, 0,  -2},
	{1, 0,  -1}
	};
  Mat.scale(k2,1/4.0);

  	templateEdge(k1,k2);
 }
public void separatedPixelDifference() {
 float k1[][] = {
	{ 0,   0,  0},
	{ 1,   0, -1},
	{ 0,   0,  0}
	};
 float k2[][] = {
	{ 0,  -1,  0},
	{ 0,   0,  0},
	{ 0,   1,  0}
	};
  	templateEdge(k1,k2);
 }
public void prewitt() {
 float k1[][] = {
	{ 1,   0, -1},
	{ 1,   0, -1},
	{ 1,   0, -1}
	};
 
 float k2[][] = {
	{-1,  -1, -1},
	{ 0,   0,  0},
	{ 1,   1,  1}
	};
	Mat.scale(k1,1/3.0);
	Mat.scale(k2,1/3.0);
  	templateEdge(k1,k2);
 }
public void freiChen() {
 float r2 = (float) Math.sqrt(2);
 float k1[][] = {
	{ 1,    0,   -1},
	{ r2,   0,  -r2},
	{ 1,    0,   -1}
	};
 
 float k2[][] = {
	{-1, -r2, -1},
	{ 0,   0,  0},
	{ 1,  r2,  1}
	};
 double s = 1/(2+r2);
	Mat.scale(k1,s);
	Mat.scale(k2,s);
  	templateEdge(k1,k2);
 }
 
public void pixelDifference() {
 float k1[][] = {
	{0,   0,   0},
	{0,   1,  -1},
	{0,   0,   0}
	};
 float k2[][] = {
	{0, -1,  0},
	{0,  1,  0},
	{0,  0,  0}
	};
  	templateEdge(k1,k2);
 }
 
 
 public void templateEdge(float k1[][], float k2[][]) {
 	colorToRed();
 	//printMaple(k1,"k1=");
 	//printMaple(k2,"k2=");
 	g = convolve(r,k1);
 	b = convolve(r,k2);
 	short t = 0;
 	for (int x=0; x < width; x++) 
 		for (int y=0; y < height; y++) {
 		  t = (short)Math.sqrt(g[x][y]*g[x][y]+b[x][y]*b[x][y]);
		  r[x][y] = t;
 		  g[x][y] = r[x][y];
 		  b[x][y] = r[x][y];
 	}
 	short2Image();   
 }
public void printMaple(float a[][],String prefix) {
	System.out.print(prefix);
	super.printMaple(a);
}		

public void laplacian5() {
float k[][] = {
	{-1, -1, -1,  -1, -1},
	{-1, -1, -1,  -1, -1},
	{-1, -1, 24,  -1, -1},
	{-1, -1, -1,  -1, -1},
	{-1, -1, -1,  -1, -1}
	};
  	convolve(k);
 }
public void laplacian3() {
float k[][] = {
	{ 0,  -1,  0},
	{-1,   4, -1},
	{ 0,  -1,  0}
	};
	 convolve(k);
 }
public void laplacian3Prewitt() {
float k[][] = {
	{ -1,  -1, -1},
	{ -1,   8, -1},
	{ -1,  -1, -1}
	};
	//Mat.scale(k,1/8.0);
  	convolve(k);
 }

public void laplacian3_4() {
float k[][] = {
	{1,  -2,  1},
	{-2,  4, -2},
	{1,  -2,  1}
	};
  	convolve(k);
 }
public void laplacian3Minus() {
float k[][] = {
	{ 0,  1, 0},
	{ 1, -4, 1},
	{ 0,  1, 0}
	};
  	convolve(k);
 }
 
public static double laplaceOfGaussian(double x, double y, 
 		double xc, double yc, double sigma) {
	  t1 = sigma*sigma;
      t2 = t1*t1;
      t5 = Math.pow(x-xc,2.0);
      t7 = Math.pow(y-yc,2.0);
      t11 = Math.exp(-(t5+t7)/t1/2);
      t13 = 1/Math.PI;
      t16 = Math.pow(2.0*x-2.0*xc,2.0);
      t18 = 1/t2/t1;
      t20 = t11*t13;
      t23 = Math.pow(2.0*y-2.0*yc,2.0);
      t26 = 1/t2*t11*t13-t16*t18*t20/8-t23*t18*t20/8;
      return t26;
}
 
public void tGenerator(int min, int max) {
	String fn = getSaveFileName("t.java generator");
    try { 
    	FileOutputStream fos =
            new FileOutputStream(fn);
    PrintWriter ps = new PrintWriter(fos);
	for (int i = min; i < max; i++)
		ps.println(
			"static double t"
			+ i + " = 0;");
    fos.close();
    }
    catch (Exception e) {};
}

public static void main(String args[]) {
	EdgeFrame ef = new EdgeFrame("Edge Frame");
	ef.show();
	ef.tGenerator(0,2000);
}

 public void thresh() {
	Mat.threshold(r);
	Mat.threshold(g);
	Mat.threshold(b);
	short2Image();
}

 public void convolveZeroCross(float k[] []) {
 	// a 1kx1k image allocates not more than
 	// 1 MB at a time.
 	//Mat.print(k);
    r = convolveZeroCross(r,k);
    g = convolveZeroCross(g,k);
    b = convolveZeroCross(b,k);
  	short2Image();
 }
 public void zeroCross() {
 	// a 1kx1k image allocates not more than
 	// 1 MB at a time.
 	//Mat.print(k);
    r = zeroCross(r);
    g = zeroCross(g);
    b = zeroCross(b);
  	short2Image();
 }
 
 public short [][] convolveZeroCross(short a[][], float k[][]) {
 	a = convolve(a,k);
 	a = zeroCross(a);
 	return a;
 }
 // p0 p1 p2
 // p3 p4 p5
 // p6 p7 p8
 // 
 //
 public short[][] zeroCross(short f[][]) {
 	short a[][] = new short[f.length][f[0].length];
 	int p[] = new int[9];
 	for (int x = 1; x < f.length-1; x++)
 		for (int y=1; y < f[0].length-1; y++) {
 			p[1] = f[x][y+1];
 			p[3] = f[x-1][y];
 			p[5] = f[x+1][y];
 			p[7] = f[x][y-1];
 			if (((p[1] < 0) && (p[7] >= 0)) ||
 			   ((p[1] >= 0) && (p[7] < 0)) ||
 			   ((p[3] < 0) && (p[5] >= 0)) ||
 			   ((p[3] >= 0) && (p[5] < 0))) 
 			   	a[x][y] = 255;
 	}
 	return a;
 }


public void laplacian9(){
	float k[][] = {
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1},
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1},
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1},
	{  -1,   -1,   -1,    8,    8,    8,   -1,   -1, -1},
	{  -1,   -1,   -1,    8,    8,    8,   -1,   -1, -1},
	{  -1,   -1,   -1,    8,    8,    8,   -1,   -1, -1},
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1},
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1},
	{  -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1, -1}};
//sum=0.0
	convolve(k);
}


public void hat13v2() {
	float k[][] = getLaplaceOfGaussianKernel(13,13,2.0);
	convolve(k);
	//Mat.printKernel(k,"hat13v2"+k.length);
}
public void hat13() {
  // Hat13 filter
  float k [][] = {
{ 0,  0,  0,  0,  0, -1,  -1, -1,   0,  0,   0,  0,  0},
{ 0,  0,  0, -1, -1, -2,  -2, -2,  -1, -1,   0,  0,  0},
{ 0,  0, -2, -2, -3, -3,  -4, -3,  -3, -2,  -2,  0,  0},
{ 0, -1, -2, -3, -3, -3,  -2, -3,  -3, -3,  -2, -1,  0},
{ 0, -1, -3, -3, -1,  4,   6,  4,  -1, -3,  -3, -1,  0},
{-1, -2, -3, -3,  4, 14,  19, 14,   4, -3,  -3, -2, -1},
{-1, -2, -4, -2,  6, 19,  24, 19,   6, -2,  -4, -2, -1},
{-1, -2, -3, -3,  4, 14,  19, 14,   4, -3,  -3, -2, -1},
{ 0, -1, -3, -3, -1,  4,   6,  4,  -1, -3,  -3, -1,  0},
{ 0, -1, -2, -3, -3, -3,  -2, -3,  -3, -3,  -2, -1,  0},
{ 0,  0, -2, -2, -3, -3,  -4, -3,  -3, -2,  -2,  0,  0},
{ 0,  0,  0, -1, -1, -2,  -2, -2,  -1, -1,   0,  0,  0},
{ 0,  0,  0,  0,  0, -1,  -1, -1,   0,  0,   0,  0,  0}};
  Timer t = new Timer();
  t.start();
  convolve(k);
  
  t.print("laplace convolution");
  //Mat.printKernel(k,"hat13"+k.length);
 }

 public void horizontalSegment() {
float mask [][] = { 
	{0, 0, 0},
	{1, 1, 1},
	{0, 0, 0}
	};
	Mat.normalize(mask);
  	convolve(mask);
 }
public void verticalSegment() {
float mask [][] = { 
	{0, 1, 0},
	{0, 1, 0},
	{0, 1, 0}
	};
	Mat.normalize(mask);
  	convolve(mask);
 }
   protected static double mean(short a[][]) {
  	double sum = 0;
  	for (int x =0; x < a.length;x++)
  		for (int y=0; y<a[0].length; y++) {
  		 sum += a[x][y];
  		}
  	return sum/ (a.length*a[0].length);
  }
  
  protected static double variance(short a[][]) {
  	double xBar = mean(a);
  	double sum = 0;
  	double dx = 0;
  	for (int x =0; x < a.length;x++)
  		for (int y=0; y<a[0].length; y++) {
  		dx = a[x][y] - xBar;
  		sum += dx*dx;
  	}
  	return sum/(a.length*a[0].length); 		
  }
 protected void printVariance() {
 	System.out.println("variance(r)="+variance(r));
 	System.out.println("variance(g)="+variance(g));
 	System.out.println("variance(b)="+variance(b));
 }
 protected double sigma(short a[][]) {
 	return Math.sqrt(variance(a));
 }
 protected void printSigma() {
 	System.out.println("Sigma(r)="+sigma(r));
 	System.out.println("Sigma(g)="+sigma(g));
 	System.out.println("Sigma(b)="+sigma(b));
 }
 public static void printLaplaceOfGaussianKernel(
		int M, int N, 
		double sigma) {
	float k[][] = getLaplaceOfGaussianKernel(M, N, sigma);
	//Mat.printKernel(k,"LaplaceOfGaussian"+k.length);
}

 public static float [][] getLaplaceOfGaussianKernel(
		int M, int N, 
		double sigma) {
	float k[][] = new float[M][N];
	int xc = M / 2;
	int yc = N / 2;
	for (int x=0; x < k.length; x++)
		for (int y=0; y < k[0].length; y++)
			k[x][y] = (float)laplaceOfGaussian(x,y,xc,yc,sigma);
	Mat.normalize(k);
	return k;
}

	public void threshLog() {
		String prompts[] = {
			"t1","t2",
			"t3","t4",
			"K=#grays, overrides above"};
		String defaults[]={
			"60","120",
			"180","240",
			"0"};
		int fieldSize = 6;
		new DoLog( this, "Threshold Dialog",
			prompts, defaults, fieldSize);
	}
	public void doit(double d[]) {
		if (d[4] != 0) 
			kgreyThresh(d[4]);
		else
			thresh4(d);		
	}
	public void kgreyThresh(double k) {
		Histogram rh = new Histogram(r,"red");
		double cmf[] = rh.getCMF();
		TransformTable tt = new TransformTable(cmf.length);
		short lut[] = tt.getLut();
		int q=1;
		short v=0;
		short dv = (short)(255/k);
		for (int i=0; i < lut.length; i++) {
			if (cmf[i] > q/k) {
				v += dv;
				q++; //(k == q+1)||
				if (q==k) v=255;
			}		
			lut[i]=v;
		}
		tt.setLut(lut);
		tt.clip();
		//tt.print();
		applyLut(lut);
	}
	
	public void thresh4(double d[]) {
	  short lut[] = new short[256];
	  if (d[4] ==0) 
	   for (int i=0; i < lut.length; i++) {
	     if (i < d[0]) lut[i] = 0;
	     else if (i < d[1]) lut[i] = (short)d[0];
	     else if (i < d[2]) lut[i] = (short)d[1];
	     else if (i < d[3]) lut[i] = (short)d[2];
	     else lut[i] = 255;
	     //System.out.println(lut[i]);
	   }
	   applyLut(lut);
	}

static double t0 = 0;
static double t1 = 0;
static double t2 = 0;
static double t3 = 0;
static double t4 = 0;
static double t5 = 0;
static double t6 = 0;
static double t7 = 0;
static double t8 = 0;
static double t9 = 0;
static double t10 = 0;
static double t11 = 0;
static double t12 = 0;
static double t13 = 0;
static double t14 = 0;
static double t15 = 0;
static double t16 = 0;
static double t17 = 0;
static double t18 = 0;
static double t19 = 0;
static double t20 = 0;
static double t21 = 0;
static double t22 = 0;
static double t23 = 0;
static double t24 = 0;
static double t25 = 0;
static double t26 = 0;
static double t27 = 0;
static double t28 = 0;
static double t29 = 0;
static double t30 = 0;
static double t31 = 0;
static double t32 = 0;
static double t33 = 0;
static double t34 = 0;
static double t35 = 0;
static double t36 = 0;
static double t37 = 0;
static double t38 = 0;
static double t39 = 0;
static double t40 = 0;
static double t41 = 0;
static double t42 = 0;
static double t43 = 0;
static double t44 = 0;
static double t45 = 0;
static double t46 = 0;
static double t47 = 0;
static double t48 = 0;
static double t49 = 0;
static double t50 = 0;
static double t51 = 0;
static double t52 = 0;
static double t53 = 0;
static double t54 = 0;
static double t55 = 0;
static double t56 = 0;
static double t57 = 0;
static double t58 = 0;
static double t59 = 0;
static double t60 = 0;
static double t61 = 0;
static double t62 = 0;
static double t63 = 0;
static double t64 = 0;
static double t65 = 0;
static double t66 = 0;
static double t67 = 0;
static double t68 = 0;
static double t69 = 0;
static double t70 = 0;
static double t71 = 0;
static double t72 = 0;
static double t73 = 0;
static double t74 = 0;
static double t75 = 0;
static double t76 = 0;
static double t77 = 0;
static double t78 = 0;
static double t79 = 0;
static double t80 = 0;
static double t81 = 0;
static double t82 = 0;
static double t83 = 0;
static double t84 = 0;
static double t85 = 0;
static double t86 = 0;
static double t87 = 0;
static double t88 = 0;
static double t89 = 0;
static double t90 = 0;
static double t91 = 0;
static double t92 = 0;
static double t93 = 0;
static double t94 = 0;
static double t95 = 0;
static double t96 = 0;
static double t97 = 0;
static double t98 = 0;
static double t99 = 0;
}