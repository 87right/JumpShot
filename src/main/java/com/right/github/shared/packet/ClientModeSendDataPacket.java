package main.java.com.right.github.shared.packet;

import main.java.com.right.github.core.SelectableItem;

public class ClientModeSendDataPacket implements Packet{
    public record ChoosingItem(SelectableItem[] selectableItems) implements Packet{}
}
