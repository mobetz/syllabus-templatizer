import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Ball;
import model.Brick;
import model.ICollidable;
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
    private Set<SimpleEntry<Shape, ICollidable>> collidables;
    private List<SimpleEntry<Rectangle, Brick>> bricks;

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
        this.collidables = new HashSet<>();
        this.bricks = new ArrayList<>();

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
        this.collidables.add(new SimpleEntry<>(sprite, game_ball));


        sprite.centerXProperty().bind(game_ball.getX());
        sprite.centerYProperty().bind(game_ball.getY());


        Paddle player = new Paddle(100, this.game_pane.getHeight());

        Rectangle paddle_sprite = new Rectangle(Paddle.SIZE, Paddle.WIDTH, Paddle.COLOR);

        paddle_sprite.xProperty().bind(player.getX());
        paddle_sprite.yProperty().bind(player.getY());

        this.game_pane.getChildren().add(paddle_sprite);
        this.moveables.add(player);
        this.collidables.add(new SimpleEntry<>(paddle_sprite, player));


        for ( int row =0; row < 3; row++) {
            for ( int col=0; col <  ( this.game_pane.getWidth() / (Brick.WIDTH + Brick.SPACE_BETWEEN)); col++) {

                if ( Math.random() > 0.5 ) {
                    Brick created_brick = new Brick(row, col);
                    Rectangle brick_sprite = new Rectangle(Brick.WIDTH, Brick.HEIGHT, Brick.COLOR);
                    brick_sprite.setX(created_brick.getX());
                    brick_sprite.setY(created_brick.getY());

                    SimpleEntry<Rectangle, Brick> brick_pair = new SimpleEntry<>(brick_sprite, created_brick);
                    this.collidables.add(new SimpleEntry<>(brick_sprite, created_brick));
                    this.bricks.add(brick_pair);
                    this.game_pane.getChildren().add(brick_sprite);
                }
            } 
        }


    }

    public void handleCollisions() {


        for ( SimpleEntry<Shape, ICollidable> thing : this.collidables ) {
            for (  SimpleEntry<Shape, ICollidable>  other : this.collidables ) {
                //Do these two things overlap?
                Shape overlapping_shape = Shape.intersect(thing.getKey(), other.getKey());
                if ( thing != other && overlapping_shape.getBoundsInParent().getWidth() > 0 ) {
                    thing.getValue().handleCollision(other.getValue());
                }
            }
        }


        //Clean up any dead bricks
        for ( int i=this.bricks.size()-1; i >=0; i-- ) {
            SimpleEntry<Rectangle, Brick> brick = this.bricks.get(i);

            if ( brick.getValue().isAlive() == false ) {
                this.game_pane.getChildren().remove(brick.getKey());
                this.bricks.remove(brick);
                this.collidables.remove(brick);
            }
        }
    }

    public void updateModels(long time_passed) {

        handleCollisions();
        
        for ( IMoveable thing : this.moveables ) {
            thing.move(time_passed, keys_pressed);
            thing.checkWallCollision(this.game_pane.getWidth(), this.game_pane.getHeight());
        }


    }

}
