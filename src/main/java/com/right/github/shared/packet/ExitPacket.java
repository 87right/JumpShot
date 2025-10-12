package main.java.com.right.github.shared.packet;

public class ExitPacket implements Packet{
    private boolean flag;
    public ExitPacket(){
        this.flag = false;
    }
    public boolean getFlag(){return this.flag;}

    public void reverseFlag(){this.flag = ! this.flag;}
    public void turnOnFlag(){flag = true;}
    public void turnOffFlag(){flag = false;}
}
