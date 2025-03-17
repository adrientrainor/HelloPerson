import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ClickCounter extends Application {
	Button btn;
	Label lbl;
	int iClickCount = 0;

	public ClickCounter() {
		// Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create button
		btn = new Button();
		btn.setText("CLICK ME");
		btn.setOnAction(e->buttonClick());
		
		// Create label
		lbl = new Label();
		lbl.setText("0 clicks");
		
		// Add button & label to new layout pane
		BorderPane pane = new BorderPane();
		pane.setTop(lbl);
		pane.setCenter(btn);
		
		// Add layout pane to new scene, then show
		Scene scene = new Scene(pane, 250, 150);
		
		// Add scene to stage & set title
		primaryStage.setScene(scene);
		primaryStage.setTitle("Click counter");
		primaryStage.show();
	}
	
	public void buttonClick() {
		iClickCount++;
		if (iClickCount == 1) {
			lbl.setText("1 click");
		} else {
			lbl.setText(iClickCount + "clicks");
		}
	}

}
