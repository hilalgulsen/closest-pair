package closestpair;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClosestPairOfPointsTest {
	ClosestPairOfPoints obj = new ClosestPairOfPoints();
	@Test
	public void testUsingInputFile_2_8() throws IOException {
		int lineNumber1 = 3;
		int lineNumber2 = 6;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = obj.compute("sample_input_2_8.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	@Test
	public void testUsingInputFile_3_1000() throws IOException {
		int lineNumber1 = 223;
		int lineNumber2 = 388;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = obj.compute("sample_input_3_1000.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	@Test
	public void testUsingInputFile_4_4() throws IOException {
		int lineNumber1 = 2;
		int lineNumber2 = 3;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = obj.compute("sample_input_4_4.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	@Test
	public void testUsingInputFile_10_100() throws IOException {
		int lineNumber1 = 40;
		int lineNumber2 = 94;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = obj.compute("sample_input_10_100.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	@Test
	public void testUsingInputFile_100_100() throws IOException {
		int lineNumber1 = 48;
		int lineNumber2 = 96;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = obj.compute("sample_input_100_100.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}

}
