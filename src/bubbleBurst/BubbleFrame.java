package bubbleBurst;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/23/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleFrame
{
    private Group group = null;
    private List<Circle> bubbles = new ArrayList<>();
    private Random randomizer = new Random();
    private Stage stage = null;
    private Label scoreField = null;
    private volatile int score = 0, bestScore = 0;

    private int sceneWidth = 1280, sceneHeight = 960;

    public BubbleFrame()
    {

    }

    public void start(Stage primaryStage)
    {
        sceneWidth = (int)Screen.getPrimary().getVisualBounds().getWidth();
        sceneHeight = (int)Screen.getPrimary().getVisualBounds().getHeight();
        stage = primaryStage;
        stage.setTitle("Bubble Burst");
        group = new Group();
        addScoreField();
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.BLUE);
        stage.setScene(scene);
        stage.setMinWidth(sceneWidth);
        stage.setMinHeight(sceneHeight);
        stage.setMaxWidth(sceneWidth);
        stage.setMaxHeight(sceneHeight);
        scene.setCursor(Cursor.HAND);
        stage.show();
    }

    private void addScoreField()
    {
        scoreField = new Label();
        scoreField.setText("Score: " + score);
        scoreField.setStyle("-fx-font-family: arial;-fx-font-size: 16pt;-fx-text-fill: red;-fx-background-color: green");
        scoreField.setLayoutX(sceneWidth - 100);
        scoreField.setLayoutY(0);
        scoreField.setMinWidth(40);
        scoreField.setMinHeight(20);
        group.getChildren().add(scoreField);
    }

    public void addBubble()
    {
        Circle bubble = new Circle();
        bubble.setCenterX(getRandomInt((int) stage.getScene().getWidth()));
        bubble.setCenterY(stage.getScene().getHeight());
        bubble.setRadius(getRandomInt(10));
        updateFill(bubble);
        BubbleBurstHandler bubbleBurstHandler = new BubbleBurstHandler(this, bubble);
        bubble.setOnMouseClicked(bubbleBurstHandler);
        bubble.setOnMouseEntered(bubbleBurstHandler);
        bubbles.add(bubble);
        group.getChildren().add(bubble);
    }

    public void moveBubbles()
    {
        for (Circle bubble : bubbles)
        {
            bubble.setCenterY(bubble.getCenterY() - getRandomInt(2));
            updateFill(bubble);
        }
    }

    public void bloatTheBubbles()
    {
        for (Circle bubble : bubbles)
        {
            if (getRandomInt(10) > 5)
            {
                bubble.setRadius(bubble.getRadius() + 1);
                updateFill(bubble);
            }
        }
    }

    private void updateFill(Circle bubble)
    {
        bubble.setFill(new RadialGradient(30, .1, bubble.getCenterX(), bubble.getCenterY() + bubble.getRadius() / 2,
                bubble.getRadius(), false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.BLUE), new Stop(1, Color.WHITE)));
    }

    public void removeBubbles()
    {
        List<Circle> bubblesToBeRemoved = new ArrayList<>();
        for (Circle bubble : bubbles)
        {
            if (bubble.getCenterY() <= 10)
            {
                group.getChildren().remove(bubble);
            }

        }
        bubbles.removeAll(bubblesToBeRemoved);
    }

    public boolean anyBubbleReachedOtherEnd()
    {
        for (Circle bubble : bubbles)
        {
            if (bubble.getCenterY() <= 10)
            {
                return true;
            }
        }
        return false;
    }
    public void burstTheBubble(Circle bubble)
    {
        group.getChildren().remove(bubble);
        bubbles.remove(bubble);
        score += bubble.getRadius();
        updateScoreField();
    }

    public int getRandomInt(int range)
    {
        int randomInt = randomizer.nextInt() % range;
        if (randomInt < 0)
        {
            randomInt = -randomInt;
        }
        return randomInt;
    }

    public Stage getStage()
    {
        return stage;
    }

    public int getBestScore()
    {
        updateBestScore();
        return bestScore;
    }

    private void updateBestScore()
    {
        if(bestScore < score)
        {
            bestScore = score;
        }
    }
    public void clearBubbles()
    {
        group.getChildren().removeAll(bubbles);
        bubbles.clear();
        updateBestScore();
        score = 0;
        updateScoreField();
    }

    private void updateScoreField()
    {
        scoreField.setText("Score: " + score);
    }
    public int getScore()
    {
        return score;
    }
}
