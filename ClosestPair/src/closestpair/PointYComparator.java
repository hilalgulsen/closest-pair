package closestpair;

import java.util.Comparator;
/**
 * The PointYComparator class compares points according to y coordinates using Comparator interface.
 * @author Hilal Gulsen
 * @version v1.0
 * 30.10.2017
 */
public class PointYComparator implements Comparator<Point> {
	
	@Override
	public int compare(Point point1, Point point2) {
		if(point1.getCoordinates()[1]>point2.getCoordinates()[1]) {
			return 1;
		}
		else if(point1.getCoordinates()[1]<point2.getCoordinates()[1]) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
