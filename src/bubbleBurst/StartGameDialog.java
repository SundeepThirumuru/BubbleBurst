package bubbleBurst;

import com.sun.javafx.effect.EffectUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartGameDialog
{
    protected BubbleFrameController bubbleFrameController = null;
    protected Stage stage = null;
    protected Group group = null;
    protected int dialogWidth = 500, dialogHeight = 300, playCircleRadius = 20, quitCircleRadius = 10;

    public StartGameDialog(BubbleFrameController bubbleFrameController)
    {
        this.bubbleFrameController = bubbleFrameController;
        init();
    }

    private void init()
    {
        stage = new Stage();
        stage.setTitle("Bubble Burst");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        group = new Group();
        addGameNameLabel();
        addPlayIcon();
        addStartLabel();
        addQuitIcon();
        addQuitLabel();
        addMoreItems();
        stage.setScene(new Scene(group, dialogWidth, dialogHeight, Color.ORANGE));
        stage.show();
    }

    protected void addMoreItems()
    {

    }

    private void addPlayIcon()
    {
        Circle circle = new Circle(dialogWidth / 2, dialogHeight / 2, playCircleRadius);
        circle.setFill(Color.RED);
        Polygon triangle = new Polygon();
        double offset = playCircleRadius / Math.pow(2, 0.5);
        triangle.getPoints().addAll(new Double[]{circle.getCenterX() - offset, circle.getCenterY() - offset,
                circle.getCenterX() - offset, circle.getCenterY() + offset,
                circle.getCenterX() + circle.getRadius(), circle.getCenterY()});
        triangle.setFill(Color.YELLOW);
        group.getChildren().add(circle);
        group.getChildren().add(triangle);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent o)
            {
                stage.close();
                bubbleFrameController.startBubbleBurst();
            }
        };
        triangle.setOnMouseClicked(eventHandler);
        circle.setOnMouseClicked(eventHandler);
    }

    private void addGameNameLabel()
    {
        Label welcomeLabel = new Label("Bubble Burst");
        welcomeLabel.setStyle("-fx-font-family: arial;-fx-font-size: 20pt;-fx-text-fill: red;");
        welcomeLabel.setLayoutX(dialogWidth / 2 - 60);
        welcomeLabel.setLayoutY(dialogHeight / 2 - 70);
        group.getChildren().add(welcomeLabel);
    }

    protected void addStartLabel()
    {
        Label startLabel = new Label("Start");
        startLabel.setStyle("-fx-font-family: arial;-fx-font-size: 14pt;-fx-text-fill: red;");
        startLabel.setLayoutX(dialogWidth / 2 - 15);
        startLabel.setLayoutY(dialogHeight / 2 + 30);
        group.getChildren().add(startLabel);
    }


    private void addQuitLabel()
    {
        Label quitLabel = new Label("Quit");
        quitLabel.setStyle("-fx-font-family: arial;-fx-font-size: 14pt;-fx-text-fill: red;");
        quitLabel.setLayoutX(dialogWidth - 70);
        quitLabel.setLayoutY(dialogHeight - 25);
        group.getChildren().add(quitLabel);
    }

    public void addQuitIcon()
    {
        Circle circle = new Circle(dialogWidth - 60, dialogHeight - 40, quitCircleRadius);
        circle.setFill(Color.RED);
        double offset = quitCircleRadius / Math.pow(2, 0.5) - 2;
        Line line1 = new Line(circle.getCenterX() - offset, circle.getCenterY() - offset, circle.getCenterX() + offset, circle.getCenterY() + offset);
        line1.setStroke(Color.YELLOW);
        line1.setStrokeWidth(3);
        Line line2 = new Line(circle.getCenterX() + offset, circle.getCenterY() - offset, circle.getCenterX() - offset, circle.getCenterY() + offset);
        line2.setStroke(Color.YELLOW);
        line2.setStrokeWidth(3);
        group.getChildren().add(circle);
        group.getChildren().add(line1);
        group.getChildren().add(line2);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent o)
            {
                System.exit(-1);
            }
        };
        circle.setOnMouseClicked(eventHandler);
        line1.setOnMouseClicked(eventHandler);
        line2.setOnMouseClicked(eventHandler);

    }
}