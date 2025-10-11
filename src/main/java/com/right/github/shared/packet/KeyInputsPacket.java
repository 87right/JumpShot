package main.java.com.right.github.shared.packet;

public class KeyInputsPacket extends BasePacket{
    public final int mouseX;
    public final int mouseY;
    private int content = 0;
    public KeyInputsPacket(int flag, int mouseX, int mouseY){
        content = flag;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public int getContent() {
        return content;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
