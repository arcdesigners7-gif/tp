package com.tp.client;

import com.tp.net.TpNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.PacketByteBuf;
import io.netty.buffer.Unpooled;
import java.util.List;

public class TpHudOverlay {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void render(DrawContext context) {
        if (client.player == null || client.getNetworkHandler() == null) return;
        List<PlayerListEntry> players = client.getNetworkHandler().getPlayerList();
        int x = context.getScaledWindowWidth() - 110;
        int y = 20;
        int index = 0;

        for (PlayerListEntry entry : players) {
            String name = entry.getProfile().getName();
            int boxY = y + index * 18;
            // background
            context.fill(x - 4, boxY - 2, x + 100, boxY + 14, 0x88000000);
            // name
            context.drawText(client.textRenderer, name, x, boxY, 0xFFFFFF, true);
            index++;
        }
    }

    public static void handleMouseClick(double mouseX, double mouseY) {
        if (client.player == null || client.getNetworkHandler() == null) return;
        int scaledX = (int)(mouseX * client.getWindow().getScaledWidth() / client.getWindow().getWidth());
        int scaledY = (int)(mouseY * client.getWindow().getScaledHeight() / client.getWindow().getHeight());

        int x = client.getWindow().getScaledWidth() - 110;
        int yStart = 20;
        int index = 0;

        for (PlayerListEntry entry : client.getNetworkHandler().getPlayerList()) {
            int boxY = yStart + index * 18;
            if (scaledX >= x - 4 && scaledX <= x + 100 && scaledY >= boxY - 2 && scaledY <= boxY + 14) {
                sendTeleportRequest(entry.getProfile().getName());
                break;
            }
            index++;
        }
    }

    private static void sendTeleportRequest(String name) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(name);
        ClientPlayNetworking.send(TpNetworking.TELEPORT_ID, buf);
    }
}
