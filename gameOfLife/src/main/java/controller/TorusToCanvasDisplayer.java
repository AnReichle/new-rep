package controller;



import model.TorusInterface;



import javafx.beans.property.DoubleProperty;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;



/**

 * Displays a torus on a flat canvas and allows to drag the display via mouse input.

 * 

 * @author Andreas

 */



public class TorusToCanvasDisplayer {

	private DoubleProperty cornerX = new SimpleDoubleProperty();

	private DoubleProperty cornerY = new SimpleDoubleProperty();

	private DoubleProperty cellLength = new SimpleDoubleProperty();

	

	private GraphicsContext gc;

	private double canvasHeight, canvasWidth;

	private TorusInterface torus;

	

	/**

	 * @param canvas The canvas associated with the displayer.

	 * @param TorusInterface The torus to be displayed.

	 */

	public TorusToCanvasDisplayer(Canvas canvas, TorusInterface torus){

		this.torus = torus;

		gc = canvas.getGraphicsContext2D();

		

		// default values

		cornerX.set(0);

		cornerY.set(0);

		cellLength.set(10);

		

		canvasWidth = canvas.getWidth();

		canvasHeight = canvas.getHeight();

	}

	

	/**

	 * Displays the torus on the canvas, showing cells with value < 1 in black and cells with value > 0 in white.

	 * The image section is uniquely determined by cornerX, cornerY and cellLength. 

	 */

	public void paint(){

		double cl = cellLength.get();

		for(int i = (int) - Math.ceil(cornerX.get() / cl); i < (canvasWidth - cornerX.get()) / cl ;i ++) {

			for(int j = (int) - Math.ceil(cornerY.get() / cl); j < (canvasHeight - cornerY.get()) / cl ;j ++) {

				if(torus.getValue(i, j) > 0) {

					gc.setFill(Color.BLACK);

				} else gc.setFill(Color.WHITE);

				gc.fillRect(cornerX.get() + i * cl, cornerY.get() + j * cl, cl, cl);

			}

		}

	}



	public DoubleProperty getCornerX() {

		return cornerX;

	}

	

	public DoubleProperty getCornerY() {

		return cornerY;

	}

	

	public DoubleProperty getCellLength() {

		return cellLength;

	}

	

	public void setCornerX(DoubleProperty cornerX) {

		this.cornerX = cornerX;

	}

	

	public void setCornerY(DoubleProperty cornerY) {

		this.cornerY = cornerY;

	}

	

	public void setCellLength(DoubleProperty cellLength) {

		this.cellLength = cellLength;

	}

}