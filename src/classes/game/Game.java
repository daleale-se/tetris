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
    private static final int CELL_SIZE = 30;
    private boolean isDropping = false;

    @Override
    public void start(Stage primaryStage) {
        grid = new Grid();

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderedPane = new BorderPane(gridPane);
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
                    if (!isDropping) {
                        isDropping = true;
                        processDrop();
                        isDropping = false;
                    }
                    break;
                case LEFT:
                    grid.deleteTetrominoTwo();
                    grid.moveTetronominoToLeft();
                    grid.updateTetromino();
                    displayGrid();
                    break;
                case RIGHT:
                    grid.deleteTetrominoTwo();
                    grid.moveTetronominoToRight();
                    grid.updateTetromino();
                    displayGrid();
                    break;
                case UP:
                    grid.deleteTetromino();
                    grid.rotateTetronomino();
                    grid.updateTetromino();
                    displayGrid();
                    break;
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

    private void displayGrid() {

        gridPane.getChildren().clear();
        ArrayList<ArrayList<Cell>> gridState = grid.getGridArray();

        for (int i = 0; i < gridState.size(); i++) {
            for (int j = 0; j < gridState.getFirst().size(); j++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                switch (gridState.get(i).get(j).getContent()) {
                    case 0:
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.LIGHTGRAY);
                        break;
                    case 1:
                        cell.setFill(Color.BLUE);
                        cell.setStroke(Color.BLUE);
                        break;
                    case 2:
                        cell.setFill(Color.RED);
                        cell.setStroke(Color.RED);
                        break;
                    case 3:
                        cell.setFill(Color.GREEN);
                        cell.setStroke(Color.GREEN);
                        break;
                    case 4:
                        cell.setFill(Color.YELLOW);
                        cell.setStroke(Color.YELLOW);
                        break;
                    case 5:
                        cell.setFill(Color.LIGHTBLUE);
                        cell.setStroke(Color.LIGHTBLUE);
                        break;
                    case 6:
                        cell.setFill(Color.PINK);
                        cell.setStroke(Color.PINK);
                        break;
                    case 7:
                        cell.setFill(Color.ORANGE);
                        cell.setStroke(Color.ORANGE);
                        break;
                }

                gridPane.add(cell, j, i);
            }
        }
    }

}
