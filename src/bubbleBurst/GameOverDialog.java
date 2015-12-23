package bubbleBurst;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameOverDialog  extends StartGameDialog
{
    public GameOverDialog(BubbleFrameController bubbleFrameController)
    {
        super(bubbleFrameController);
    }

    protected void addMoreItems()
    {
        addScore();
        addBestScore();
        updateStartLabel();
    }
    private void addScore()
    {
        Label scoreLabel = new Label("Your Score: " + bubbleFrameController.getBubbleFrame().getScore());
        scoreLabel.setStyle("-fx-font-family: arial;-fx-font-size: 20pt;-fx-text-fill: red;");
        scoreLabel.setLayoutX(dialogWidth / 2 - 60);
        scoreLabel.setLayoutY(dialogHeight / 2 + 50);
        group.getChildren().add(scoreLabel);
    }

    private void addBestScore()
    {
        Label bestScoreLabel = new Label("Best Score: " + bubbleFrameController.getBubbleFrame().getBestScore());
        bestScoreLabel.setStyle("-fx-font-family: arial;-fx-font-size: 20pt;-fx-text-fill: red;");
        bestScoreLabel.setLayoutX(dialogWidth / 2 - 60);
        bestScoreLabel.setLayoutY(dialogHeight / 2 + 80);
        group.getChildren().add(bestScoreLabel);
    }

    protected void updateStartLabel()
    {
        for(Node node : group.getChildren())
        {
            if(node instanceof Label && ((Label)node).getText().equals("Start"))
            {
                Label startLabel = (Label)node;
                startLabel.setText("Play Again");
                startLabel.setLayoutX(startLabel.getLayoutX() - 12);
            }
        }
    }
}
