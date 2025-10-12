package main.java.com.right.github.shared.packet;


import main.java.com.right.github.core.DisplayObjectData;

public class PlayerPacket extends LevelPacket implements Packet{
    public static class MoveRequestPacket extends PlayerPacket implements Packet {

    }
    public static class SendStatePacket extends PlayerPacket implements Packet{
        private final DisplayObjectData displayObjectData;

        public SendStatePacket(DisplayObjectData pDisplayObjectData){
            displayObjectData = pDisplayObjectData;
        }

        public DisplayObjectData getDisplayObjectData() {
            return displayObjectData;
        }
    }
}
