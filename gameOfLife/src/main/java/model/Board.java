package model;



/**

 * Represents the cells as an 'int' array and enforces the iteration laws.

 * The cell value cv <= 0 marks dead cells and cv > 0 marks cells that are alive since cv generations.

 * 

 * @author Andreas

 */



public class Board extends Torus implements TorusModel {



	/**

	 * Creates a board, where all cells are dead.

	 * @param width Width of the array.

	 * @param length Height of the array.

	 */

	public Board(int width, int length){

		super(width, length);

	}

	

	/**

	 * Creates a copy of b.

	 * @param b Board to be copied.

	 */

	public Board(Board b){

		super(b.getWidth(), b.getHeight());

		array = b.array.clone();

	}

	

    /**

     * Creates a board with the configuration given by array.

     * @param array 

     */

	public Board(int[][] array){

		super(array.length, array[0].length);

		this.array = array;

	}

	

	/**

	 * Counts the number of alive cells in the neighborhood of (x,y).

	 * @param x The x-coordinate.

	 * @param y The y-coordinate.

	 * @return Number of alive cells in the neighborhood of (x,y).

	 */

	private int countAliveNeighbors(int x, int y) {

		int countNeigh = 0;

		for(int i = -1; i < 2; i++) {

			for(int j = -1; j < 2; j++) {

				if(!(i == 0 && j == 0) && getValue(x+i, y+j) > 0) countNeigh++;

			}

		}

		return countNeigh;

	}

	

	/**

	 * Calculates the cell value in the next iteration dependent on the current cell value and number of alive neighbors.

	 * @param value Cell value.

	 * @param neighCount Number of alive neighbors.

	 * @return Cell value in the next generation.

	 */

	private static int calculateNewCellAge(int value, int neighCount) {

		if(value <= 0 && neighCount == 3) return 1;

		if(value > 0 && (neighCount == 2 || neighCount == 3)) return value + 1;

		else return 0;

	}

	

	/**

	 * Advances the board to the next generation of cells.

	 */

	public void iterate(){

		// try

		// Board copy = new Board(this);

		Board temp = new Board(getWidth(), getHeight());

		for(int i = 0; i < getWidth(); i++) {

			for(int j = 0; j < getHeight(); j++) {

				temp.setValue(i,j, getValue(i, j));

			}

		}

		for(int i = 0; i < getWidth(); i++) {

			for(int j = 0; j < getHeight(); j++) {

				setValue(i,j, calculateNewCellAge(temp.getValue(i, j), temp.countAliveNeighbors(i, j)));

			}

		}

	}

}