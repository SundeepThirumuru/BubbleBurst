package bubbleBurst;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleFrameController extends Application
{
    private BubbleFrame bubbleFrame = null;
    private BubbleManager bubbleManager = null;
    private volatile boolean freeze = true;

    public BubbleFrameController()
    {

    }

    @Override
    public void start(Stage stage) throws Exception
    {
        new StartGameDialog(this);
    }

    public void startBubbleBurst()
    {
        if (bubbleFrame == null)
        {
            bubbleFrame = new BubbleFrame();
            Stage stage = new Stage();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>()
            {
                @Override
                public void handle(WindowEvent o)
                {
                    System.exit(-1);
                }
            });
            bubbleFrame.start(stage);
            bubbleFrame.getStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent o)
                {
                    if (o.getCode().equals(KeyCode.ESCAPE))
                    {
                        endGame();
                    }
                }
            });
            bubbleManager = new BubbleManager(this);
            bubbleManager.scheduleBubbleTasks();
        }
        else
        {
            bubbleFrame.clearBubbles();
        }
        setFreeze(false);

    }

    public BubbleFrame getBubbleFrame()
    {
        return bubbleFrame;
    }

    public boolean isFreeze()
    {
        return freeze;
    }

    public void setFreeze(boolean freeze)
    {
        this.freeze = freeze;
    }

    public void endGame()
    {
        setFreeze(true);
        new GameOverDialog(this);

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
