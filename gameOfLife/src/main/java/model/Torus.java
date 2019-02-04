package model;



/**

 * Encapsulates an 'int' array, where opposite sides are identified.

 * 

 * @author Andreas

 */

public class Torus implements TorusInterface {

	protected int[][] array;

	

	/**

	 * @param width Width of the array.

	 * @param height Height of the array.

	 * */

	public Torus(int width, int length){

		array = new int[width][length];

	}

	

	/**

	 * @return Width of array.

	 */

	public int getWidth() {

		return array.length;

	}

	

	/**

	 * @return Height of array.

	 */

	public int getHeight() {

		return array[0].length;

	}

	

	/**

	 * Divides a line into cells of length = period, starting at x = 0.

	 * Gives the distance between x and the beginning of the surrounding cell.

	 * @param x Position.

	 * @param period Length of cells.

	 * @return Position in the surrounding cell.

	 */

	private static int getPhase(int x, int period){

		if(x >= 0) return x % period;

		// check

		else return period - 1 + (x + 1) % period;

	}

	

	/**

	 * @param x The x-coordinate.

	 * @param y The y-coordinate.

	 * @return Value at the representative of position (x,y) in array.

	 */

	public int getValue(int x, int y) {

		return array[getPhase(x, getWidth())][getPhase(y, getHeight())];

	}

	

	/**

	 * @param x The x-coordinate.

	 * @param y The y-coordinate.

	 * @param value Value to put at the representative of position (x,y) in the array.

	 */

	public void setValue(int x, int y, int value) {

		array[getPhase(x, getWidth())][getPhase(y, getHeight())] = value;

	}

}