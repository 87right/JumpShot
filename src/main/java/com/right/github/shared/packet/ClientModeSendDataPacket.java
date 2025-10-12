package main.java.com.right.github.shared.packet;

import main.java.com.right.github.core.SelectableItem;

public interface ClientModeSendDataPacket extends Packet {
    record ChoosingItem(SelectableItem[] selectableItems) implements ClientModeSendDataPacket{}

    record ChoosingItemResponse(int id) implements  ClientModeSendDataPacket{}
}
