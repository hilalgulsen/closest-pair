package closestpair;
/**
 * The Pair class represents two points and their distance.
 * 
 * @author Hilal Gülþen
 */
public class Pair {
	private Point point1;
	private Point point2;
	private double distance;
	
	public Point getPoint1() {
		return point1;
	}
	public void setPoint1(Point point1) {
		this.point1 = point1;
	}
	public Point getPoint2() {
		return point2;
	}
	public void setPoint2(Point point2) {
		this.point2 = point2;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
