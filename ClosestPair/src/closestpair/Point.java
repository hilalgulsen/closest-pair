package closestpair;
/**
 * The Point class holds dimension, coordinates and line number of points.
 * 
 * @author Hilal Gulsen
 * @version v1.0
 * 30.10.2017
 */
public class Point{
	private int dimension;
	private Double[] coordinates;
	private int lineNumber;
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public Double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
