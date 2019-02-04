package controller;





import javafx.animation.AnimationTimer;

import javafx.beans.property.BooleanProperty;

import javafx.beans.property.LongProperty;

import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.SimpleLongProperty;



/**

 * Repeats a task periodically.

 * 

 * @author Andreas

 */



public class MainLoop extends AnimationTimer{

	private BooleanProperty isRunning = new SimpleBooleanProperty();

	private LongProperty intervalBetweenFrames = new SimpleLongProperty();

	

	private Runnable task;

	

	private long timeLastFrame;

	

	/**

	 * @param task Functional interface that encapsulates the repeated task.

	 */

	public MainLoop(Runnable task) {

		// default values

		isRunning.set(true);

		intervalBetweenFrames.set(10L);



		this.task = task;

		timeLastFrame = System.currentTimeMillis();

		

		// for debugging

		intervalBetweenFrames.addListener( (o, oldVal, newVal) -> {

			System.out.println("'intervalBetweenFrames' in 'loop' has changed!");

		});

	}

	

	@Override

	public void handle(long now) {

		if(isRunning.get() && (now-timeLastFrame) > intervalBetweenFrames.get()) {

			timeLastFrame = now;

			task.run();

		}

	}	

	

	public BooleanProperty getIsRunning() {

		return isRunning;

	}

	

	public LongProperty getIntervalBetweenFrames() {

		return intervalBetweenFrames;

	}

	

	public void setIsRunning(BooleanProperty isRunning) {

		this.isRunning = isRunning;

	}

	

	public void setIntervalBetweenFrames(LongProperty intervalBetweenFrames) {

		this.intervalBetweenFrames = intervalBetweenFrames;

	}

}