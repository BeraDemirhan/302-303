package Backend;

import Backend.ButtonResponders;
import Backend.GameControler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



class ButtonRespondersTest {

    @org.junit.jupiter.api.Test
    void login() throws IOException {
        ButtonResponders buttonResponders = new ButtonResponders();
        buttonResponders.SignUpButton("username", "password");
        assertTrue(buttonResponders.LoginButton("username", "password"));
    }

}