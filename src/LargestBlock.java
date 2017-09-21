/**
 * Written by: C. West
 * Exercise: 22.19  
 * Date: 08/14/2014
 * Purpose: Find the largest block of 1's in a 10x10 grid; Also, change values to find the largest as well.
 **/
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LargestBlock extends Application {

    Random random = new Random();
    GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        TextField[][] txtFields = new TextField[10][10];
        refresh(txtFields);
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> refresh(txtFields));
        Button findLargest = new Button("Find Largest Block");
        findLargest.setOnAction(e -> findLargest(txtFields));

        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(refreshBtn, findLargest);
        hbox.setAlignment(Pos.CENTER);

        pane.setCenter(grid);
        pane.setBottom(hbox);
        Scene scene = new Scene(pane, 350, 300);
        primaryStage.setTitle("Largest Block");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refresh(TextField txtFields[][]) {
        grid.getChildren().clear();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int r = random.nextInt() % 2;
                txtFields[i][j] = new TextField(Math.abs(r) + "");
                txtFields[i][j].setAlignment(Pos.CENTER);
                txtFields[i][j].setEditable(true);
                grid.add(txtFields[i][j], i, j);
            }
        }
    }

    public void findLargest(TextField txtFields[][]) {
        int max = 0;
        int maxX = 0;
        int maxY = 0;
        int[][] matrix = new int[txtFields.length][txtFields[0].length];

        for (int i = 0; i < txtFields.length; i++) {
            for (int j = 0; j < txtFields[i].length; j++) {
                txtFields[i][j].setStyle("-fx-background-fill: white");
                if (txtFields[i][j].getText().equals("0")) {
                    matrix[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    matrix[i][j] = 1;
                } else {
                    int p = Math.min(matrix[i][j - 1], matrix[i - 1][j]);
                    int n = Math.min(p, matrix[i - 1][j - 1]);
                    matrix[i][j] = 1 + n;

                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                        maxX = i;
                        maxY = j;
                    }
                }
            }
        }

        for (int x = maxX - max; x < maxX; x++) {
            for (int y = maxY - max; y < maxY; y++) {
                txtFields[x + 1][y + 1].setStyle("-fx-background-color: grey");
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
