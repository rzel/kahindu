// dclap/quickdraw.java // Macintosh QuickDraw info for PICT data// d.gilbert, dec. 1996package dclap;// pack edu.indiana.bio.dclap;public class QuickDrawFont {  int val;  String name;  public QuickDrawFont(int v, String n) { val= v; name= n; }  int fontval(String n) {    if (name.equalsIgnoreCase(n)) return val;    else return -1;    }}