package closestpair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * The ClosestPairFinder class is main class.Reading points from file,
 * finding closest pair of points, writing line numbers and coordinates
 * of closest pair to file are implemented in this class.
 * 
 * @author Hilal Gulsen
 * @version v1.0
 * 30.10.2017
 */
public class ClosestPairFinder {
	/**Number of points in input file */
	private int pointCount;
	
	/**
	 * Takes input file name as command line argument and calls compute
	 * method to find closest pair.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		ClosestPairFinder finder = new ClosestPairFinder();
		final long startTime = System.currentTimeMillis();				//start time of system.
		finder.compute(args[0]);
		final long totalTime = System.currentTimeMillis() - startTime; 	//end time of system.
		System.out.println("Running time of system : " + totalTime + " milliseconds");
	}
	
	/**
	 * Calls readFromFile method to read points from file
	 * and calls appropriate algorithm according to dimension of these points.
	 * Finds closest pair and calls writeToFile method to write line number
	 * and coordinates of closest pair.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public Pair compute(String fileName) throws IOException{
		ClosestPair closest = new ClosestPair();
		Point[] pointArray = readFromFile(fileName);
		if(pointArray.length<2) {	//If there are less than two points, it outputs message to console
			if(pointArray[0].getDimension()!=0) {
				System.out.println("There are no more points to pair");
			}
			Pair err = new Pair();
			err.setDistance(-1);
			return err;
		}
		else {
			if(pointArray[0].getDimension()==2) {
				Point[] pointsY = new Point[pointArray.length];
				for(int i=0;i<pointArray.length;i++) {
					pointsY[i] = pointArray[i];
				}
				Arrays.sort(pointArray, new PointXComparator());
				Arrays.sort(pointsY,new PointYComparator());
				divideAndConquer(pointArray,pointsY,pointCount);
				writeToFile(closest.getClosestPair());
				return closest.getClosestPair();
			}
			else {
				Pair pair = findPairBruteForce(pointArray,pointCount);
				writeToFile(pair);
				return pair;
			}
		}

	}
	
	/**
	 * Finds closest pair using brute force.
	 * @param points 
	 * @param pointCount
	 * @return
	 */
	public Pair findPairBruteForce(Point[] points,int pointCount) {
		double min = Double.MAX_VALUE;
		Pair pair = new Pair();
		for(int i=0;i<points.length;i++) {
			for(int j=i+1;j<points.length;j++) {
				double distance = euclideanDistance(points[i],points[j]);
				if(distance<min) {
					min=distance;
					pair.setPoint1(points[i]);
					pair.setPoint2(points[j]);
					pair.setDistance(min);
				}
			}
		}
		
		ClosestPair closestPair = new ClosestPair();
		closestPair.setClosestPair(pair);
		return pair;
	}

	/**
	 * A recursive function to find smallest distance
	 * @param points All points which are sorted according to x coordinates
	 * @param pointCount
	 * @return
	 */
	public double divideAndConquer(Point[] points,Point[] pointsY,int pointCount) {
		if(pointCount<=3) {
			return findPairBruteForce(points,pointCount).getDistance();
		}
		//Find middle point
		int mid = pointCount/2;
		Point midPoint = points[mid];
		
		Point[] rightHalve = new Point[points.length - mid];
		for (int i = 0; i < rightHalve.length; i++) {
			rightHalve[i] = points[i+mid];
		}

		// Divide points in y sorted array around the vertical line.
		List<Point> yl = new ArrayList<>();
		List<Point> yr = new ArrayList<>();

	    for (int i = 0; i < pointCount; i++)
	    {
	      if (pointsY[i].getCoordinates()[0] <= midPoint.getCoordinates()[0])
	    	  yl.add(pointsY[i]);
	      else
	    	  yr.add(pointsY[i]);
	    }
	    Point[] Pyl = yl.toArray(new Point[0]);
	    Point[] Pyr = yr.toArray(new Point[0]);
		//Think the vertical line which is going over middle point.
		//dl is minimum distance on left of vertical line and dr is minimum distance on right of vertical line.
		double dl = divideAndConquer(points,Pyl,Pyl.length);
		double dr = divideAndConquer(rightHalve,Pyr,Pyr.length);
		double d = Math.min(dl, dr);
		
		//Create an array strip to hold points which are closer than d to vertical line
		Point[] strip = new Point[pointCount];
		int j = 0;
		for(int i=0; i<pointCount ;i++) {
			if(Math.abs(points[i].getCoordinates()[0]-midPoint.getCoordinates()[0])<d) {
				strip[j] = points[i];
				j++;
			}
		}
		//Find minimum of d and minimum distance in strip points
		return Math.min(d, closestStrip(strip,j,d));
	}
	
	/**
	 * Finds the distance between the closest points of strip of given size.
	 * All points in strip[] are sorted according to y coordinate.
	 * They all have an upper bound on minimum distance as d.
	 * @param strip
	 * @param size
	 * @param d
	 * @return
	 */
	public double closestStrip(Point strip[], int size, double d)
	{
	    double min = d;

	    for (int i = 0; i < size; ++i) {
	    	for (int j = i+1; j < size && (strip[j].getCoordinates()[1] - strip[i].getCoordinates()[1]) < min; ++j) {
	    		if (euclideanDistance(strip[i],strip[j]) < min) {
	    			 min = euclideanDistance(strip[i], strip[j]);
	    		}
	    	}
	    }
	    return min;
	}
	
