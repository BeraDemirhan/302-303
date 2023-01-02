package Backend.BoardClassTesting;
import java.lang.Math;
import Backend.GameControler;
import Backend.GameObjects.Aliens.BlindAlienImpl;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import UI.Board;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class BottleThrownAnimationChecker {
    @Test
    void bottleThrownAnimationChecker(){

        Board board = new Board();

        int[] player = new int[2];
        player[0] = 100;
        player[1] = 200;

        board.setBottleLabelCoords(100,200);


        System.out.println(board.getBottleLabelCoords()[0]);
        System.out.println(board.getBottleLabelCoords()[1]);


        int[] hedef = new int[2];
        hedef[0] = 250;
        hedef[1] = 200;
        board.bottleThrowAnimation(player,hedef);

        assertTrue(board.getBottleThrown() == false);

        System.out.println(board.getBottleLabelCoords()[0]);
        System.out.println(board.getBottleLabelCoords()[1]);


        assertTrue(Math.abs(board.getBottleLabelCoords()[0] - hedef[0] ) < 50) ;
        assertTrue(Math.abs(board.getBottleLabelCoords()[1] - hedef[1]) < 50 ) ;
    }

}
