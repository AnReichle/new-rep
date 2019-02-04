package controller;



import java.net.URL;

import java.util.ResourceBundle;



import javafx.beans.property.BooleanProperty;

import javafx.beans.property.DoubleProperty;

import javafx.beans.property.LongProperty;

import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.beans.property.SimpleLongProperty;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;

import javafx.scene.control.Slider;



import model.TorusConfigurator;

import model.TorusModel;



public class DemoController extends FXMLController {

	@FXML private Button btnStart;

	@FXML private Button btnNext;

	@FXML private Button btnRandomize;

	

	@FXML private Slider fillFactor;

	@FXML private Slider frameDuration;

	

	@FXML private Canvas canvas;

	

	private TorusModel model;

	private TorusConfigurator configurator;

	

	private TorusToCanvasDisplayer displayer; 

	

	private DoubleProperty fillFactorProperty = new SimpleDoubleProperty();

	private BooleanProperty isRunningProperty = new SimpleBooleanProperty();

	private LongProperty frameDurationProperty = new SimpleLongProperty();

	

	public DemoController(TorusModel model, TorusConfigurator configurator) {

		this.model = model;

		this.configurator = configurator;

	}

	

	public void initialize(URL arg0, ResourceBundle arg1) {

		// default values

		fillFactorProperty.set(0.5);

		isRunningProperty.set(true);

		frameDurationProperty.set(10L);

		

		displayer = new TorusToCanvasDisplayer(canvas, model);

		

		setFillFactorSlider(fillFactor);

		setFrameDurationSlider(frameDuration);

		

		MainLoop loop = new MainLoop(() ->  {

			model.iterate();

			displayer.paint();

		});

		

		loop.getIsRunning().bind(isRunningProperty);

		loop.getIntervalBetweenFrames().bind(frameDurationProperty);

		

		loop.start();

	}

	

	@FXML protected void handleBtnStartAction(ActionEvent event) {

    	if(isRunningProperty.get()) {

    		isRunningProperty.set(false);

    		btnStart.setText("start");

    	}

    	else {

    		isRunningProperty.set(true);

    		btnStart.setText("stop");

    	}

	}

	

	@FXML protected void handleBtnNextAction(ActionEvent event) {

		model.iterate();

		displayer.paint();

	}

	

	@FXML protected void handleBtnRandomizeAction(ActionEvent event) {

		configurator.randomize(fillFactorProperty.get());

		displayer.paint();

	}

	

	private void setFillFactorSlider(Slider slider) {

		slider.valueProperty().addListener((observable, oldValue, newValue) -> {

			fillFactorProperty.set(newValue.doubleValue());

		});

	}

	

	private void setFrameDurationSlider(Slider slider) {

		slider.valueProperty().addListener((observable, oldValue, newValue) -> {

			frameDurationProperty.set(newValue.longValue());

			System.out.println("'frameDurationPoperty' in 'myController' has changed!");

		});

	}

}