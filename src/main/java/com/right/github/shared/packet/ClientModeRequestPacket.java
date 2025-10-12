package main.java.com.right.github.shared.packet;

import main.java.com.right.github.client.ui.EnumUIModes;

public class ClientModeRequestPacket implements Packet{
    private final EnumUIModes uiModes;
    public ClientModeRequestPacket(EnumUIModes uiModes){
        this.uiModes = uiModes;
    }
    public EnumUIModes getUiModes() {
        return uiModes;
    }
}
