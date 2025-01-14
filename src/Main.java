import classes.game.Grid;
import classes.game.tetrominoes.Position;
import classes.game.tetrominoes.Shape;
import classes.game.tetrominoes.Tetromino;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Grid grid;
    private Tetromino currentTetromino;
    private Text gridDisplay;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the grid and Tetromino
        grid = new Grid();
        Shape lShape = new Shape(new int[][]{{2, 0}, {2, 0}, {2, 2}});
        currentTetromino = new Tetromino(new Position(new int[]{6, 0}), lShape);
        grid.insertTetromino(currentTetromino);

        // Pane for displaying the grid
        Pane root = new Pane();
        gridDisplay = new Text();
        gridDisplay.setText(gridToString());
        gridDisplay.setStyle("-fx-font-family: monospace; -fx-font-size: 14;");
        root.getChildren().add(gridDisplay);

        // Set up the scene
        Scene scene = new Scene(root, 400, 800);
        primaryStage.setTitle("Tetris Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Timer for automatic Tetromino drop
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
            grid.updateTetromino();
            grid.printGrid();
            grid.dropTetromino();
            updateGridDisplay();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Key handling for Tetromino control
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
                    // Add a method to rotate the Tetromino
                    // grid.rotateTetromino();
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
        // Convert the grid to a string representation
        return grid.getGridRepresentation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




