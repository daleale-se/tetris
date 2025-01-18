package classes.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    private Grid grid;
    private Text gridDisplay;

    @Override
    public void start(Stage primaryStage) {
        grid = new Grid();

        StackPane root = new StackPane();
        gridDisplay = new Text();
        gridDisplay.setText(gridToString());
        gridDisplay.setStyle("-fx-font-family: monospace; -fx-font-size: 28;");
        root.setAlignment(Pos.CENTER); // Center the text
        root.getChildren().add(gridDisplay);

        Scene scene = new Scene(root, 400, 800);
        primaryStage.setTitle("Tetris Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            grid.updateTetromino();
            updateGridDisplay();
            grid.deleteTetromino();
            grid.dropTetromino();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN:
                    grid.deleteTetromino();
                    grid.dropTetromino();
                    grid.updateTetromino();
                    updateGridDisplay();
                    break;
                case LEFT:
                    grid.deleteTetromino();
                    grid.moveTetronominoToLeft();
                    grid.updateTetromino();
                    updateGridDisplay();
                    break;
                case RIGHT:
                    grid.deleteTetromino();
                    grid.moveTetronominoToRight();
                    grid.updateTetromino();
                    updateGridDisplay();
                    break;
                case UP:
                    grid.deleteTetromino();
                    grid.rotateTetronomino();
                    grid.updateTetromino();
                    updateGridDisplay();
                    break;
            }
        });
    }

    private void updateGridDisplay() {
        gridDisplay.setText(gridToString());
    }

    private String gridToString() {
        return grid.getGridRepresentation();
    }

    public void startGame(String[] args) {
        launch(args);
    }

}
