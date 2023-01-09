package Backend.BoardClassTesting;

import Backend.GameObjects.Aliens.BlindAlienImpl;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import UI.Board;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectCreationControl {
    @Test
    void HealthPowerupCreation(){
        JLabel health = null;
        Board board = new Board();
        board.createHealth();
        assertTrue(board.getHealth());
    }
    @Test
    void AlienCreator(){
        BlindAlienImpl blindAlien = new BlindAlienImpl(100, 100);
        Board board = new Board();
        board.createAlien();
        assertInstanceOf(BlindAlienImpl.class, blindAlien);
    }
}