	/**
	 * Calculates distance between two points.
	 * @param point1
	 * @param point2
	 * @return
	 */
	public double euclideanDistance(Point point1,Point point2) {
		double total = 0;
		for(int i=0; i<point1.getDimension();i++) {
			total += Math.pow(point1.getCoordinates()[i]-point2.getCoordinates()[i], 2.0);
		}
		return total;
	}
	
	/**
	 * Reads given input file in input folder and creates array which contains points.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public Point[] readFromFile(String fileName) throws IOException {
		File file = new File("./input/"+fileName);
		if(file.exists() && !file.isDirectory()) {
			List<String> lines = Files.readAllLines(file.toPath());
			pointCount = lines.size();
			int lineNumber = 1;
			int numberOfPoints = lines.size();
			Point[] points = new Point[numberOfPoints];
			String row = lines.get(0);
			String[] rowCoordinates = row.split("\t");
			int dimension = rowCoordinates.length;
			for(String line:lines) {
				Point point = new Point();
				String[] coordinates = line.split("\t");
				Double[] fCoordinates = new Double[dimension];
				int index = 0;
				for(String coordinate:coordinates) {
					fCoordinates[index] = Double.parseDouble(coordinate);
					index++;
				}
				point.setLineNumber(lineNumber);
				point.setCoordinates(fCoordinates);
				point.setDimension(dimension);
				lineNumber++;
				points[lineNumber-2] = point;
			}
			return points;
		}
		else {
			System.out.println("File was not found under input folder");
			Point[] err = new Point[1];
			err[0] = new Point();
			err[0].setDimension(0);
			return err;
		}

	}
	
	/**
	 * Writes line number and coordinates of closest pair to txt file.
	 * File is placed under output folder and named as sample_output_dimension_pointcount.txt.
	 * @param pair
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeToFile(Pair pair) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("./output/sample_output_"+pair.getPoint1().getDimension()+"_"+pointCount+".txt","UTF-8");
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		
		for(int i=0;i<pair.getPoint1().getDimension();i++) {
			double coord1 = notation(pair.getPoint1().getCoordinates()[i]);
			double coord2 = notation(pair.getPoint2().getCoordinates()[i]);
			
			if(coord1%1==0) {
				builder1.append((int) coord1);
				builder1.append("\t");
			}
			else {
				builder1.append(coord1);
				builder1.append("\t");
			}
			if(coord2%1==0) {
				builder2.append((int) coord2);
				builder2.append("\t");
			}
			else {
				builder2.append(coord2);
				builder2.append("\t");
			}
		}
		int lineNumber1 = pair.getPoint1().getLineNumber();
		int lineNumber2 = pair.getPoint2().getLineNumber();
		if(lineNumber1<lineNumber2) {
			writer.println(lineNumber1+":"+builder1.toString());
			writer.println(lineNumber2+":"+builder2.toString());
		}
		else {
			writer.println(lineNumber2+":"+builder2.toString());
			writer.println(lineNumber1+":"+builder1.toString());
		}
		writer.close();
	}
	public static double notation(double num) {
		int decimal = (int) num;
		double fraction = Math.abs(num - decimal);
		int digitLength = String.valueOf(Math.abs(decimal)).length();
		int indicatorPlace = 7 - digitLength;
		int indicator = (int)(Math.abs(fraction) * Math.pow(10,indicatorPlace)) % 10;
		double result;
		int roundingPoint = 7-digitLength-1;
		if(indicator>=5) {
			if(7-digitLength-1>0) {
				//fraction += Math.pow(10, -roundingPoint);
				String fractionDigit = String.join("", Collections.nCopies(roundingPoint, "#"));
				String dFormat = "#." + fractionDigit;
				DecimalFormat df = new DecimalFormat(dFormat);
				String f = df.format(fraction).replace(',', '.');
				double newFraction = Double.parseDouble(f);
				
				if(num<0) {
					result = decimal - newFraction;
				}
				else {
					result = decimal + newFraction;
				}
			}
			else if (7-digitLength-1 ==0){
				if(num<0) {
					result = decimal - 1;
				}
				else {
					result = decimal + 1;
				}
			}
			else {
				result = decimal;
			}
		}
		else {
			if(7-digitLength-1>0) {
				String fractionDigit = String.join("", Collections.nCopies(roundingPoint, "#"));
				String dFormat = "#." + fractionDigit;
				DecimalFormat df = new DecimalFormat(dFormat);
				String f = df.format(fraction).replace(',', '.');
				double newFraction = Double.parseDouble(f);
				if(num<0) {
					result = decimal - newFraction;
				}
				else {
					result = decimal + newFraction;
				}
			}
			else {
				result = decimal;
			}
		}
		return result;
	}
}
