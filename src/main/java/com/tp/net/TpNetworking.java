package com.tp.net;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;

public class TpNetworking {
    public static final Identifier TELEPORT_ID = new Identifier("tp", "teleport_request");

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(TELEPORT_ID, (server, player, handler, buf, responseSender) -> {
            String targetName = buf.readString(32767);
            server.execute(() -> {
                PlayerManager pm = server.getPlayerManager();
                if (!pm.isOperator(player.getGameProfile())) {
                    player.sendMessage(Text.literal("You must be OP to use teleport."), false);
                    return;
                }
                ServerPlayerEntity target = pm.getPlayer(targetName);
                if (target != null) {
                    ServerWorld world = target.getWorld();
                    player.teleport(world, target.getX(), target.getY(), target.getZ(), player.getYaw(), player.getPitch());
                    player.sendMessage(Text.literal("Teleported to " + targetName), false);
                } else {
                    player.sendMessage(Text.literal("Player not found: " + targetName), false);
                }
            });
        });
    }
}
