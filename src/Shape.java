//package bluewin;
import java.awt.*;
import java.awt.geom.*;
public class Shape
{
 public int shapeType;
 public double value[];
 public Point2D vertex[];

 //constructor
 Shape(int type)
 {
  shapeType = type;
 }
 Shape()
 {
 }
 public void storeLine(Point2D start,Point2D end)
 {
  vertex = new Point2D[2];
  vertex[0]=start;
  vertex[1]=end;
 }
 public void setCircle(double radius,Point2D center)
 {
 value = new double[1];
 vertex = new Point2D[1];
 value[0] = radius;
 vertex[0]= center;

 }
 public void setPolygon(Point2D vertices[])
 {
  vertex = new Point2D[vertices.length];
  for(int i=0;i<vertices.length;i++)
  {
   vertex[i]=vertices[i];
  }
 }
 public void storeWhiteRegion(double minx,double miny,double maxx,double maxy) throws java.lang.NullPointerException
 {
  Point temppoint = new Point((int)minx,(int)miny);
  vertex = new Point2D[2];
  vertex[0]=(Point2D)temppoint;  
  temppoint = new Point((int)maxx,(int)maxy);
  vertex[1]=(Point2D)temppoint;  
 }

 
  public void storeLetter(char c,int x,int y) 
 {
  
value   = new double[1];
value[0]= (double)c;
vertex = new Point2D[1];
Point temppoint = new Point(x,y); 
vertex[0]=(Point2D)temppoint;   
 }

 


}