//package bluewin;

import java.awt.geom.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import java.lang.*;
import java.awt.*;

class pt_ang 
{
	public Point2D pt;
	public double a;
        public int index; 
}

class AngleComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		pt_ang p1 = (pt_ang)o1;
		pt_ang p2 = (pt_ang)o2;

		if(p1.a<p2.a) return -1;
		else return 1;
	}
}
class SequenceComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		pt_ang p1 = (pt_ang)o1;
		pt_ang p2 = (pt_ang)o2;

		if(p1.index<p2.index) return -1;
		else return 1;
	}
}


public class Recognize
{
	double length0; //length
	double length1; //peremeter
	double length_by_peremeter; //ratio
	double area; //area
	double thinness; //thinness ratio
	double area_triangle;
	double area_ratio;//traiangle_by_convex;
	Morph morph_obj = new Morph();
        Point2D p[];
        
   
	private double isLeft(Point2D p0, Point2D p1, Point2D p2)
	{
		return ((p1.getX()-p0.getX())*(p2.getY()-p0.getY()))-((p2.getX()-p0.getX())*(p1.getY()-p0.getY()));
	}

	public  Shape recognizeShape(Point2D point_array[],Point2D point_array_out[])
	{
		
                p = new Point[point_array.length];
                p=point_array;
                length0=0;

		//length of drawn figure
		for(int i=1;i<point_array.length;i++)
		{
			length0 += point_array[i].distance(point_array[i-1]);
		}
		length1=0;

		//length of drawn figure
		for(int i=1;i<point_array_out.length;i++)
		{
			length1 += point_array_out[i].distance(point_array_out[i-1]);
		}

		//get the lenth by peremeter ratio
		length_by_peremeter = (length0/length1);
		if(length_by_peremeter>1.6)
		{
			System.out.println("Delete Command");
		    Shape objDelete = morph_obj.delete(point_array_out);
		    return objDelete;

		}
		else
		{
			//System.out.println("Cannot RecogniZed");
			return (isCircle(point_array_out));
		}
	}

	private Shape isCircle(Point2D point_array_out[])
	{
		double sum = 0.0;
		for (int i = 0; i < point_array_out.length-1; i++)
		{
			sum = sum + (point_array_out[i].getX() * point_array_out[i+1].getY()) - (point_array_out[i].getY() * point_array_out[i+1].getX());
		}
		area=0.5 * sum;
		thinness = length1 * length1 / area;
		if(thinness<13.5)
		{
			System.out.println("May Be Circle");
			return(morph_obj.drawCircle(point_array_out,area));
		}
		else if(thinness>102)
		{
			System.out.println("may Be Line");
			return(morph_obj.drawLine(point_array_out));
		}
		else
		{
			System.out.println("Others");
			return(isTriangle(point_array_out));
		}

	}

