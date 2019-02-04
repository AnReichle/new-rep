



import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;



import controller.DemoController;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;



public class Main extends Application {

	

	public static void main(String[] args) {

		launch(args);

	}



	@Override

	public void start(Stage primaryStage) throws Exception {

	    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	    DemoController myController = (DemoController) context.getBean("myController");

	      

        primaryStage.setScene(new Scene(myController.getRoot()));

        primaryStage.setTitle("Game of Life");

        primaryStage.show();	

        

        ((ClassPathXmlApplicationContext) context).close();

	}

}