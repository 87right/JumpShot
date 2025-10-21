package main.java.com.right.github.client.ui.menu.frame.dispatch;

import main.java.com.right.github.client.data.ClientData;

import java.awt.event.MouseEvent;

public class MouseClick {
    private final ClientData clientData;
    public boolean active = false;

    private boolean lmbPressed = false;
    private boolean mmbPressed = false;
    private boolean rmbPressed = false;

    private boolean lmbReleased = false;
    private boolean mmbReleased = false;
    private boolean rmbReleased = false;

    private boolean lmbState = false;
    private boolean mmbState = false;
    private boolean rmbState = false;

    public MouseClick(ClientData clientData){this.clientData = clientData;}

    public void mousePressed(MouseEvent e){
        if (active){
            switch (e.getButton()){
                case MouseEvent.BUTTON1: {
                    lmbPressed = true;
                    lmbState = true;
                    break;
                }
                case MouseEvent.BUTTON2: {
                    mmbPressed = true;
                    mmbState = true;
                    break;
                }
                case MouseEvent.BUTTON3: {
                    rmbPressed = true;
                    rmbState = true;
                    break;
                }
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        if (active){
            switch (e.getButton()){
                case MouseEvent.BUTTON1: {
                    lmbReleased = true;
                    lmbState = false;
                    break;
                }
                case MouseEvent.BUTTON2: {
                    mmbReleased = true;
                    mmbState = false;
                    break;
                }
                case MouseEvent.BUTTON3: {
                    rmbReleased = true;
                    rmbState = false;
                    break;
                }
            }
        }
    }

    public boolean consumeLMBPressed(){if (lmbPressed){lmbPressed = false; return true;}return false;}
    public boolean consumeMMBPressed(){if (mmbPressed){mmbPressed = false; return true;}return false;}
    public boolean consumeRMBPressed(){if (rmbPressed){rmbPressed = false; return true;}return false;}
    public boolean consumeLMBReleased(){if (lmbReleased){lmbReleased = false; return true;}return false;}
    public boolean consumeMMBReleased(){if (mmbReleased){mmbReleased = false; return true;}return false;}
    public boolean consumeRMBReleased(){if (rmbReleased){rmbReleased = false; return true;}return false;}

    public boolean getLMBState(){return lmbState;}
    public boolean getMMBState(){return mmbState;}
    public boolean getRMBState(){return rmbState;}
}
