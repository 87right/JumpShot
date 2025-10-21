package main.java.com.right.github.client.ui.menu.frame;


import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.dispatch.KeyCommand;
import main.java.com.right.github.client.ui.menu.frame.dispatch.KeyTyping;
import main.java.com.right.github.client.ui.menu.frame.dispatch.MouseClick;
import main.java.com.right.github.client.ui.menu.frame.dispatch.MouseMovement;
import main.java.com.right.github.shared.packet.ExitPacket;

import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    private final ExitPacket exitPacket;

    public KeyCommand keyCommand;
    public KeyTyping keyTyping;
    public MouseClick mouseClick;
    public MouseMovement mouseMovement;

    public MainFrame(ExitPacket pExitPacket, ClientData clientData){
        super();
        this.exitPacket = pExitPacket;

        keyCommand = new KeyCommand(clientData);
        keyTyping = new KeyTyping(clientData);
        mouseClick = new MouseClick(clientData);
        mouseMovement = new MouseMovement(clientData, getInsets());

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
        keyTyping.KeyTyped(e);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyCommand.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keyCommand.keyReleased(e);
    }
    // endregion
    // region MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("クリック座標:"+e.getPoint().getX() + ", " + e.getPoint().getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mouseClick.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClick.mouseReleased(e);
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    // endregion
    // region MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMovement.mouseDragged(e, 29);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovement.mouseMoved(e, 29);// getJMenuBar() == null ? 0: getJMenuBar().getHeight() がだめだったので数値で
    }
    //endregion
}
