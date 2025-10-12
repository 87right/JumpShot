package main.java.com.right.github.shared.packet;


import main.java.com.right.github.core.DisplayObjectData;

public class EntityPacket extends LevelPacket implements Packet{
    private final DisplayObjectData displayObjectData;

    public EntityPacket(DisplayObjectData displayObjectData){
        this.displayObjectData = displayObjectData;
    }

    public DisplayObjectData getDisplayObjectData() {
        return displayObjectData;
    }
}
