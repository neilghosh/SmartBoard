import java.awt.geom.*;
import java.awt.*;
class Recreate {
    public static void drawImage(java.util.ArrayList objects) {
        Morph objMorph = new Morph();
        Shape objShape = new Shape();
       Graphics g = Master.getGraphicsFromMaster();
       
       g.clearRect(0,0,600,400);
              
        for(int i=0;i<objects.size();i++) {
            objShape=(Shape)(objects.get(i));
            drawShape(objShape);
        }
        
    }
     
    
    public static void drawShape(Shape objShape) throws java.lang.NullPointerException
       {
            Graphics g = Master.getGraphicsFromMaster();
            g.setColor(Color.BLACK);
            
            if (objShape.shapeType==0) {
                Point2D topLeft = objShape.vertex[0];
                Point2D bottomRight = objShape.vertex[1];
                g.clearRect((int)topLeft.getX(),(int)topLeft.getY(),(int)(bottomRight.getX()-topLeft.getX()),(int)(bottomRight.getY()-topLeft.getY()));
                
                
            }
            else if(objShape.shapeType==1) {
                Point2D topLeft = objShape.vertex[0];
                Point2D bottomRight = objShape.vertex[1];
                g.drawLine((int)topLeft.getX(),(int)topLeft.getY(),(int)bottomRight.getX(),(int)bottomRight.getY());
                
            }
            
            else if(objShape.shapeType==2) {
                Point2D centre = objShape.vertex[0];
                int radius     = (int)objShape.value[0];
                g.drawOval((int)(centre.getX()-radius),(int)(centre.getY()-radius),(int)radius*2,(int)radius*2);
                
            }
            else if(objShape.shapeType==3) {
                Point2D a = objShape.vertex[0];
                Point2D b = objShape.vertex[1];
                Point2D c = objShape.vertex[2];
                g.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
                g.drawLine((int)b.getX(),(int)b.getY(),(int)c.getX(),(int)c.getY());
                g.drawLine((int)c.getX(),(int)c.getY(),(int)a.getX(),(int)a.getY());
               
            }
            
            else if(objShape.shapeType==4) {
                
                Point2D a = objShape.vertex[0];
                Point2D b = objShape.vertex[1];
                Point2D c = objShape.vertex[2];
                Point2D d = objShape.vertex[3];
                g.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
                g.drawLine((int)b.getX(),(int)b.getY(),(int)c.getX(),(int)c.getY());
                g.drawLine((int)c.getX(),(int)c.getY(),(int)d.getX(),(int)d.getY());
                g.drawLine((int)d.getX(),(int)d.getY(),(int)a.getX(),(int)a.getY());
                
                
            }
            
            
             else if(objShape.shapeType==10) 
              {
               Point2D a = objShape.vertex[0];
               char c[]=new char[1];
               c[0]= (char)objShape.value[0];
               
               String b = new String(c) ;
             //  String b = new String("d") ;
               
               g.drawString(b,(int)a.getX(),(int)a.getY());
                
            }
            
            
             else if(objShape.shapeType==5) 
             {
                Graphics2D g2 = (Graphics2D)g;
                g2.drawString("PATTER NOT RECOGNIZED --WAIT FOR THE NEXT VERSION",0,20);
               /* Polygon pp = new Polygon();
                int p = objShape.vertex.length;
                GeneralPath curve = new GeneralPath();
                curve.reset();
                curve.moveTo((float)objShape.vertex[0].getX(),(float)objShape.vertex[0].getX());
                int x1,x2,x3,y1,y2,y3;
                    if(p >= 4)
                    {
                        for(int i=3; i < p-1; i++)
                        {
                        if((i%3) == 0){ //if i is a multiple of 3
                        x1 = (int)objShape.vertex[i-2].getX();
                        y1 = (int)objShape.vertex[i-2].getY();
                        x2 = (int)objShape.vertex[i-1].getX();
                        y2 = (int)objShape.vertex[i-1].getY();
                        x3 = (int)objShape.vertex[i].getX();
                        y3 = (int)objShape.vertex[i].getY();
                        curve.curveTo(x1,y1,x2,y2,x3,y3);
                        
                        pp.addPoint(x1,y1);
                        //System.out.print(x1+" "+x1);
                        //g.setColor(Color.BLUE);
                        //g.drawLine(x1,y1,x2,y2);
                        }
                    }
                      
                 Graphics2D g2 = (Graphics2D)g;
                 //System.out.print(curve.toString());
                 //g2.setColor(Color.BLUE);
                 //curve.closePath();
                // g2.setColor(Color.blue);
                 //g2.drawPolygon(pp);
                 //g2.setColor(Color.green);
                // g2.draw(curve);
                */
                
                
                
                
                
                
              
                  
                  
          
            
             
        
     
    }
}
    
    
    
    
}

