import Backend.ButtonResponders;
import Backend.GameControler;

import static org.junit.jupiter.api.Assertions.*;

class ButtonRespondersTest {

    @Test
    void login() {
        ButtonResponders buttonResponders = new ButtonResponders();
        buttonResponders.SignUpButton("username", "password");
        assertTrue(buttonResponders.LoginButton("username", "password"), true);
    }

}