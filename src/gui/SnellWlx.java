package gui;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PrintJob;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnellWlx extends Applet {
   Frame myFrame = new Frame("Bars");
   FrameOne mk;
   Panel p,p1;

   int option,enhance;
   int step,i,j;
   float x,y ;
   float xs, ys;
   int ax,ay,ax1,ay1,cnt;
   int r1, b1, g1,r2,b2,g2,r3,b3,g3;
   int xk[] =new int[3];
   int sw[] =new int[3];

   int cols[][] =new int[1200][3];
   int col1[][] =new int[64][3];
   int col2[][] =new int[64][3];
   int col3[][] =new int[64][3];
   int col4[][] =new int[64][3];

   Scrollbar s1,s2,s3;
   Button bb1,bb2;
   Label sl1,sl2,sl3;
   TextField st1,st2,st3;
   Component c;

   Menu patternsMenu=new Menu("Patterns");

public void init() {

r3=b3=g3=255;
r2=b2=g2=255;
r1=b1=g1=255;

for(i=0;i<300;i++) {
      cols[i][0]=0;
      cols[i][1]=0;
      cols[i][2]=0;
     }
for(i=0;i<3;i++) {
        xk[i]=40+i*40;
        sw[i]=1+i;
    }
for(i=0;i<32;i++) {
        col1[i][0]=i*8;
        col1[i][1]=0;
        col1[i][2]=0;
        col1[32+i][0]=255-i*8;
        col1[32+i][1]=0;
        col1[32+i][2]=0;

        col2[i][0]=0;
        col2[i][1]=i*8;
        col2[i][2]=0;
        col2[32+i][0]=0;
        col2[32+i][1]=255-i*8;
        col2[32+i][2]=0;

        col3[i][0]=0;
        col3[i][1]=0;
        col3[i][2]=i*8;
        col3[32+i][0]=0;
        col3[32+i][1]=0;
        col3[32+i][2]=255-i*8;
    }

c = this;
p=new Panel();
mk=new FrameOne(this);

mk.setLayout(new BorderLayout());

s1=new Scrollbar (Scrollbar.HORIZONTAL,255,10,0,255);
s2=new Scrollbar (Scrollbar.HORIZONTAL,255,10,0,255);
s3=new Scrollbar (Scrollbar.HORIZONTAL,255,10,0,255);

bb1=new Button("Restore Defaults");
bb2=new Button("Cancel");



p.setLayout(new GridLayout(3,4,10,3));
p.add(st1 = new TextField("255", 4));
st1.setEditable(false);
p.add(sl1 = new Label("RED       "));
p.add(s1);
p.add(bb1);

p.add(st2 = new TextField("255", 4));
st2.setEditable(false);
p.add(sl2 = new Label("GREEN     "));
p.add(s2);
p.add(bb2);

p.add(st3 = new TextField("255", 4));
st3.setEditable(false);
p.add(sl3 = new Label("BLUE      "));
p.add(s3);


mk.add("South",p);
mk.add("Center",c);
mk.show();
mk.setSize(256,256);
option=8;
enhance=0;
step=80;
}


public void print() {
		   Toolkit tk = Toolkit.getDefaultToolkit();
		    PrintJob printJob = 
		    	tk.getPrintJob(
		    		new Frame(),
		    		"print me!",
		    		null);
			Graphics g = printJob.getGraphics();
			paint(g);
			printJob.end();
}

public void paint(Graphics g) {

for(cnt=1;cnt<(2+enhance);cnt++) {
    xs=(float)c.getSize().width;
    ys=(float)c.getSize().height;


if (option==10)    {
    g.setColor(new Color(0,0,0));
    g.fillRect(0,0,(int)xs,(int)ys);

while (option==10)
  {
      for(j=0;j<3;j++)
        {
          if (((xk[j])>(int)(ys*7/8))||(xk[j]<(int)(xs/8))) sw[j]=-sw[j];
          xk[j]=xk[j]+sw[j];
        }
      for(j=0;j<(int)ys;j++) cols[j][0]=cols[j][1]=cols[j][2]=0;

      for(j=0;j<64;j++)
        {
          cols[xk[0]-32+j][0]=col1[j][0];
          cols[xk[1]-32+j][1]=col2[j][1];
          cols[xk[2]-32+j][2]=col3[j][2];
        }
      for(j=1;j<(int)ys;j++)
          if ((cols[j][0]+cols[j][1]+cols[j][2])!=0)
          {
           g.setColor(new Color(cols[j][0],cols[j][1],cols[j][2]));
           g.drawLine(0,j,(int)xs,j);
          }
  }


}


    if (enhance==1){
    x=xs*22/50;
    y=ys*4/7;
    ax=(int)(xs*2/50)+(int)(xs*2/50+x)*(cnt-1);
    ay=(int)(ys*4/50);
    ax1=(int)(xs*2/50);
    ay1=(int)(ys*4/50);
    g.setColor(new Color(0,0,0));
    Font fix=new Font("TimesRoman", Font.PLAIN, (int)(y/20));
    g.setFont(fix);
    if (cnt==2)
    g.drawString("after",ax+5+(int)(4.9*x/12)+(int)(x/12),ay-5);
    g.drawString("before",ax1+5+(int)(4.9*x/12)+(int)(x/12),ay1-5);
    }
    else
    {
    x=xs;
    y=ys;
    ax=0;
    ay=0;
    }
    if (cnt==1)
        {
               r1=r3;
               g1=g3;
               b1=b3;
        }
    else
       {
               r1=r2;
               g1=g2;
               b1=b2;

       }


    if (option==1)
    {
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+1,ay+0,(int)(x),(int)(y));
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+1,ay+0,(int)(x*72/512),(int)(y*409/512));
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*73/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*146/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*219/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*292/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*365/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*438/512),ay+0,(int)(x*75/512),(int)(y*409/512));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect((int)(ax+x*93/512),(int)(ay+y*410/512),(int)(x*94/512),(int)(y*105/512));
    }
    else if (option==2)
    {
    g.setColor(new Color((int)(187*r1/255),(int)(187*g1/255),(int)(187*b1/255)));
    g.fillRect(ax+1,ay+0,(int)(x),(int)(y));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect((int)(ax+x*55/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(17*r1/255),(int)(17*g1/255),(int)(17*b1/255)));
    g.fillRect((int)(ax+x*55/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(238*r1/255),(int)(238*g1/255),(int)(238*b1/255)));
    g.fillRect((int)(ax+x*100/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(51*r1/255),(int)(51*g1/255),(int)(51*b1/255)));
    g.fillRect((int)(ax+x*100/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(204*r1/255),(int)(204*g1/255),(int)(204*b1/255)));
    g.fillRect((int)(ax+x*145/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(68*r1/255),(int)(68*g1/255),(int)(68*b1/255)));
    g.fillRect((int)(ax+x*145/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(187*r1/255),(int)(187*g1/255),(int)(187*b1/255)));
    g.fillRect((int)(ax+x*190/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(102*r1/255),(int)(102*g1/255),(int)(102*b1/255)));
    g.fillRect((int)(ax+x*190/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(153*r1/255),(int)(153*g1/255),(int)(153*b1/255)));
    g.fillRect((int)(ax+x*235/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(119*r1/255),(int)(119*g1/255),(int)(119*b1/255)));
    g.fillRect((int)(ax+x*235/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(119*r1/255),(int)(119*g1/255),(int)(119*b1/255)));
    g.fillRect((int)(ax+x*280/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(153*r1/255),(int)(153*g1/255),(int)(153*b1/255)));
    g.fillRect((int)(ax+x*280/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(102*r1/255),(int)(102*g1/255),(int)(102*b1/255)));
    g.fillRect((int)(ax+x*325/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(187*r1/255),(int)(187*g1/255),(int)(187*b1/255)));
    g.fillRect((int)(ax+x*325/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(51*r1/255),(int)(51*g1/255),(int)(51*b1/255)));
    g.fillRect((int)(ax+x*370/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(238*r1/255),(int)(238*g1/255),(int)(238*b1/255)));
    g.fillRect((int)(ax+x*370/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(17*r1/255),(int)(17*g1/255),(int)(17*b1/255)));
    g.fillRect((int)(ax+x*415/512),(int)(ay+y*78/512),(int)(x*45/512),(int)(y*100/512));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect((int)(ax+x*415/512),(int)(ay+y*338/512),(int)(x*45/512),(int)(y*100/512));

    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*170/512),(int)(ay+y*211/512),(int)(x*172/512),(int)(y*91/512));

    }
    else if (option==3)
    {
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+0,ay+0,(int)(x),(int)(y));
    }
    else if (option==4)
    {
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+0,ay+0,(int)(x),(int)(y));
    }
    else if (option==5)
    {
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+0,ay+0,(int)(x),(int)(y));
    int i1,j1;

    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    for (i1=1;i1<(int)(512/step)+1;i1++)
        g.drawLine(ax+(int)(i1*x*step/512),ay+0,ax+(int)(i1*x*step/512),ay+(int)y);
    for (j1=1;j1<(int)(512/step)+1;j1++)
        g.drawLine(ax+0,ay+(int)(j1*y*step/512),ax+(int)x,ay+(int)(j1*y*step/512));

    }
    else if (option==6)
    {
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+1,ay+0,(int)(x),(int)(y));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect((int)(ax+x*140/512),ay+(int)(y*142/512),(int)(x*134/512),(int)(y*260/512));
    g.fillRect((int)(ax+x*275/512),ay+(int)(y*142/512),(int)(x*134/512),(int)(y*260/512));
    g.setColor(new Color((int)(69*r1/255),(int)(69*g1/255),(int)(69*b1/255)));
    g.drawLine((int)(ax+x*87/512),ay+(int)(0),ax+(int)(x*87/512),ay+(int)(y));
    g.setColor(new Color((int)(194*r1/255),(int)(194*g1/255),(int)(194*b1/255)));
    g.drawLine((int)(ax+x*88/512),ay+(int)(0),ax+(int)(x*88/512),ay+(int)(y));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.drawLine((int)(ax+x*89/512),ay+(int)(0),ax+(int)(x*89/512),ay+(int)(y));
    g.setColor(new Color((int)(194*r1/255),(int)(194*g1/255),(int)(194*b1/255)));
    g.drawLine((int)(ax+x*90/512),ay+(int)(0),ax+(int)(x*90/512),ay+(int)(y));
    g.setColor(new Color((int)(69*r1/255),(int)(69*g1/255),(int)(69*b1/255)));
    g.drawLine((int)(ax+x*91/512),ay+(int)(0),ax+(int)(x*91/512),ay+(int)(y));


    g.setColor(new Color((int)(41*r1/255),(int)(41*g1/255),(int)(41*b1/255)));
    g.drawLine((int)(ax+x*9/512),ay+(int)(0),ax+(int)(x*9/512),ay+(int)(y));
    g.setColor(new Color((int)(37*r1/255),(int)(37*g1/255),(int)(37*b1/255)));
    g.drawLine((int)(ax+x*10/512),ay+(int)(0),ax+(int)(x*10/512),ay+(int)(y));
    g.setColor(new Color((int)(8*r1/255),(int)(8*g1/255),(int)(8*b1/255)));
    g.drawLine((int)(ax+x*11/512),ay+(int)(0),ax+(int)(x*11/512),ay+(int)(y));
    g.setColor(new Color((int)(62*r1/255),(int)(62*g1/255),(int)(62*b1/255)));
    g.drawLine((int)(ax+x*12/512),ay+(int)(0),ax+(int)(x*12/512),ay+(int)(y));
    g.setColor(new Color((int)(111*r1/255),(int)(111*g1/255),(int)(111*b1/255)));
    g.drawLine((int)(ax+x*13/512),ay+(int)(0),ax+(int)(x*13/512),ay+(int)(y));
    g.setColor(new Color((int)(20*r1/255),(int)(20*g1/255),(int)(20*b1/255)));
    g.drawLine((int)(ax+x*14/512),ay+(int)(0),ax+(int)(x*14/512),ay+(int)(y));
    g.setColor(new Color((int)(40*r1/255),(int)(40*g1/255),(int)(40*b1/255)));
    g.drawLine((int)(ax+x*15/512),ay+(int)(0),ax+(int)(x*15/512),ay+(int)(y));
    g.setColor(new Color((int)(177*r1/255),(int)(177*g1/255),(int)(177*b1/255)));
    g.drawLine((int)(ax+x*16/512),ay+(int)(0),ax+(int)(x*16/512),ay+(int)(y));
    g.setColor(new Color((int)(98*r1/255),(int)(98*g1/255),(int)(98*b1/255)));
    g.drawLine((int)(ax+x*17/512),ay+(int)(0),ax+(int)(x*17/512),ay+(int)(y));
    g.setColor(new Color((int)(3*r1/255),(int)(3*g1/255),(int)(3*b1/255)));
    g.drawLine((int)(ax+x*18/512),ay+(int)(0),ax+(int)(x*18/512),ay+(int)(y));
    g.setColor(new Color((int)(172*r1/255),(int)(172*g1/255),(int)(172*b1/255)));
    g.drawLine((int)(ax+x*19/512),ay+(int)(0),ax+(int)(x*19/512),ay+(int)(y));
    g.setColor(new Color((int)(205*r1/255),(int)(205*g1/255),(int)(205*b1/255)));
    g.drawLine((int)(ax+x*20/512),ay+(int)(0),ax+(int)(x*20/512),ay+(int)(y));
    g.setColor(new Color((int)(16*r1/255),(int)(16*g1/255),(int)(16*b1/255)));
    g.drawLine((int)(ax+x*21/512),ay+(int)(0),ax+(int)(x*21/512),ay+(int)(y));
    g.setColor(new Color((int)(94*r1/255),(int)(94*g1/255),(int)(94*b1/255)));
    g.drawLine((int)(ax+x*22/512),ay+(int)(0),ax+(int)(x*22/512),ay+(int)(y));
    g.setColor(new Color((int)(16*r1/255),(int)(16*g1/255),(int)(16*b1/255)));
    g.drawLine((int)(ax+x*23/512),ay+(int)(0),ax+(int)(x*23/512),ay+(int)(y));
    g.setColor(new Color((int)(205*r1/255),(int)(205*g1/255),(int)(205*b1/255)));
    g.drawLine((int)(ax+x*24/512),ay+(int)(0),ax+(int)(x*24/512),ay+(int)(y));
    g.setColor(new Color((int)(172*r1/255),(int)(172*g1/255),(int)(172*b1/255)));
    g.drawLine((int)(ax+x*25/512),ay+(int)(0),ax+(int)(x*25/512),ay+(int)(y));
    g.setColor(new Color((int)(3*r1/255),(int)(3*g1/255),(int)(3*b1/255)));
    g.drawLine((int)(ax+x*26/512),ay+(int)(0),ax+(int)(x*26/512),ay+(int)(y));
    g.setColor(new Color((int)(98*r1/255),(int)(98*g1/255),(int)(98*b1/255)));
    g.drawLine((int)(ax+x*27/512),ay+(int)(0),ax+(int)(x*27/512),ay+(int)(y));
    g.setColor(new Color((int)(177*r1/255),(int)(177*g1/255),(int)(177*b1/255)));
    g.drawLine((int)(ax+x*28/512),ay+(int)(0),ax+(int)(x*28/512),ay+(int)(y));
    g.setColor(new Color((int)(40*r1/255),(int)(40*g1/255),(int)(40*b1/255)));
    g.drawLine((int)(ax+x*29/512),ay+(int)(0),ax+(int)(x*29/512),ay+(int)(y));
    g.setColor(new Color((int)(20*r1/255),(int)(20*g1/255),(int)(20*b1/255)));
    g.drawLine((int)(ax+x*30/512),ay+(int)(0),ax+(int)(x*30/512),ay+(int)(y));
    g.setColor(new Color((int)(11*r1/255),(int)(11*g1/255),(int)(11*b1/255)));
    g.drawLine((int)(ax+x*31/512),ay+(int)(0),ax+(int)(x*31/512),ay+(int)(y));
    g.setColor(new Color((int)(82*r1/255),(int)(82*g1/255),(int)(82*b1/255)));
    g.drawLine((int)(ax+x*32/512),ay+(int)(0),ax+(int)(x*32/512),ay+(int)(y));
    g.setColor(new Color((int)(8*r1/255),(int)(8*g1/255),(int)(8*b1/255)));
    g.drawLine((int)(ax+x*33/512),ay+(int)(0),ax+(int)(x*33/512),ay+(int)(y));
    g.setColor(new Color((int)(37*r1/255),(int)(37*g1/255),(int)(37*b1/255)));
    g.drawLine((int)(ax+x*34/512),ay+(int)(0),ax+(int)(x*34/512),ay+(int)(y));
    g.setColor(new Color((int)(41*r1/255),(int)(41*g1/255),(int)(41*b1/255)));
    g.drawLine((int)(ax+x*35/512),ay+(int)(0),ax+(int)(x*35/512),ay+(int)(y));


    }
    else if (option==7)
    {
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+1,ay+0,(int)(x),(int)(y));
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+1,ay+0,ax+(int)(x*72/512),(int)(y*369/512));
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*73/512),ay+0,(int)(x*75/512),(int)(y*369/512));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*146/512),ay+0,(int)(x*75/512),(int)(y*369/512));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*219/512),ay+0,(int)(x*75/512),(int)(y*369/512));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*292/512),ay+0,(int)(x*75/512),(int)(y*369/512));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect((int)(ax+x*365/512),ay+0,(int)(x*75/512),(int)(y*369/512));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect((int)(ax+x*438/512),ay+0,(int)(x*75/512),(int)(y*369/512));

    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+1,ay+(int)(y*370/512),(int)(x*75/512),(int)(y*40/512));

    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)(x*146/512),ay+(int)(y*370/512),(int)(x*75/512),(int)(y*40/512));

    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)(x*292/512),ay+(int)(y*370/512),(int)(x*75/512),(int)(y*40/512));


    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)(x*438/512),ay+(int)(y*370/512),(int)(x*75/512),(int)(y*40/512));


    g.setColor(new Color((int)(8*r1/255),(int)(62*g1/255),(int)(89*b1/255)));
    g.fillRect(ax+1,ay+(int)(y*410/512),(int)(x*95/512),(int)(y*102/512));

    g.setColor(new Color((int)(58*r1/255),(int)(0*g1/255),(int)(126*b1/255)));
    g.fillRect(ax+(int)(x*146/512),ay+(int)(y*410/512),(int)(x*130/512),(int)(y*102/512));

    g.setColor(new Color((int)(19*r1/255),(int)(19*g1/255),(int)(19*b1/255)));
    g.fillRect(ax+(int)(x*339/512),ay+(int)(y*410/512),(int)(x*123/512),(int)(y*102/512));

    g.setColor(new Color((int)(38*r1/255),(int)(38*g1/255),(int)(38*b1/255)));
    g.fillRect(ax+(int)(x*409/512),ay+(int)(y*410/512),(int)(x*29/512),(int)(y*102/512));


    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+(int)(x*93/512),ay+(int)(y*410/512),(int)(x*93/512),(int)(y*102/512));

    }

    else if (option==8)
    {
    y=y-25;
    x=x-10;
    g.setColor(new Color((int)(127*r1/255),(int)(127*g1/255),(int)(127*b1/255)));
    g.fillRect(ax+5,ay+0,(int)(x),(int)(y));

    int i1,j1,k1,l1;
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    k1=(int)(3*x/24);
    for (i1=1;i1<12;i1++)
        g.drawLine(ax+5+(int)(i1*x/12),ay+0,ax+5+(int)(i1*x/12),ay+0+(int)y);
    for (j1=1;j1<9;j1++)
        g.drawLine(ax+5,ay+0+(int)(j1*y/9),ax+5+(int)x,ay+0+(int)(j1*y/9));
        g.drawArc(ax+5+k1,ay+0,(int)(x-2*k1),(int)(y),0,360);
    g.setColor(new Color((int)(127*r1/255),(int)(127*g1/255),(int)(127*b1/255)));
    g.fillRect(ax+5+(int)(3*x/12),ay+0+(int)(2*y/9),(int)(6*x/12),(int)(5*y/9));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.drawRect(ax+5+(int)(3*x/12),ay+0+(int)(2*y/9),(int)(6*x/12),(int)(5*y/9));
    for (i1=0;i1<60;i1++)
        {
        l1=(int)(128+(127*(1-Math.sin((double)(i1)/120*3.14159237)))*Math.cos((double)(i1)/3*3.14159237));
        g.setColor(new Color((int)(l1*r1/255),(int)(l1*g1/255),(int)(l1*b1/255)));
        g.drawArc(ax+5+k1+(int)((x-2*k1)*(120-i1)/240),ay+0+(int)(y*(120-i1)/240),(int)((i1*(x-2*k1)/120)),(int)(i1*y/120),0,360);
        }

//upper left
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5,ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(x/24),ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(2*x/24),ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(3*x/24),ay+0,(int)(x/24),(int)(y/20));

    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5,ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(2*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(3*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));

    g.setColor(new Color((int)(64*r1/255),(int)(64*g1/255),(int)(64*b1/255)));
    g.fillRect(ax+5,ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(92*r1/255),(int)(92*g1/255),(int)(92*b1/255)));
    g.fillRect(ax+5+(int)(x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(162*r1/255),(int)(162*g1/255),(int)(162*b1/255)));
    g.fillRect(ax+5+(int)(2*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(210*r1/255),(int)(210*g1/255),(int)(210*b1/255)));
    g.fillRect(ax+5+(int)(3*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));

