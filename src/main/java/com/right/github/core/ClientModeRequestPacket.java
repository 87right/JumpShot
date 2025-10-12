package main.java.com.right.github.core;

import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.shared.packet.BasePacket;

public class ClientModeRequestPacket extends BasePacket {
    private final EnumUIModes uiModes;
    public ClientModeRequestPacket(EnumUIModes uiModes){
        this.uiModes = uiModes;
    }
    public EnumUIModes getUiModes() {
        return uiModes;
    }
}
