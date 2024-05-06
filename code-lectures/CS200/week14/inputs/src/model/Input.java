package model;

import java.util.Map;

import javafx.scene.input.KeyCode;

/*
 * 
 * An "enum" is like a collection of values that limit what a user can pass in to
 * one of a few listed options.
 */
public enum Input {
    
    LEFT,
    RIGHT,
    UNKNOWN;


    /*
     * While we don't typically create full objects on an enum, enums can still hold
     * static functions that allow you to do things like convert into/out of an enum
     */

     private static final Map<KeyCode, Input> KEY_MAPPINGS = Map.of(
        KeyCode.LEFT, LEFT,
        KeyCode.RIGHT, RIGHT
     );

     public static Input mapKeyCodeToInput(KeyCode key) {
       return KEY_MAPPINGS.getOrDefault(key, UNKNOWN); 

     }


}
