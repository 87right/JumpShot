package main.java.com.right.github.shared.packet;

import main.java.com.right.github.core.FloatObject;
import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.SelectableItem;
import main.java.com.right.github.core.StringObject;

public interface ClientModeSendDataPacket extends Packet {
    record ChoosingItem(SelectableItem[] selectableItems, IntegerObject response) implements ClientModeSendDataPacket{}
    record SelectingBlockPos(IntegerObject response) implements ClientModeSendDataPacket{}
    record InputtingText(StringObject response) implements ClientModeSendDataPacket{}
    record SelectingPos(FloatObject responseX, FloatObject responseY) implements  ClientModeSendDataPacket{}

    record Response() implements  ClientModeSendDataPacket{}
}
