package main.java.com.right.github.shared.packet;

import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.SelectableItem;

public interface ClientModeSendDataPacket extends Packet {
    record ChoosingItem(SelectableItem[] selectableItems, IntegerObject response) implements ClientModeSendDataPacket{}

    record Response() implements  ClientModeSendDataPacket{}
}
