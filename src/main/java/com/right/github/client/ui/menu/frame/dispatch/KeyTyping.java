package main.java.com.right.github.client.ui.menu.frame.dispatch;

import main.java.com.right.github.client.data.ClientData;

import java.awt.event.KeyEvent;

public class KeyTyping {
    private final ClientData clientData;
    public boolean active = false;

    // Componentを実装するまでとりあえず置いておく
//    private final Set<Character> ALLOWED_CHARS = Set.of(
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_'
//    );

    private boolean eventEnter = false;
    private boolean eventBackSpace = false;
    private char inputChar = 0;

    public KeyTyping(ClientData clientData){this.clientData = clientData;}

    public void KeyTyped(KeyEvent e){
        if (active){
            char key = e.getKeyChar();
            if (key == '\n') {
                eventEnter = true;
            } else if (key == '\b') {
                eventBackSpace = true;
            }else {
                inputChar = key;
            }
        }
    }

    public boolean getEventEnter(){if (eventEnter){eventEnter = false; return true;}return false;}
    public boolean getEventBackSpace(){if (eventBackSpace){eventBackSpace = false; return true;}return false;}
    public char getInputChar(){char tempChar = inputChar; inputChar = 0; return tempChar;}
}
