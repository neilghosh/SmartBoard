//package bluewin;

import java.awt.geom.*;
import java.util.*;



class PointComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		Point2D p1 = (Point2D)o1;
		Point2D p2 = (Point2D)o2;
		if(p1.getX()<p2.getX())	return -1;
		else if(p1.getX()>p2.getX()) return 1;
			else if(p1.getY()<p2.getY()) return -1;
				else if(p1.getY()>p2.getY()) return 1;
					else return 0;
	}
}

public class ConvexHull
{
	private double isLeft(Point2D p0,Point2D p1,Point2D p2)
	{
		return ((p1.getX()-p0.getX())*(p2.getY()-p0.getY()))-((p2.getX()-p0.getX())*(p1.getY()-p0.getY()));
	}

	public Point2D[] createConvexHull(java.util.ArrayList pts) throws ArrayIndexOutOfBoundsException
	{
		//convert to array
		int size=pts.size();
		Point2D point_array[] = new Point2D[size];
		Point2D point_array_out[] = new Point2D[size];
		Point2D point_array_old[] = new Point2D[size];//backup

		for(int j=0;j<pts.size();j++)
		{
			point_array[j]=(Point2D)(pts.get(j));
			point_array_old[j]=(Point2D)(pts.get(j));
		}


		//shorting the input array
		Arrays.sort(point_array, new PointComparator());
		/*System.out.println("Before filtering");
		for (int i=0; i<point_array.length; i++)
		{
			System.out.println(point_array[i]);
		}
		*/

		int bot=0, top=(-1);  // indices for bottom and top of the stack
		int i;                // array scan index

		//get the special points
		int minmin = 0, minmax;
		double xmin;
		xmin = point_array[0].getX();
		for (i=1; i<size; i++)
		{
			if (point_array[i].getX()!= xmin) break;
		}

		minmax = i-1;
		if (minmax == size-1)
		{
			// degenerate case: all x-coords == xmin
			point_array_out[++top] = point_array[minmin];
			if (point_array[minmax].getY()!= point_array[minmin].getY())	// a nontrivial segment
			point_array_out[++top] = point_array[minmax];
			point_array_out[++top] = point_array[minmin];           		// add polygon endpoint
		}

		// Get the indices of points with max x-coord and min|max y-coord
		int maxmin, maxmax = size-1;
		double xmax = point_array[size-1].getX();
		for (i=size-2; i>=0; i--)
		{
			if (point_array[i].getX() != xmax) break;
		}
		maxmin = i+1;

		// Compute the lower hull on the stack point_array_out
		point_array_out[++top] = point_array[minmin];      // push minmin point onto stack
		i = minmax;
		while (++i <= maxmin)
		{
			// the lower line joins point_array[minmin] with point_array[maxmin]
			if (isLeft(point_array[minmin], point_array[maxmin], point_array[i]) >= 0 && i < maxmin) continue;          // ignorepoint_array[i] above or on the lower line

			while (top > 0)	// there are at least 2 points on the stack
			{
				// test if point_array[i] is left of the line at the stack top
				if (isLeft(point_array_out[top-1], point_array_out[top], point_array[i]) > 0) break;	// point_array[i] is a new hull vertex
				else top--;		// pop top point off stack
			}
			point_array_out[++top] = point_array[i];	// push point_array[i] onto stack
		}

		// Next, compute the upper hull on the stack H above the bottom hull
		if (maxmax != maxmin)
		{
			// if distinct xmax points
			point_array_out[++top] = point_array[maxmax];  // push maxmax point onto stack
		}

		bot = top;		// the bottom point of the upper hull stack
		i = maxmin;
		while (--i >= minmax)
		{
			// the upper line joins point_array[maxmax] with point_array[minmax]
			if (isLeft( point_array[maxmax], point_array[minmax], point_array[i]) >= 0 && i > minmax) continue;          // ignore point_array[i] below or on the upper line

			while (top > bot)    // at least 2 points on the upper stack
			{
				// test if point_array[i] is left of the line at the stack top
				if (isLeft( point_array_out[top-1], point_array_out[top], point_array[i]) > 0) break;         // point_array[i] is a new hull vertex
				else top--;         // pop top point off stack

            }
			point_array_out[++top] = point_array[i];       // push point_array[i] onto stack
		}
		if (minmax != minmin) point_array_out[++top] = point_array[minmin];  // push joining endpoint onto stack

		//System.out.println("AFter filtering");
		//create the final output array
		Point2D points_hull[]=new Point2D[top+1];
		for (i=0;i<=top;i++)
		{
			points_hull[i]=point_array_out[i];
			//System.out.println(point_array_out[i]);
		}



		/*System.out.println("AFter filtering1");
		for (i=0; i<points_hull.length; i++)
		{
			System.out.println(points_hull[i]);
		}
          */
		//Recoginze objRecoginze = new Recoginze();
		//objRecoginze.recognizeShape(point_array_old, points_hull);
		return(points_hull);
	}
}
