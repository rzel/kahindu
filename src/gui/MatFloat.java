package gui;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MatFloat extends 
	Mat {
	float f[][];
	
MatFloat (float flt[][]) {
	  f = flt;
}


public String getSaveFileName(String prompt) {
	FileDialog fd = new 
		FileDialog(
			new Frame(), 
			prompt, 
			FileDialog.SAVE);
	fd.setVisible(true);
	fd.setVisible(false);
	String fn=fd.getDirectory()+fd.getFile();
	if (fd.getFile() == null) return null; //usr canceled
	return fn;
}
	
public static void main(String args[]) {
	System.out.println("Test");
	float flt[][] = 
		{
			{1.0f,3.0f,4.0f},
			{1.0f,3.0f,4.0f},
			{1.0f,3.0f,4.0f}
		};
	MatFloat mf = new MatFloat(flt);
	print(flt);
	String fn = mf.getSaveFileName("flt.gz file");
	mf.saveAsgz(fn);
	mf.readAsgz(fn);
	print(mf.f);
}
public void saveAsgz() {
	saveAsgz(
		getSaveFileName("flt.gz file")); 
}
public void saveAsgz(String fn) {
    try {
    	FileOutputStream fos = new FileOutputStream(fn);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
     	ObjectOutputStream oos = new ObjectOutputStream(gos);
         oos.writeInt(f.length);
         oos.writeInt(f[0].length);
         for (int x=0; x < f.length; x++)
          for (int y=0; y < f[0].length; y++) 
 			oos.writeFloat(f[x][y]);
         oos.close();
         gos.finish();

        } catch(Exception e) {
         System.out.println("Save saveAsFloatgz:"+e);
        }
}

public  void readAsgz(String fn) {
    try {
    	FileInputStream fis = new FileInputStream(fn);
        GZIPInputStream gis = new GZIPInputStream(fis);
     	ObjectInputStream ois = new ObjectInputStream(gis);
         int w = ois.readInt();
         int h = ois.readInt();
         f = new float[w][h];
         for (int x=0; x < w; x++)
           for (int y=0; y < h; y++)
             f[x][y] = ois.readFloat();
          ois.close();
        } catch(Exception e) {
         System.out.println("readAsgz:"+e);
     	}
	}
}