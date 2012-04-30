//package bluewin;

import java.awt.geom.*;
import java.awt.*;
import java.util.*;

public class Morph
{
	public Shape delete(Point2D[] point_array_out)
	{
		double minx=point_array_out[0].getX(),maxx=0,miny=point_array_out[0].getY(),maxy=0;

		for(int i=0;i<point_array_out.length;i++)
		{
			if(point_array_out[i].getX()<minx) minx=point_array_out[i].getX();
			if(point_array_out[i].getX()>maxx) maxx=point_array_out[i].getX();
			if(point_array_out[i].getY()<miny) miny=point_array_out[i].getY();
			if(point_array_out[i].getY()>maxy) maxy=point_array_out[i].getY();
		}
		//MainEntry.entry.getGraphics().clearRect((int)minx,(int)miny, (int)(maxx-minx),(int)( maxy-miny));
		Shape objDelete = new Shape(0);
        objDelete.storeWhiteRegion(minx,miny,maxx,maxy);
        return (objDelete);
	}

	public Shape drawLine(Point2D[] point_array_out)
	{
		double minx=point_array_out[0].getX(),maxx=0,miny=point_array_out[0].getY(),maxy=0;
		for(int i=0;i<point_array_out.length;i++)
		{
			if(point_array_out[i].getX()<minx) 
                        {
                            minx=point_array_out[i].getX();
                            miny=point_array_out[i].getY();
                            
                        }
			if(point_array_out[i].getX()>maxx) 
                        {
                            maxx=point_array_out[i].getX();
                            maxy=point_array_out[i].getY();
                        }
			

		}
		//System.out.println(minx+" "+miny+" "+maxx+" "+maxy) ;
		//WhiteBoard.jPanel1.getGraphics().clearRect(0,0, 600, 400);
		//Graphics g=MainEntry.entry.getGraphics();
                //g.drawLine((int)minx,(int)miny,(int)maxx,(int)maxy);
               
                
		Shape objLine = new Shape(1);
		objLine.storeLine(new Point((int)minx,(int)miny),new Point((int)maxx,(int)maxy));
		return(objLine);
	}

	Shape drawCircle(Point2D[] point_array_out,double area)
	{
		double sumx=0,sumy=0,radius;
		for(int i=0;i<point_array_out.length;i++)
		{
			sumx += point_array_out[i].getX();
			sumy += point_array_out[i].getY();
		}
		sumx /= point_array_out.length;
		sumy /= point_array_out.length;
		radius = java.lang.Math.sqrt(area / 3.141);
		//System.out.println(sumx+" "+sumy+ " hello "+radius);
		//WhiteBoard.jPanel1.getGraphics().clearRect(0,0, 600, 400);
		//MainEntry.entry.getGraphics().drawOval((int)(sumx-radius),(int)(sumy-radius),(int)radius*2,(int)radius*2);
		Shape objCircle = new Shape(2);
            objCircle.setCircle(radius,(Point2D)(new Point((int)sumx,(int)sumy)));
        return(objCircle);
	}

	Shape drawTriangle(Point2D a,Point2D b,Point2D c)
	{
		//Graphics g = MainEntry.entry.getGraphics();
                //g.clearRect(0,0, 600, 400);
                /*g.setColor(Color.RED);
               	g.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
		g.drawLine((int)b.getX(),(int)b.getY(),(int)c.getX(),(int)c.getY());
		g.drawLine((int)c.getX(),(int)c.getY(),(int)a.getX(),(int)a.getY());
                 */
                Point[] triangle = new Point[3];
                triangle[0]=(Point)a;
                triangle[1]=(Point)b;
                triangle[2]=(Point)c;
                
        Shape objTriangle = new Shape(3);
        objTriangle.setPolygon(triangle);
        return(objTriangle);
	}

	
	Shape  drawRectangle(Point2D a,Point2D b,Point2D c, Point2D d)
	{
                System.out.print(a+" "+b+" "+c+ " "+d);
               /* Graphics g = MainEntry.entry.getGraphics();
                //g.clearRect(0,0, 600, 400);
                g.setColor(Color.RED);
               	g.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
		g.drawLine((int)b.getX(),(int)b.getY(),(int)c.getX(),(int)c.getY());
		g.drawLine((int)c.getX(),(int)c.getY(),(int)d.getX(),(int)d.getY());
                g.drawLine((int)d.getX(),(int)d.getY(),(int)a.getX(),(int)a.getY());
                */
                Point[] quad = new Point[4];
                quad[0]=(Point)a;
                quad[1]=(Point)b;
                quad[2]=(Point)c;
                quad[3]=(Point)d;
        Shape objRectangle = new Shape(4);
        objRectangle.setPolygon(quad);
        return(objRectangle);
            
	}
        
        Shape drawLetter(char c,int x,int y)
        {
             Shape objLetter = new Shape(10);
             objLetter.storeLetter(c,x,y);
            // System.out.print(c+">>");
             return objLetter;
            
        }
        
        Shape drawCurve(Point2D a[]) 
        {
         
        /* ArrayList b = new ArrayList();
         int i=0,j=0;
         b.add(a[i]);
         for( i = 0, j = 1 ; j < a.length;)
         {
             if(a[i].distance(a[j]) > 10.0)
             {
                b.add(a[j]); 
                i=j;
                j++;
             }
             else
             {
                 j++;
             }
             
         }
         System.out.print(a.length+" - "+b.size());
         
         Point2D c[] = new Point2D[b.size()] ;
         for(i=0;i<b.size()-1;i++)
         {
             c[i]  = (Point2D)b.get(i);
             System.out.println(c[i]+"\n");
         }
         
         
         Shape objCurve = new Shape(5);
         objCurve.setPolygon(c);
         return objCurve;*/
            
            Shape objCurve = new Shape(5);
            return objCurve;
        }
        
	
}
