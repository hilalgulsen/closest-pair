package closestpair;

import java.util.Comparator;
/**
 * The PointXComparator class compares points according to x coordinates using Comparator interface.
 * @author Hilal Gülþen
 */
public class PointXComparator implements Comparator<Point>{

	@Override
	public int compare(Point point1, Point point2) {
		if(point1.getCoordinates()[0]>point2.getCoordinates()[0]) {
			return 1;
		}
		else if(point1.getCoordinates()[0]<point2.getCoordinates()[0]) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
}
