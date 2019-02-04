package controller;



import java.io.IOException;



import org.springframework.beans.factory.InitializingBean;



import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.scene.Parent;





public abstract class FXMLController implements Initializable, InitializingBean {



	protected Parent root;



	protected String fxmlFilePath;



	public void afterPropertiesSet() throws Exception {

		loadFXML();

	}



	protected final void loadFXML() throws IOException {	

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));

		loader.setController(this);

		this.root = loader.load();

	}



	public Parent getRoot() {

		return root;

	}

	

	public void setFxmlFilePath(String fxmlFilePath) {

		this.fxmlFilePath = fxmlFilePath;

	}

}