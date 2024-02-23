import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/scene.fxml"));
        AnchorPane pane = loader.load();

        Scene s = new Scene(pane);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
