package closestpair;

/**
 * The ClosestPair class holds closest pair.
 * @author Hilal Gulsen
 * @version v1.0
 * 30.10.2017
 */
public class ClosestPair {
	private static Pair closestPair;

	public static Pair getClosestPair() {
		return closestPair;
	}

	public static void setClosestPair(Pair closestPair) {
		ClosestPair.closestPair = closestPair;
	}
}
