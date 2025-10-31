package main.java.com.right.github.client.ui.menu.frame.dispatch;

import java.awt.event.KeyEvent;

public class KeyCommand {
    public boolean active = false;

    private int state;
    private static final int STATE_CHANGE_FLAG = 31;
    private final KeyBind[] keyBinds = {
            new KeyBind("left", KeyEvent.VK_A, 1),
            new KeyBind("right", KeyEvent.VK_D, 0),
            new KeyBind("jump", KeyEvent.VK_SPACE, 2),
            new KeyBind("esc", KeyEvent.VK_ESCAPE, 3),
    };

    public KeyCommand(){}

    public void keyPressed(KeyEvent e){
        if (active){
            for (KeyBind keyBind : keyBinds){
                if (keyBind.check(e)){
                    state = keyBind.pressed(state);
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){
        if (active){
            for (KeyBind keyBind : keyBinds){
                if (keyBind.check(e)){
                    state = keyBind.released(state);
                }
            }
        }
    }

    public int getState() {return state;}
    public void checkedState(){state = state & ~ (1 << STATE_CHANGE_FLAG);}
    public void resetState(){state = 0;}

    private static class KeyBind{
        public String name;
        public int key;
        public int bitID;
        public KeyBind(String name, int key, int bitID){
            if (bitID < 0 || bitID > 30){
                throw new RuntimeException("bitID "+bitID+" is out of space!");
            }
            this.name = name; this.key = key; this.bitID = bitID;
        }
        public int stateChanged(int state){return state | 1 << STATE_CHANGE_FLAG;}
        public int pressed(int state){return stateChanged(state) | 1 << bitID;}
        public int released(int state){return stateChanged(state) & ~ (1 << bitID);}
        public boolean check(KeyEvent e){return e.getKeyCode() == key;}

    }
}
