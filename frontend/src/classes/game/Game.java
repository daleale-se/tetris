package classes.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Application {
    private Grid grid;
    private GridPane gridPane;
    private GridPane nextTetrominoes;
    private boolean isDropping = false;
    private boolean isPaused = false;

    @Override
    public void start(Stage primaryStage) {
        grid = new Grid();

        gridPane = new GridPane();
        // gridPane.setAlignment(Pos.CENTER);
        nextTetrominoes = new GridPane();
        // nextTetrominoes.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderedPane = new BorderPane();
        borderedPane.setCenter(gridPane);
        borderedPane.setRight(nextTetrominoes);
        borderedPane.setBackground(new Background(new BackgroundFill(
                Color.BLACK,
                CornerRadii.EMPTY,
                null
        )));

        Scene scene = new Scene(borderedPane, 400, 800);
        primaryStage.setTitle("Tetris Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            if (!isDropping) {
                isDropping = true;
                processDrop();
                isDropping = false;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN:
                    if (!isDropping && !isPaused) {
                        isDropping = true;
                        processDrop();
                        isDropping = false;
                    }
                    break;
                case LEFT:
                    if (!isPaused) {
                        grid.deleteTetrominoTwo();
                        grid.moveTetronominoToLeft();
                        grid.updateTetromino();
                        displayGrid();
                    }
                    break;
                case RIGHT:
                    if (!isPaused) {
                        grid.deleteTetrominoTwo();
                        grid.moveTetronominoToRight();
                        grid.updateTetromino();
                        displayGrid();
                    }
                    break;
                case UP:
                    if (!isPaused) {
                        grid.deleteTetromino();
                        grid.rotateTetronomino();
                        grid.updateTetromino();
                        displayGrid();
                    }
                    break;
                case SPACE:
                    if (!isPaused) {
                        grid.deleteTetromino();
                        grid.instantDrop();
                        grid.updateTetromino();
                        grid.dropTetromino();
                        displayGrid();
                    }
                    break;
                case P:
                    if (isPaused) {
                        timeline.play();
                        this.isPaused = false;
                    } else {
                        timeline.stop();
                        this.isPaused = true;
                    }
            }
        });
    }

    public void startGame(String[] args) {
        launch(args);
    }

    private void processDrop() {
        grid.deleteTetromino();
        grid.dropTetromino();
        grid.updateTetromino();
        displayGrid();
    }

    private void displayNextTetronominoes() {
        nextTetrominoes.getChildren().clear();

    }

    private void displayGrid() {

        gridPane.getChildren().clear();
        grid.displayGrid(gridPane);

    }

}
