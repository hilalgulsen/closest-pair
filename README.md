# Closest Pair of Points
This is a Java program which reads a text file where each line contains the coordinates of a multidimensional point and then looks for the closest pair of points in the file. If the program has found the closest pair of points, it outputs the line numbers and the coordinates of the two closest points.
 
The text file contains one point per line. The coordinate values are separated by a tabulator character. The coordinate values are not necessarily integers. In the case of a floating point coordinate value, the decimal separator is a period.

# Usage instructions
Input files should be under input folder. Name of input file should be passed by command line argument. For example, if sample_input_2_8.tsv file is going to be used, command line argument must be **sample_input_2_8.tsv**.

Program produces output file under output folder. It is named as **sample_output_dimension_numberOfPoints.txt**.

    If there are less than 2 points in input file, program outputs "There are no more points to pair" message.
    If file is not in right directory, program outputs "File was not found under input folder" message.

# Solution of the Problem
First of all, the file is read to extract the number of points along with the information about the dimension and the line numbers. These information is kept as a *Point class*. Note that, number of points and the dimension info can be obtained from the name of the files for this particular task. However, the name of the given files might be arbitrary. Therefore, aformentioned info is extracted by reading the file. 
For 2D points Divide and Conquer algorithm is implemented to obtain a better time complexity. For higher dimensional points, Brute Force algorithm is applied. 

## Brute Force
This algorithm basically checks every pair of points using two nested for loops and determines the closest pair via Euclidean distance formula.

## Divide And Conquer
We partition S into S1, S2 by vertical line defined by median x-coordinate in S. Sort points according to y-coordinate. To sort points according to x and y coordinates Comparator interface is used.
Recursively compute closest pair distances δ1 and δ2. Set δ = min(δ1, δ2). Now compute the closest pair with one point each in S1 and S2.
In each candidate pair (p, q), where p ∈ S1 and q ∈ S2, the points p, q must both lie within δ of vertical line.
Consider a point p ∈ S1. All points of S2 within distance δ of p must lie in a δ × 2δ rectangle R.
Build an array *strip* of all such points.
Find the smallest distance in *strip*.
Return the minimum of d and closest distance in strip.

# Complexity
Brute Force algorithm takes O(n^2) time due to nested for loops.
Divide and Conquer algorithm divides all points in two sets and recursively calls for two sets. After dividing, it finds the strip in O(n) time. Also, it takes O(n) time to divide the Py array around the mid vertical line. Finally finds the closest points in strip in O(n) time. So T(n) can expressed as follows. 

    T(n) = 2T(n/2) + O(n) + O(n) + O(n)
    T(n) = 2T(n/2) + O(n)
    T(n) = T(nLogn)
Therefore, this algorithm takes O(n(Logn) time.

# Tests
JUnit test case is implemented to check whether the expected outputs are taken according to given inputs. Numerous tests are undertaken for five given input files which contain different number of points with various dimensions. The output files obtained from tests are aligned with the given sample output files.
