import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Ball;
import model.IMoveable;
import model.Input;
import model.Paddle;

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


    private Set<IMoveable> moveables;

    private long last_rendered_at;

    private Set<Input> keys_pressed;


    /*
     * With animation, we're doing more than just listening to events that happen in reaction to the user.
     * Our ball should be able to move even if the user has done nothing at all.
     * 
     * In order to represent this in our controller, we can create an AnimationTimer class. This class
     * has a method that fires repeatedly every few milliseconds.
     */
    @FXML
    public void initialize() {

        this.keys_pressed = new HashSet<>();
        this.moveables = new HashSet<>();


        this.last_rendered_at = Instant.now().getEpochSecond();

        AnimationTimer game_loop = new AnimationTimer() {
            @Override
            public void handle(long current_timestamp) {
                /* This handle method handles all the work for drawing one "frame" of our game */
                long time_passed = current_timestamp - last_rendered_at;
                updateModels(time_passed);

                last_rendered_at = current_timestamp;

            }
        };

        game_loop.start();


        this.game_pane.sceneProperty().addListener((prop, old_scene, attached_scene) -> {

            attached_scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyPressEvent) -> {
                KeyCode key_pressed = keyPressEvent.getCode();
                Input translated_key = Input.mapKeyCodeToInput(key_pressed);
                this.keys_pressed.add(translated_key);
            });

            attached_scene.addEventFilter(KeyEvent.KEY_RELEASED, (keyPressEvent) -> {
                KeyCode key_pressed = keyPressEvent.getCode();
                Input translated_key = Input.mapKeyCodeToInput(key_pressed);
                this.keys_pressed.remove(translated_key);
            });
        });


    }


    public void onNewGame(ActionEvent e ) {


        /*
         * Notice: in our Controller we keep the Model representation of the Ball and the View
         * representation of the Ball (a Circle object) completely separate.
         */
        Ball game_ball = new Ball(50, 50);

        Circle sprite = new Circle(
            game_ball.getX().get(), 
            game_ball.getY().get(),
            Ball.SIZE,
            Ball.COLOR
        );

        this.game_pane.getChildren().add(sprite);
        this.moveables.add(game_ball);


        sprite.centerXProperty().bind(game_ball.getX());
        sprite.centerYProperty().bind(game_ball.getY());


        Paddle player = new Paddle(100, this.game_pane.getHeight());

        Rectangle paddle_sprite = new Rectangle(Paddle.SIZE, Paddle.WIDTH, Paddle.COLOR);

        paddle_sprite.xProperty().bind(player.getX());
        paddle_sprite.yProperty().bind(player.getY());

        this.game_pane.getChildren().add(paddle_sprite);
        this.moveables.add(player);

    }


    public void updateModels(long time_passed) {
        
        for ( IMoveable thing : this.moveables ) {
            thing.move(time_passed, keys_pressed);
            thing.checkWallCollision(this.game_pane.getWidth(), this.game_pane.getHeight());
        }


    }

}
