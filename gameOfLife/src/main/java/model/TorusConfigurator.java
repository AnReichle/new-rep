package model;



import java.util.Random;



/**

 * Provides methods to set the configuration of the tours, set as its variable.

 * 

 * @author Andreas

 */



public class TorusConfigurator{

	private TorusInterface torus;

	

	public TorusConfigurator(TorusInterface torus){

		this.torus = torus;

	}

	

	/**

	 * Fills the torus randomly with 0 and 1.

	 * @param fillFactor The probability for a cell to contain 1.

	 */

	public void randomize(double fillFactor) {

		Random random = new Random();

		for(int i=0;i<torus.getWidth();i++) {

			for(int j=0;j<torus.getHeight();j++) {

				torus.setValue(i,j,random.nextDouble() > fillFactor? 0 : 1);

			}

		}

	}

	

	/**

	 * Sets all cells to 0.

	 */

	public void clear() {

		for(int i=0;i<torus.getWidth();i++) {

			for(int j=0;j<torus.getHeight();j++) {

				torus.setValue(i,j,0);

			}

		}

	}

	

	public void setTorus(TorusInterface torus) {

		this.torus = torus;

	}

	

	public TorusInterface getTorus(){

		return torus;

	}

}