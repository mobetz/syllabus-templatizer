import java.time.Instant;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.Ball;

/*
 * Objectives for Today
 * 
 *  By the end of today, we will:
      * Identify how to apply the MVC pattern when building applications that use animation.
      * See how we divide the logic of a model from the elements actually rendered to the page.
      * Describe how to use timer callbacks to periodically update our scene without intervention from the user.
 */


public class GameController {
    
    @FXML
    private Pane game_pane;

    private Ball game_ball;
    private Circle sprite;

    private long last_rendered_at;


    /*
     * With animation, we're doing more than just listening to events that happen in reaction to the user.
     * Our ball should be able to move even if the user has done nothing at all.
     * 
     * In order to represent this in our controller, we can create an AnimationTimer class. This class
     * has a method that fires repeatedly every few milliseconds.
     */
    @FXML
    public void initialize() {


        this.last_rendered_at = Instant.now().getEpochSecond();

        AnimationTimer game_loop = new AnimationTimer() {
            @Override
            public void handle(long current_timestamp) {
                /* This handle method handles all the work for drawing one "frame" of our game */
                long time_passed = current_timestamp - last_rendered_at;
                updateModels(time_passed);
                renderNextFrame();

                last_rendered_at = current_timestamp;

            }
        };

        game_loop.start();
    }


    public void onNewGame(ActionEvent e ) {


        /*
         * Notice: in our Controller we keep the Model representation of the Ball and the View
         * representation of the Ball (a Circle object) completely separate.
         */
        this.game_ball = new Ball(50, 50);

        this.sprite = new Circle(
            this.game_ball.getCoordinates().getX(), 
            this.game_ball.getCoordinates().getY(),
            Ball.SIZE,
            Ball.COLOR
        );

        this.game_pane.getChildren().add(this.sprite);

    }


    public void updateModels(long time_passed) {
        if ( this.game_ball != null ) {
            this.game_ball.checkWallCollision(game_pane.getWidth(), game_pane.getHeight());
            this.game_ball.move(time_passed);
        }
    }

    public void renderNextFrame() {
        if ( this.sprite != null ) { 
            this.sprite.setCenterX(this.game_ball.getCoordinates().getX());
            this.sprite.setCenterY(this.game_ball.getCoordinates().getY());
        }
    }
}
