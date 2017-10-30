package closestpair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ClosestPairOfPoints {
	private int pointCount;
	
	public static void main(String[] args) throws IOException{
		ClosestPairOfPoints object = new ClosestPairOfPoints();
		object.compute(args[0]);
	}
	
	public Pair compute(String fileName) throws IOException{
		Point[] pointArray = readFromFile(fileName);
		Pair closestPair = findPair(pointArray);
		writeToFile(closestPair);
		return closestPair;
	}
	
	public Pair findPair(Point[] points) {
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
		return pair;
	}
	
	public double euclideanDistance(Point point1,Point point2) {
		double total = 0;
		for(int i=0; i<point1.getDimension();i++) {
			total += Math.pow(point1.getCoordinates()[i]-point2.getCoordinates()[i], 2.0);
		}
		return total;
	}
	public Point[] readFromFile(String fileName) throws IOException {
		File file = new File("./input/"+fileName);
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
	public void writeToFile(Pair pair) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("./output/sample_output_"+pair.getPoint1().getDimension()+"_"+pointCount+".txt","UTF-8");
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(int i=0;i<pair.getPoint1().getDimension();i++) {
			builder1.append(Math.round(pair.getPoint1().getCoordinates()[i]));
			builder1.append("\t");
			builder2.append(Math.round(pair.getPoint2().getCoordinates()[i]));
			builder2.append("\t");
		}
		writer.println(pair.getPoint1().getLineNumber()+":"+builder1.toString());
		writer.println(pair.getPoint2().getLineNumber()+":"+builder2.toString());
		writer.close();
	}
}
