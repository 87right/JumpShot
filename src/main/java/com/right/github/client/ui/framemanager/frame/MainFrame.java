package main.java.com.right.github.client.ui.framemanager.frame;


import main.java.com.right.github.shared.packet.ExitPacket;

import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    private final ExitPacket exitPacket;

    private int flagAction = 0;
    private final int flagRight = 1;
    private final int flagLeft = 1 << 1;
    private final int flagJump = 1 << 2;
    private final int flagShot = 1 << 3;

    private int uiInput = 0;
    private final int lmb = 1;
    private final int mmb = 1 << 1;
    private final int rmb = 1 << 2;

    private final int flagChanged = 1 << 31;

    public final int keyRight = KeyEvent.VK_D;
    public final int keyLeft = KeyEvent.VK_A;
    public final int keyJump = KeyEvent.VK_SPACE;

    private int mouseX = 0;
    private int mouseY = 0;

    private boolean isInputConsumedByUI = false;
    private String textingText = "";
    private boolean enter = false;
    public MainFrame(ExitPacket pExitPacket){
        super();
        this.exitPacket = pExitPacket;

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // region KeyListener
    @Override
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {


            switch (getDefaultCloseOperation()) {
                case HIDE_ON_CLOSE:
                    exitPacket.turnOnFlag();
                    setVisible(false);
                    break;
                case DISPOSE_ON_CLOSE:
                    dispose();
                    break;
                case EXIT_ON_CLOSE:
                    // This needs to match the checkExit call in
                    // setDefaultCloseOperation
                    System.exit(0);
                    break;
                case DO_NOTHING_ON_CLOSE:
                default:
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (!isInputConsumedByUI){
            return;
        }
        switch(e.getKeyChar()){
            case '\b':{
                if (textingText.isEmpty()){
                    return;
                }
                textingText = textingText.substring(0, textingText.length() - 1);
                return;
            }
            case '\n', '\r':{
                enter = true;
                return;
            }
        }
        textingText += e.getKeyChar();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        switch (e.getKeyCode()){
            case keyRight: flagAction = flagAction | flagRight;  break;
            case keyLeft: flagAction = flagAction | flagLeft; break;
            case keyJump: flagAction = flagAction | flagJump; break;
        }
        flagAction = flagAction | flagChanged;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        switch (e.getKeyCode()){
            case keyRight: flagAction = flagAction & ~flagRight; break;
            case keyLeft: flagAction = flagAction & ~flagLeft; break;
            case keyJump: flagAction = flagAction & ~flagJump; break;
        }
        flagAction = flagAction | flagChanged;
    }
    // endregion
    // region MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isInputConsumedByUI){
            return;
        }
        uiInput = flagChanged;
        // マウスのボタンがクリックされた
        switch ( e.getButton() ) {
            case MouseEvent.BUTTON1:
                uiInput = uiInput | lmb;
//                System.out.println("左クリック");
                break;
            case MouseEvent.BUTTON2:
                uiInput = uiInput | mmb;
//                System.out.println("ホイールクリック");
                break;
            case MouseEvent.BUTTON3:
                uiInput = uiInput | rmb;
//                System.out.println("右クリック");
                break;
        }
        mouseX = e.getX();
        mouseY = e.getY();
//        System.out.println("クリック座標:"+e.getPoint().getX() + ", " + e.getPoint().getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        // マウスのボタンが押し下げられた
        switch ( e.getButton() ) {
            case MouseEvent.BUTTON1:
//                System.out.println("左押し下げ");
                flagAction = flagAction | flagShot;
                break;
            case MouseEvent.BUTTON2:
//                System.out.println("ホイール押し下げ");
                break;
            case MouseEvent.BUTTON3:
//                System.out.println("右押し下げ");
                break;
        }
        mouseX = e.getX();
        mouseY = e.getY();
        flagAction = flagAction | flagChanged;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        // マウスのボタンが離された
        switch ( e.getButton() ) {
            case MouseEvent.BUTTON1:
//                System.out.println("左離し");
                flagAction = flagAction & ~flagShot;
                break;
            case MouseEvent.BUTTON2:
//                System.out.println("ホイール離し");
                break;
            case MouseEvent.BUTTON3:
//                System.out.println("右離し");
                break;
        }
        mouseX = e.getX();
        mouseY = e.getY();
        flagAction = flagAction | flagChanged;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        // 画面内にマウスカーソルが侵入した
//        System.out.println("画面内に入りました。");
        mouseX = e.getX();
        mouseY = e.getY();
        flagAction = flagAction | flagChanged;
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        // マウスカーソルが画面外へ出た
//        System.out.println("画面外にでました。");
        mouseX = e.getX();
        mouseY = e.getY();
        flagAction = flagAction | flagChanged;
    }
    // endregion
    // region MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isInputConsumedByUI){
            return;
        }
        mouseX = e.getX();
        mouseY = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!isInputConsumedByUI){
            return;
        }
        mouseX = e.getX();
        mouseY = e.getY();
    }
    //endregion


    public void setInputConsumedByUI(boolean inputConsumedByUI) {
        isInputConsumedByUI = inputConsumedByUI;
    }
    public boolean isInputConsumedByUI() {
        return isInputConsumedByUI;
    }
    public int getFlagAction() {
        return flagAction;
    }
    public int getMouseX() {
        return mouseX - getInsets().left;
    }
    public int getMouseY() {
        return mouseY - getInsets().top;
    }
    public int getUiInput(){return uiInput;}
    public String getTextingText() {
        return textingText;
    }
    public void setTextingText(String textingText) {
        this.textingText = textingText;
    }
    public void checked(){
        flagAction = flagAction & ~flagChanged;
    }
    public void checkedUIInput() {
        uiInput = uiInput & ~flagChanged;
    }

    public boolean enter() {
        if (enter){
            enter = false;
            return true;
        }
        return false;
    }
}
