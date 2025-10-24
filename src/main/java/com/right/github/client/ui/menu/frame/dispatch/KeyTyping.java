package main.java.com.right.github.client.ui.menu.frame.dispatch;

import java.awt.event.KeyEvent;

public class KeyTyping {
    public boolean active = false;

    private boolean eventEnter = false;
    private boolean eventBackSpace = false;
    private char inputChar = 0;

    public KeyTyping(){}

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
