package main.java.com.right.github.world.entity;


import main.java.com.right.github.core.*;
import main.java.com.right.github.shared.packet.BasePacket;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.PlayerPacket;
import main.java.com.right.github.world.level.block.Block;
import main.java.com.right.github.world.level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Queue;

public class Player extends Entity {
    private final BufferedImage TEXTURE_R1 = TextureManager.getTexture("entity/player/player_1.png");
    private final BufferedImage TEXTURE_R2 = TextureManager.getTexture("entity/player/player_2.png");
    private final BufferedImage TEXTURE_L1 = TextureManager.getTexture("entity/player/player_3.png");
    private final BufferedImage TEXTURE_L2 = TextureManager.getTexture("entity/player/player_4.png");

    private int keyInput = 0;
    private int mouseX = 0;
    private int mouseY = 0;

    private BlockPos blockPos;

    public Player(Pos pSpawnPos, Queue<BasePacket> toSendPackets){
        this.setState(pSpawnPos, new Vec2D(0f, 0.1f), new Vec2D(0f, 0f), Configs.PLAYER_WIDTH, Configs.PLAYER_HEIGHT);
        toSendPackets.add(new PlayerPacket.SendStatePacket(getDisplayObjectData()));
        setAABB(new AABB(Configs.PLAYER_WIDTH, Configs.PLAYER_HEIGHT));

        getDisplayObjectData().setImage(TEXTURE_R1);
    }

    @Override
    public void update(Queue<BasePacket> toSendPackets, Level pLevel) {

        Pos proPos = pos.move(velocity);
        velocity = velocity.add(acceleration);

        if (velocity.getX() > Configs.BLOCK_SIZE - 1){velocity.setX(Configs.BLOCK_SIZE - 1);}
        if (velocity.getY() > Configs.BLOCK_SIZE - 1){velocity.setY(Configs.BLOCK_SIZE - 1);}

        setAcceleration(new Vec2D(0.0f, 0.0f));
        if (!onGround(pLevel)){
            acceleration.setY(Configs.GRAVITY);
        }

        Vec2D mtv = new Vec2D(0.0f, 0.0f);
        blockPos = new BlockPos(pos);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                // 探索中のブロック情報を取得
                BlockPos currentBlockPos = blockPos.offset(x, y);
                Block currentBlock = pLevel.getBlock(currentBlockPos);

                // そもそも当たり判定を持っているかcheck
                if (currentBlock.hasCollision()){
                    // mtvに代入してもらいつつ、衝突したかどうかを優先的に取得
                    boolean collided = AABB.overlapped(proPos, getAABB(), currentBlockPos.getTopLeft(), currentBlock.getAABB(), mtv);
                    if (collided){
                        // 加工済みのmtvを用いてめり込み回避
                        proPos = proPos.move(mtv);

                        // 戻される方向と逆方向の速度を持っていた場合、リセット
                        if ((mtv.getX() > 0 && velocity.getX() < 0) || (mtv.getX() < 0 && velocity.getX() > 0)){
                            velocity.setX(0.0f);
                        }
                        if ((mtv.getY() > 0 && velocity.getY() < 0) || (mtv.getY() < 0 && velocity.getY() > 0)){
                            velocity.setY(0.0f);
                        }
                    }
                }
            }


            boolean onGround = onGround(pLevel);
            if ((keyInput & 4)  > 0 && onGround) {
                velocity.setY(-6.0f);
            }

            // TODO: 描画と物理の分離
            if ((keyInput & 1)  > 0){
                if ((keyInput & 2)  > 0){
                    if (onGround){
                        velocity.setX(0.0f);
                    }

                    if (getDisplayObjectData().getImage() == TEXTURE_L2 || getDisplayObjectData().getImage() == TEXTURE_L1){
                        getDisplayObjectData().setImage(TEXTURE_L1);
                    }else {
                        getDisplayObjectData().setImage(TEXTURE_R1);
                    }
                }else {
                    if (onGround){
                        velocity.setX(5.0f);
                    }else {
                        acceleration.addX(0.06f);
                    }
                    if (getDisplayObjectData().getImage() == TEXTURE_R1){
                        getDisplayObjectData().setImage(TEXTURE_R2);
                    }else {
                        getDisplayObjectData().setImage(TEXTURE_R1);
                    }
                }
            }else{
                if ((keyInput & 2)  > 0){
                    if (onGround){
                        velocity.setX(-5.0f);
                    }else {
                        acceleration.addX(-0.06f);
                    }
                    if (getDisplayObjectData().getImage() == TEXTURE_L1){
                        getDisplayObjectData().setImage(TEXTURE_L2);
                    }else {
                        getDisplayObjectData().setImage(TEXTURE_L1);
                    }
                }else {
                    if (onGround){
                        velocity.setX(0.0f);
                    }
                    if (getDisplayObjectData().getImage() == TEXTURE_L2 || getDisplayObjectData().getImage() == TEXTURE_L1){
                        getDisplayObjectData().setImage(TEXTURE_L1);
                    }else {
                        getDisplayObjectData().setImage(TEXTURE_R1);
                    }
                }
            }
        }
        pos = proPos;

        // さすがに速さ無制限はまずい
        if (velocity.getX() < -10.0f){
            velocity.setX(-10.0f);
        }else if (velocity.getX() > 10.0f){
            velocity.setX(10.0f);
        }

        if (velocity.getY() < -10.0f){
            velocity.setY(-10.0f);
        }else if (velocity.getY() > 10.0f){
            velocity.setY(10.0f);
        }

        getDisplayObjectData().setX(pos.getX());
        getDisplayObjectData().setY(pos.getY());
    }

    public void keyInputs(KeyInputsPacket keyInputsPacket, Level pLevel) {
        keyInput = keyInputsPacket.getContent();
        mouseX = keyInputsPacket.getMouseX();
        mouseY = keyInputsPacket.getMouseY();
    }

}
