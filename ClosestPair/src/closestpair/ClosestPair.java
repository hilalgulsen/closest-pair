package closestpair;

/**
 * The ClosestPair class holds closest pair.
 * @author Hilal G�l�en
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
