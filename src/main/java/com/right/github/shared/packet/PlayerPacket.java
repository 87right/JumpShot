package main.java.com.right.github.shared.packet;


import main.java.com.right.github.core.DisplayObjectData;

public class PlayerPacket extends LevelPacket{
    public static class MoveRequestPacket extends PlayerPacket {

    }
    public static class SendStatePacket extends PlayerPacket {
        private final DisplayObjectData displayObjectData;

        public SendStatePacket(DisplayObjectData pDisplayObjectData){
            displayObjectData = pDisplayObjectData;
        }

        public DisplayObjectData getDisplayObjectData() {
            return displayObjectData;
        }
    }
}