	private Shape isTriangle(Point2D point_array[]) throws ArrayIndexOutOfBoundsException
	{
		pt_ang obj_pt_ang[] = new pt_ang[point_array.length-1];
		double angle,temp1,temp2,angle_deg;
                int i;

		for(i=1;i<point_array.length-1;i++)
		{      
			temp1=(point_array[i].getY()-point_array[i-1].getY())/(point_array[i].getX()-point_array[i-1].getX());
			temp2=(point_array[i+1].getY()-point_array[i].getY())/(point_array[i+1].getX()-point_array[i].getX());
			temp1 = java.lang.Math.atan(temp1);
			temp2 = java.lang.Math.atan(temp2);
			angle=temp1-temp2;
			angle_deg=angle*180/3.141;
			pt_ang an =new pt_ang();
			an.pt=point_array[i];
			if (angle_deg<0) angle_deg=180 + angle_deg;
			an.a=angle_deg;
			obj_pt_ang[i-1]=an;
                        obj_pt_ang[i-1].index=i;
                        
                        
                      //   System.out.println("\n"+angle_deg+" "+i+" "+point_array[i].getX()+" "+point_array[i].getY());
		}
                        //int last=point_array.length;//Deter fine the last angle...
                        temp1=(point_array[i].getY()-point_array[i-1].getY())/(point_array[i].getX()-point_array[i-1].getX());
			temp2=(point_array[1].getY()-point_array[1].getY())/(point_array[1].getX()-point_array[i].getX());
			temp1 = java.lang.Math.atan(temp1);
			temp2 = java.lang.Math.atan(temp2);
			angle=temp1-temp2;
			angle_deg=angle*180/3.141;
			pt_ang an =new pt_ang();
			an.pt=point_array[i];
			if (angle_deg<0) angle_deg=180 + angle_deg;
			an.a=angle_deg;
			obj_pt_ang[i-1]=an;
                        obj_pt_ang[i-1].index=i;
                        
                
                
                
                
                
                

		Arrays.sort(obj_pt_ang, new AngleComparator());
                //IF THE POINTS ARE TOO CLOSE THEN IGNORE IT
                
               java.util.ArrayList closePointRemoved = new  ArrayList();
               //closePointRemoved
               for(int j=0;j<obj_pt_ang.length;j++)
               {
                 closePointRemoved.add(obj_pt_ang[j]);
               }
               
               for(int j=0;j<closePointRemoved.size()-1;j++)
               {
                   if(((pt_ang)closePointRemoved.get(j)).pt.distance(((pt_ang)closePointRemoved.get(j+1)).pt)<50)
                       {
                         
                          closePointRemoved.remove(j+1);
                         
                      
                        /*Graphics g = WhiteBoard.jPanel1.getGraphics();
                        g.setColor(Color.BLUE);
			g.fillOval((int)((pt_ang)closePointRemoved.get(j+1)).pt.getX(),(int)((pt_ang)closePointRemoved.get(j+1)).pt.getY(),10,10);
                         */
                       j--;
                       }
               }
               pt_ang new_obj_pt_ang[] = new pt_ang[closePointRemoved.size()];
                
              for(i=0;i<closePointRemoved.size();i++)
               {
                new_obj_pt_ang[i]=(pt_ang)closePointRemoved.toArray()[i];
               }
               
                
		/*for(i=0;i<3; i++)		//obj_pt_ang.length-2;i++)
		{
			WhiteBoard.jPanel1.getGraphics().setColor(new Color(4));
			WhiteBoard.jPanel1.getGraphics().drawOval((int)obj_pt_ang[i].pt.getX(),(int)obj_pt_ang[i].pt.getY(),10,10);
                        //System.out.print(obj_pt_ang[i].a+"  "+(int)obj_pt_ang[i].pt.getX()+" "+(int)obj_pt_ang[i].pt.getY()+"\n");                        
		}*/

		//WhiteBoard.jPanel1.getGraphics().fillOval((int)point_array[0].getX(),(int)point_array[0].getY(),10,10);
         
		// compute the area of a triangle
		//area_triangle = isLeft(point_array[0], obj_pt_ang[0].pt, obj_pt_ang[1].pt) / 2.0;
                area_triangle = java.lang.Math.abs(isLeft(new_obj_pt_ang[0].pt, new_obj_pt_ang[1].pt, new_obj_pt_ang[2].pt) / 2.0);
                //System.out.print("-->> "+area_triangle);
		area_ratio=area_triangle/area;
		if(area_ratio>0.8 && length_by_peremeter>0.8 && length_by_peremeter<1.2)
		{
			System.out.println("TRIANGLE RECOGNIZED");
			return(morph_obj.drawTriangle(obj_pt_ang[0].pt,obj_pt_ang[1].pt,obj_pt_ang[2].pt));
		}
		else
                     return(isRectancle(point_array,new_obj_pt_ang));
                
                
                    //morph_obj.drawCurve(a);
                        
	}

	private Shape isRectancle(Point2D point_array[],pt_ang obj_pt_ang[])
	{
	/*	for(int i=0;i<4; i++)		//obj_pt_ang.length-2;i++)
		{
			Graphics g = WhiteBoard.jPanel1.getGraphics();
                        g.setColor(Color.GREEN);
			g.fillOval((int)obj_pt_ang[i].pt.getX(),(int)obj_pt_ang[i].pt.getY(),10,10);
		}*/
            
            
                    //WhiteBoard.jPanel1.getGraphics().fillOval((int)point_array[0].getX(),(int)point_array[0].getY(),10,10);
                    System.out.println("Quadrilateral RECOGNIZED");
                    //order the point in a sequence
                    Arrays.sort(obj_pt_ang,0,4,new SequenceComparator());
                   if(area_ratio>0.4 && area_ratio<0.6 && length_by_peremeter>0.8 && length_by_peremeter<1.2)
                    {
                   return(morph_obj.drawRectangle(obj_pt_ang[0].pt,
                                                     obj_pt_ang[1].pt, 
                                                      obj_pt_ang[2].pt,
                                                       obj_pt_ang[3].pt));
                   
                   }
                   return(morph_obj.drawCurve(p));
                   
                   

        }

}
