package main.java.com.right.github.client.ui.frame;


import main.java.com.right.github.client.core.ClientPartCore;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.shared.packet.ExitPacket;

import javax.swing.*;
import java.awt.event.*;
import java.util.Queue;

public class MainFrame extends JFrame implements KeyListener, MouseListener {
    private final ExitPacket exitPacket;
    private final Queue<ClientPartCore.Packet> addressToNet;

    private int flagAction = 0;
    private final int flagRight = 1;
    private final int flagLeft = 1 << 1;
    private final int flagJump = 1 << 2;
    private final int flagShot = 1 << 3;
    private final int flagChanged = 1 << 31;

    public final int keyRight = KeyEvent.VK_D;
    public final int keyLeft = KeyEvent.VK_A;
    public final int keyJump = KeyEvent.VK_SPACE;

    public int mouseX = 0;
    public int mouseY = 0;

    public boolean isInputConsumedByUI = false;
    public String textingText = "";
    public MainFrame(ExitPacket pExitPacket, Queue<ClientPartCore.Packet> pAddressToNet){
        super();
        this.exitPacket = pExitPacket;
        this.addressToNet = pAddressToNet;

        addKeyListener(this);
        addMouseListener(this);
    }


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
                    break;
                }
                textingText = textingText.substring(0, textingText.length() - 1);
                return;
            }
            case '\n', '\r':{
                isInputConsumedByUI = false;
                return;
            }
        }
        textingText += e.getKeyChar();
        System.out.println(textingText);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (isInputConsumedByUI){
            return;
        }
        switch (e.getKeyCode()){
            case keyRight: flagAction = flagAction | flagRight; break;
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
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isInputConsumedByUI){
            return;
        }
        // マウスのボタンがクリックされた
        switch ( e.getButton() ) {
            case MouseEvent.BUTTON1:
//                System.out.println("左クリック");
                break;
            case MouseEvent.BUTTON2:
//                System.out.println("ホイールクリック");
                break;
            case MouseEvent.BUTTON3:
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

    public int getFlagAction() {
        return flagAction;
    }
    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }
}
