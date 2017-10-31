package closestpair;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * The ClosestPairFinderTest class includes unit tests.
 * It is checked whether the expected outputs are taken according to given input files.
 * 
 * @author Hilal Gulsen
 * @version v1.0
 * 30.10.2017
 */
public class ClosestPairFinderTest {
	ClosestPairFinder finder = new ClosestPairFinder();
	
	/**
	 * Checks expected line numbers and detected line numbers of closest pair using "sample_input_2_8.tsv" file.
	 * @throws IOException
	 */
	@Test
	public void testUsingInputFile_2_8() throws IOException {
		int lineNumber1 = 3;
		int lineNumber2 = 6;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = finder.compute("sample_input_2_8.tsv");
		int[] actuals = {closestPair.getPoint2().getLineNumber(),closestPair.getPoint1().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	
	/**
	 * Checks expected line numbers and detected line numbers of closest pair using "sample_input_3_1000.tsv" file.
	 * @throws IOException
	 */
	@Test
	public void testUsingInputFile_3_1000() throws IOException {
		int lineNumber1 = 223;
		int lineNumber2 = 388;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = finder.compute("sample_input_3_1000.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	
	/**
	 * Checks expected line numbers and detected line numbers of closest pair using "sample_input_4_4.tsv" file.
	 * @throws IOException
	 */
	@Test
	public void testUsingInputFile_4_4() throws IOException {
		int lineNumber1 = 2;
		int lineNumber2 = 3;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = finder.compute("sample_input_4_4.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	
	/**
	 * Checks expected line numbers and detected line numbers of closest pair using "sample_input_10_100.tsv" file.
	 * @throws IOException
	 */
	@Test
	public void testUsingInputFile_10_100() throws IOException {
		int lineNumber1 = 40;
		int lineNumber2 = 94;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = finder.compute("sample_input_10_100.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}
	
	/**
	 * Checks expected line numbers and detected line numbers of closest pair using "sample_input_100_100.tsv" file.
	 * @throws IOException
	 */
	@Test
	public void testUsingInputFile_100_100() throws IOException {
		int lineNumber1 = 48;
		int lineNumber2 = 96;
		int[] lineNumbers= {lineNumber1,lineNumber2};
		Pair closestPair;
		closestPair = finder.compute("sample_input_100_100.tsv");
		int[] actuals = {closestPair.getPoint1().getLineNumber(),closestPair.getPoint2().getLineNumber()};
		Assert.assertArrayEquals(lineNumbers, actuals);
	}

}
