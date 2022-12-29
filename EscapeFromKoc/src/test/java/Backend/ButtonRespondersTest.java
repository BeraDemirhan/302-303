import Backend.ButtonResponders;
import Backend.GameControler;

import static org.junit.jupiter.api.Assertions.*;

class ButtonRespondersTest {

    @Test
    void login() {
        ButtonResponders.login();
        assertEquals(GameControler.getGameStatus(), GameControler.LOGIN);
    }

    @Test
    void startGame() {
        ButtonResponders.startGame();
        assertEquals(GameControler.getGameStatus(), GameControler.RUNNING);
    }

    @Test
    void pauseGame() {
        ButtonResponders.pauseGame();
        assertEquals(GameControler.getGameStatus(), GameControler.PAUSED);
    }

    @Test
    void buildGame() {
        ButtonResponders.buildGame();
        assertEquals(GameControler.getGameStatus(), GameControler.BUILDING);
    }

    @Test
    void exit() {
        ButtonResponders.exit();
        assertEquals(GameControler.getGameStatus(), GameControler.EXIT);
    }
}