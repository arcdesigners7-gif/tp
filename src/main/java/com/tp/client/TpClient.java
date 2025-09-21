package com.tp.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.v1.MouseButtonCallback;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class TpClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Render HUD
        HudRenderCallback.EVENT.register((context, tickDelta) -> TpHudOverlay.render(context));

        // Listen mouse click to detect clicks on our HUD
        MouseButtonCallback.EVENT.register((window, button, action, mods) -> {
            if (button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS) {
                // mouse coordinates in raw window pixels
                TpHudOverlay.handleMouseClick(MinecraftClient.getInstance().mouse.getX(), MinecraftClient.getInstance().mouse.getY());
            }
            return false;
        });
    }
}
