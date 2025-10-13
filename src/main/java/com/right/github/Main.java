package main.java.com.right.github;

import main.java.com.right.github.client.Client;
import main.java.com.right.github.server.EnumServerType;
import main.java.com.right.github.server.Server;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.Packet;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main{
    private static final int TPS = 30;
    public static final String GAME_ID = "jumpshot";

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int MSPT = 1000 / TPS;

        Queue<Packet> packetsClientToServer = new ArrayDeque<>();
        Queue<Packet> packetsServerToClient = new ArrayDeque<>();

        Client client = new Client(packetsClientToServer, packetsServerToClient);
        Server server = new Server(packetsServerToClient, packetsClientToServer);

        Logs.Info("Created Server and Client.");

        client.start();
        server.start(packetsServerToClient, EnumServerType.GAME);

        server.update(packetsClientToServer, packetsServerToClient);
        client.update();

        System.out.println("\n");
        Logs.Info("Game has took " + (System.currentTimeMillis() - startTime) + " ms to start.\n");
        while (client.isRunning() && server.isRunning()){
            startTime = System.currentTimeMillis();

            server.update(packetsClientToServer, packetsServerToClient);
            client.update();

            try{
                if (MSPT + startTime - System.currentTimeMillis() > 0){
                    Thread.sleep(MSPT + startTime - System.currentTimeMillis());
                }else{
                    Logs.Warn("The Game is too slow."  + (MSPT + startTime - System.currentTimeMillis()));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        Logs.Info("Saving Server and Client.");

        client.cleanUP();
        server.cleanUP();

        Logs.Info("Saved Server and Client.");

        System.exit(0);

    }
}