//upper right
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(4*x/24),ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(3*x/24),ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(2*x/24),ay+0,(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(1*x/24),ay+0,(int)(x/24),(int)(y/20));

    g.setColor(new Color((int)(0*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(4*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(3*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(2*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));
    g.setColor(new Color((int)(192*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(1*x/24),ay+0+(int)(y/20),(int)(x/24),(int)(y/20));

    g.setColor(new Color((int)(210*r1/255),(int)(210*g1/255),(int)(210*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(4*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(162*r1/255),(int)(162*g1/255),(int)(162*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(3*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(92*r1/255),(int)(92*g1/255),(int)(92*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(2*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));
    g.setColor(new Color((int)(64*r1/255),(int)(64*g1/255),(int)(64*b1/255)));
    g.fillRect(ax+(int)x+5-(int)(1*x/24),ay+0+(int)(y/10),(int)(x/24),(int)(y/80));


//lower left
    g.setColor(new Color((int)(92*r1/255),(int)(0*g1/255),(int)(92*b1/255)));
    g.fillRect(ax+5,ay+0+(int)y-(int)(y/10),(int)(x/12),(int)(y/10));
    g.setColor(new Color((int)(163*r1/255),(int)(0*g1/255),(int)(163*b1/255)));
    g.fillRect(ax+5+(int)(x/12),ay+0+(int)y-(int)(y/10),(int)(x/14),(int)(y/10));
    g.setColor(new Color((int)(255*r1/255),(int)(0*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+5+(int)(2*x/13),ay+0+(int)y-(int)(y/10),(int)(x/14),(int)(y/10));

    g.setColor(new Color((int)(255*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)x-(int)(3*x/13),ay+0+(int)y-(int)(y/10),(int)(3*x/13),(int)(y/10));


//texts
    g.setColor(new Color((int)(127*r1/255),(int)(127*g1/255),(int)(127*b1/255)));
    g.fillRect(ax+5+(int)(4*x/12),ay+0+(int)y-(int)(y/9),(int)(4*x/12),0+(int)(y/9));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.drawLine(ax+5+(int)(4*x/12),ay+0+(int)(8*y/9),ax+5+(int)(4*x/12),ay+0+(int)(y));
    g.drawLine(ax+5+(int)(8*x/12),ay+0+(int)(8*y/9),ax+5+(int)(8*x/12),ay+0+(int)(y));
    g.drawLine(ax+5+(int)(5*x/12),ay+0+(int)(8*y/9),ax+5+(int)(5*x/12),ay+0+(int)(y));
    g.drawLine(ax+5+(int)(7*x/12),ay+0+(int)(8*y/9),ax+5+(int)(7*x/12),ay+0+(int)(y));


    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    Font fix=new Font("TimesRoman", Font.BOLD, (int)(y/30));
    g.setFont(fix);
    g.drawString("1.0",ax+5+(int)(4.3*x/12),ay+0+(int)y-(int)(.05*y/10));
    g.drawString("0.5",ax+5+(int)(4.9*x/12)+(int)(x/12),ay+0+(int)y-(int)(.05*y/10));
    g.drawString("1.5",ax+5+(int)(4.3*x/12)+(int)(3*x/12),ay+0+(int)y-(int)(.05*y/10));

    g.drawString("3",ax+5+(int)(10.1*x/12),ay+0+(int)y-(int)(5.1*y/9));
    g.drawString("4",ax+5+(int)(10.1*x/12),ay+0+(int)y-(int)(4.1*y/9));
    g.drawString("5",ax+5+(int)(10.1*x/12),ay+0+(int)y-(int)(3.1*y/9));

    g.drawString("4.43",ax+5+(int)(10.1*x/12),ay+0+(int)y-(int)(6.7*y/9));
    g.drawString("3.58",ax+5+(int)(1.1*x/12),ay+0+(int)y-(int)(6.7*y/9));
    g.drawString("4.28",ax+5+(int)(1.1*x/12),ay+0+(int)y-(int)(2.1*y/9));
    g.drawString("Mhz",ax+5+(int)(9.1*x/12),ay+0+(int)y-(int)(6.7*y/9));

    fix=new Font("TimesRoman", Font.PLAIN, (int)(y/23));
    g.setFont(fix);
    g.drawString("NTSC",ax+5+(int)(5.6*x/12),ay+0+(int)y-(int)(7.1*y/9));

    fix=new Font("TimesRoman", Font.PLAIN, (int)(y/26));
    g.setFont(fix);
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+5+(int)(5.5*x/12),ay+0+(int)(1.1*y/9),(int)(x/12),(int)(y/20));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.drawString("SW2",ax+5+(int)(5.6*x/12),ay+0+(int)y-(int)(7.6*y/9));

    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    fix=new Font("TimesRoman", Font.PLAIN, (int)(y/33));
    g.setFont(fix);

    g.drawString("300",ax+5+(int)(.1*x/12),ay+0+(int)y-(int)(2.6*y/9));
    g.drawString("100",ax+5+(int)(.1*x/12),ay+0+(int)y-(int)(6.2*y/9));
    g.drawString("TVL",ax+5+(int)(.1*x/12),ay+0+(int)y-(int)(6.7*y/9));
    g.drawString("300",ax+5+(int)(4.2*x/12),ay+0+(int)y-(int)(8.2*y/9));
    g.drawString("400",ax+5+(int)(7.2*x/12),ay+0+(int)y-(int)(8.2*y/9));

    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(2.1*x/12),ay+0+(int)(y/20),(int)(x/16),(int)(y/20));
    g.fillRect(ax+5+(int)(9.1*x/12),ay+0+(int)(y/20),(int)(x/16),(int)(y/20));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.drawLine(ax+5+(int)(2.2*x/12),ay+0+(int)(1.1*y/20),ax+5+(int)(2.7*x/12),ay+0+(int)(1.7*y/20));
    g.drawLine(ax+5+(int)(9.45*x/12),ay+0+(int)(1.1*y/20),ax+5+(int)(9.45*x/12),ay+0+(int)(1.7*y/20));
    g.drawLine(ax+5+(int)(9.2*x/12),ay+0+(int)(1.45*y/20),ax+5+(int)(9.75*x/12),ay+0+(int)(1.45*y/20));

    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(11*x/12),ay+0+(int)y-(int)(6*y/9),(int)(x/12),(int)(y/9));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.drawLine(ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(5.9*y/9),ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(5.1*y/9));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+5+(int)(11*x/12),ay+0+(int)y-(int)(5*y/9),(int)(x/12),(int)(y/9));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.drawLine(ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(4.9*y/9),ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(4.1*y/9));
    g.setColor(new Color((int)(64*r1/255),(int)(64*g1/255),(int)(64*b1/255)));
    g.fillRect(ax+5+(int)(11*x/12),ay+0+(int)y-(int)(4*y/9),(int)(x/12),(int)(y/9));
    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.drawLine(ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(3.9*y/9),ax+5+(int)(11.5*x/12),ay+0+(int)y-(int)(3.1*y/9));


    g.setColor(new Color((int)(255*r1/255),(int)(255*g1/255),(int)(255*b1/255)));
    g.fillRect(ax+5+(int)(2.15*x/12),ay+0+(int)(3.2*y/9),(int)(x/16),(int)(y/12));
    g.setColor(new Color((int)(92*r1/255),(int)(92*g1/255),(int)(92*b1/255)));
    g.fillRect(ax+5+(int)(2.15*x/12),ay+0+(int)(4.2*y/9),(int)(x/16),(int)(y/12));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(0*b1/255)));
    g.fillRect(ax+5+(int)(2.15*x/12),ay+0+(int)(5.2*y/9),(int)(x/16),(int)(y/12));
    g.setColor(new Color((int)(128*r1/255),(int)(128*g1/255),(int)(128*b1/255)));
    g.drawLine(ax+5+(int)(2.25*x/12),ay+0+(int)(3.3*y/9),ax+5+(int)(2.25*x/12),ay+0+(int)(3.8*y/9));
    g.drawLine(ax+5+(int)(2.25*x/12),ay+0+(int)(5.3*y/9),ax+5+(int)(2.25*x/12),ay+0+(int)(5.8*y/9));
    g.drawLine(ax+5+(int)(2.73*x/12),ay+0+(int)(3.3*y/9),ax+5+(int)(2.73*x/12),ay+0+(int)(3.8*y/9));
    g.drawLine(ax+5+(int)(2.73*x/12),ay+0+(int)(5.3*y/9),ax+5+(int)(2.73*x/12),ay+0+(int)(5.8*y/9));

//lower mid colors
//a
    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(4*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(4.12*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(4.23*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(4.34*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(4.45*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(4.56*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(4.67*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(4.78*x/12),ay+0+(int)y-(int)(y/9),(int)(.11*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(4.89*x/12),ay+0+(int)y-(int)(y/9),(int)(.12*x/12),(int)(y/18));

//b
    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(30*b1/255)));
    g.fillRect(ax+5+(int)(5.01*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(5.23*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));

    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(30*b1/255)));
    g.fillRect(ax+5+(int)(5.45*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(5.67*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));

    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(30*b1/255)));
    g.fillRect(ax+5+(int)(5.89*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(6.12*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));

    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(30*b1/255)));
    g.fillRect(ax+5+(int)(6.34*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));
    g.setColor(new Color((int)(0*r1/255),(int)(0*g1/255),(int)(192*b1/255)));
    g.fillRect(ax+5+(int)(6.56*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));

    g.setColor(new Color((int)(192*r1/255),(int)(192*g1/255),(int)(30*b1/255)));
    g.fillRect(ax+5+(int)(6.78*x/12),ay+0+(int)y-(int)(y/9),(int)(.22*x/12),(int)(y/18));

//c
    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7*x/12),ay+0+(int)y-(int)(y/9),(int)(.07*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.07*x/12),ay+0+(int)y-(int)(y/9),(int)(.07*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.14*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.22*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.30*x/12),ay+0+(int)y-(int)(y/9),(int)(.07*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.37*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.45*x/12),ay+0+(int)y-(int)(y/9),(int)(.07*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.52*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.60*x/12),ay+0+(int)y-(int)(y/9),(int)(.07*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.67*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.75*x/12),ay+0+(int)y-(int)(y/9),(int)(.09*x/12),(int)(y/18));
    g.setColor(new Color((int)(60*r1/255),(int)(168*g1/255),(int)(168*b1/255)));
    g.fillRect(ax+5+(int)(7.84*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    g.setColor(new Color((int)(128*r1/255),(int)(60*g1/255),(int)(60*b1/255)));
    g.fillRect(ax+5+(int)(7.92*x/12),ay+0+(int)y-(int)(y/9),(int)(.08*x/12),(int)(y/18));

    int i;
    for (i=1;i<12;i++)
    {
        g.setColor(new Color((int)((int)((i+1)%2)*255*r1/255),(int)(((i+1)%2)*255*g1/255),(int)(((i+1)%2)*255*b1/255)));
        g.drawLine(ax+5+(int)((938+2*i)*x/1200),ay+0+(int)(6.5*y/9),ax+5+(int)((915+6*i)*x/1200),ay+0+(int)(2.5*y/9));
        g.drawLine(ax+5+(int)((938+2*i)*x/1200),ay+0+(int)(6.5*y/9),ax+5+(int)((915+6*i+1)*x/1200),ay+0+(int)(2.5*y/9));
        g.drawLine(ax+5+(int)((938+2*i)*x/1200),ay+0+(int)(6.5*y/9),ax+5+(int)((915+6*i-1)*x/1200),ay+0+(int)(2.5*y/9));
    }

    for (i=1;i<11;i++)
    {
        g.setColor(new Color((int)(((int)((i+1)%2)*100+100)*r1/255),(int)((int)(((i+1)%2)*100+100)*g1/255),(int)((int)(((i+1)%2)*100+100)*b1/255)));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((29+i)*y/90),ax+5+(int)(x/12),ay+0+(int)((30+i)*y/90));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((29.3+i)*y/90),ax+5+(int)(x/12),ay+0+(int)((30.3+i)*y/90));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((29.6+i)*y/90),ax+5+(int)(x/12),ay+0+(int)((30.6+i)*y/90));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((29.8+i)*y/90),ax+5+(int)(x/12),ay+0+(int)((30.8+i)*y/90));
    }

    for (i=1;i<20;i++)
    {
        g.setColor(new Color((int)((int)(((i+1)%2)*100+100)*r1/255),(int)((int)(((i+1)%2)*100+100)*g1/255),(int)(((int)((i+1)%2)*100+100)*b1/255)));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((78+i)*y/180),ax+5+(int)(x/12),ay+0+(int)((80+i)*y/180));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((78.4+i)*y/180),ax+5+(int)(x/12),ay+0+(int)((80.4+i)*y/180));
    }

    for (i=1;i<40;i++)
    {
        g.setColor(new Color((int)(((int)((i+1)%2)*100+100)*r1/255),(int)(((int)((i+1)%2)*100+100)*g1/255),(int)(((int)((i+1)%2)*100+100)*b1/255)));
        g.drawLine(ax+5+(int)(.01*x/12),ay+0+(int)((196+i)*y/360),ax+5+(int)(x/12),ay+0+(int)((200+i)*y/360));
    }

    for (i=1;i<40;i++)
    {
        g.setColor(new Color((int)(((int)((i+1)%2)*100+100)*r1/255),(int)(((int)((i+1)%2)*100+100)*g1/255),(int)(((int)((i+1)%2)*100+100)*b1/255)));
        g.drawLine(ax+5+(int)((40+i)*x/480),ay+0+(int)(1.01*y/9),ax+5+(int)((40+i)*x/480),ay+0+(int)(1.97*y/9));
        g.drawLine(ax+5+(int)((40+i)*x/480),ay+0+(int)(7.01*y/9),ax+5+(int)((40+i)*x/480),ay+0+(int)(7.97*y/9));
        g.drawLine(ax+5+(int)((400+i)*x/480),ay+0+(int)(1.01*y/9),ax+5+(int)((400+i)*x/480),ay+0+(int)(1.97*y/9));
    }

    for (i=1;i<37;i++)
    {
        g.setColor(new Color((int)(((int)((i+1)%2)*100+100)*r1/255),(int)(((int)((i+1)%2)*100+100)*g1/255),(int)(((int)((i+1)%2)*100+100)*b1/255)));
        g.drawLine(ax+5+(int)(10.5*x/12),ay+0+(int)(7.5*y/9),ax+5+(int)(10.5*x/12+(x/30)*Math.cos(i*Math.PI/18)),ay+0+(int)(7.5*y/9+(y/22)*Math.sin(i*Math.PI/18)));
    }

        g.setColor(new Color((int)(128*r1/255),(int)(128*g1/255),(int)(128*b1/255)));
        g.fillOval(ax+5+(int)(10.5*x/12)-(int)(x/96),ay+0+(int)(7.5*y/9)-(int)(x/72),(int)(x/48),(int)(x/36));
    }

    else;
}
 }

}

class FrameOne extends ClosableFrame 
	implements ActionListener {
    SnellWlx can;
    
    MenuBar mbar=new MenuBar();
    Menu CanMenu = new Menu("Test Patterns");
    MenuItem eiaBars_mi = addMenuItem(CanMenu,"eiabars");
	MenuItem chip_chart_mi = addMenuItem(CanMenu,"chip_chart");
    MenuItem black_mi = addMenuItem(CanMenu,"black");
    MenuItem white_mi = addMenuItem(CanMenu,"white");
    MenuItem grid_mi = addMenuItem(CanMenu,"grid");
    MenuItem pulseAndBar_mi = addMenuItem(CanMenu,"pulse and bar");
    MenuItem smpteBars_mi = addMenuItem(CanMenu,"smpte bars");
    MenuItem increaseGrid_mi = addMenuItem(CanMenu,"increase grid");
    MenuItem decreaseGrid_mi = addMenuItem(CanMenu,"decrease grid");
    MenuItem snellWilcox_mi = addMenuItem(CanMenu,"snell & wilcox");
    MenuItem print_mi = addMenuItem(CanMenu,"print...");
    
    public static void main(String args[]) {
    	SnellWlx sw = new SnellWlx();
    	sw.init();
    	FrameOne fo = new FrameOne(sw);
    }

    public FrameOne(SnellWlx can) {
        super("Test Patterns");
        this.can=can;
        can.p.setVisible(false);
        mbar.add(CanMenu);
    	setMenuBar(mbar);
    }
    
	public MenuItem addMenuItem(Menu aMenu, String itemName) {	
		MenuItem mi = new MenuItem(itemName);
		aMenu.add(mi);
		mi.addActionListener(this);
		return(mi);
	}
 
public void actionPerformed(ActionEvent e) {
	if (!(e.getSource() instanceof MenuItem))
		return;
	MenuItem mi = (MenuItem)e.getSource();
	if (mi == print_mi) can.print();
	if (mi == eiaBars_mi) can.option=1;
	if (mi == chip_chart_mi) can.option=2;
 	if (mi == black_mi) can.option=3;
	if (mi == white_mi)can.option=4;
	if (mi == grid_mi) can.option=5;
	if (mi == pulseAndBar_mi) can.option=6;
	if (mi == smpteBars_mi) can.option=7;
	if (mi == snellWilcox_mi) can.option=8;
	if (mi == increaseGrid_mi) 
		if (can.step<200) can.step+=10;

	if (mi == decreaseGrid_mi) 
		if (can.step>10) can.step-=10;


    can.repaint();
}

}
